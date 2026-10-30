package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import org.springframework.stereotype.Service; 
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import java.util.Optional;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;



@Service
public class EquipamentoService {
    
    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public Equipamento cadastrar(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
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