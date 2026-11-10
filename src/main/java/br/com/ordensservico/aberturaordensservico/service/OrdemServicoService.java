package br.com.ordensservico.aberturaordensservico.service;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import java.time.LocalDateTime;
import java.util.List;




@Service
public class OrdemServicoService {
    
    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrar(OrdemServicoRequest dados) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(dados.getEquipamentoId());

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setDescricao(dados.getDescricao());
        ordemServico.setDataAbertura(LocalDateTime.now());
        ordemServico.setEquipamento(equipamentoEncontrado.get());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    
}
