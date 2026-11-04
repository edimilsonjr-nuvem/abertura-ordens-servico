package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

    public EquipamentoService(
            EquipamentoRepository equipamentoRepository,
            SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Optional<Equipamento> cadastrar(EquipamentoRequest dados) {

       Optional<Setor> setorEncontrado =
                setorRepository.findById(dados.getSetorId());

       if (setorEncontrado.isEmpty()) {
        return Optional.empty();
       }

       Equipamento equipamento = new Equipamento();
       equipamento.setNome(dados.getNome());
       equipamento.setNumeroPatrimonio(dados.getNumeroPatrimonio());
       equipamento.setSetor(setorEncontrado.get());

       return Optional.of(equipamentoRepository.save(equipamento));
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizar(
            Integer id, EquipamentoRequest novosDados) {

        Optional<Equipamento> equipamentoExistente =
                equipamentoRepository.findById(id);

        if (equipamentoExistente.isPresent()) {

            if (novosDados.getSetorId() == null) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O ID do setor não foi informado"
                );
            }

            Equipamento equipamento = equipamentoExistente.get();

            equipamento.setNome(novosDados.getNome());
            equipamento.setNumeroPatrimonio(
                novosDados.getNumeroPatrimonio()
            );

            Optional<Setor> setor =
                    setorRepository.findById(novosDados.getSetorId());

            if (setor.isEmpty()) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Setor não encontrado"
                );
            }

            equipamento.setSetor(setor.get());

            return Optional.of(equipamentoRepository.save(equipamento));

        } else {
            return Optional.empty();
        }
    }

    public boolean excluir(Integer id) {

        if (!equipamentoRepository.existsById(id)) {
            return false;
        }

        equipamentoRepository.deleteById(id);
        return true;
    }
}
