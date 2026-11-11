package br.com.ordensservico.aberturaordensservico.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.ordensservico.aberturaordensservico.dto.EncerramentoOrdemServicoRequest;

@RestController 
@RequestMapping ("/ordens-servico")
public class OrdemServicoController {
    
    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping 
    public ResponseEntity<OrdemServico> cadastrar(@Valid @RequestBody OrdemServicoRequest dados) {
        Optional<OrdemServico> ordemServicoCadastrada = ordemServicoService.cadastrar(dados);

        if (ordemServicoCadastrada.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoCadastrada.get());
    }

    @GetMapping 
    public ResponseEntity<List<OrdemServico>> listar() {

        List<OrdemServico> ordensServico = ordemServicoService.listar();

        return ResponseEntity.ok(ordensServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Integer id) {
        Optional<OrdemServico> ordemServico = ordemServicoService.buscarPorId(id);

        if (ordemServico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ordemServico.get());
    }

    @PatchMapping ("/{id}/encerramento")
    public ResponseEntity<OrdemServico> encerrar(@PathVariable Integer id, @Valid @RequestBody EncerramentoOrdemServicoRequest dados) {
        Optional<OrdemServico> ordemServicoEncerrada = ordemServicoService.encerrar(id, dados);

        if (ordemServicoEncerrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ordemServicoEncerrada.get());
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<OrdemServico>> listarAbertas() {
        List<OrdemServico> ordensServicoAbertas = ordemServicoService.listarAbertas();

        return ResponseEntity.ok(ordensServicoAbertas);
    }
}
