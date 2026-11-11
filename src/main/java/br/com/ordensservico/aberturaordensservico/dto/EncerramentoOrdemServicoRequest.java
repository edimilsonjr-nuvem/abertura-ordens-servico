package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;

public class EncerramentoOrdemServicoRequest {
    
    @NotBlank (message = "A solução da ordem de serviço é obrigatória")
    private String solucao;

    public EncerramentoOrdemServicoRequest() {
    }

    public EncerramentoOrdemServicoRequest(String solucao) {
        this.solucao = solucao;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }
}
