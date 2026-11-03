package br.com.ordensservico.aberturaordensservico.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import java.util.Optional;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;


@Service
public class EquipamentoService {
    
    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Optional<Equipamento> cadastrar(
       String nome, String numeroPatrimonio, Integer setorId) {

            Optional<Setor> setorEncontrado = setorRepository.findById(setorId);

            if (setorEncontrado.isPresent()) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O setor não foi encontrado");
            }

            Equipamento novoEquipamento = new Equipamento();
            novoEquipamento.setNome(nome);
            novoEquipamento.setNumeroPatrimonio(numeroPatrimonio);
            novoEquipamento.setSetor(setorEncontrado.get());

            return Optional.of(equipamentoRepository.save(novoEquipamento));
        }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    
    public Optional<Equipamento> atualizar(Integer id, Equipamento novosDados) {

        Optional<Equipamento> equipamentoExistente = equipamentoRepository.findById(id.intValue());

        if (equipamentoExistente.isEmpty()) {
           return Optional.empty();
        }

        Equipamento equipamento = equipamentoExistente.get();

        equipamento.setNome(novosDados.getNome());
        equipamento.setNumeroPatrimonio(novosDados.getNumeroPatrimonio());
        equipamento.setSetor(novosDados.getSetor());
        return Optional.of(equipamentoRepository.save(equipamento));

    }

    public boolean excluir(Integer id) {

        if (!equipamentoRepository.existsById(id.intValue())) {
            return false;
        }
        equipamentoRepository.deleteById(id.intValue());
        return true;
    }


}