package br.com.ordensservico.aberturaordensservico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordens_servico")
public class OrdemServico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "A descrição da ordem de serviço é obrigatória")
    private String descricao;

    private LocalDateTime dataAbertura;
    private LocalDateTime dataEncerramento;

    @ManyToOne
    @JoinColumn (name = "equipamento_id") 
    @NotNull (message = "O equipamento é obrigatório")
    private Equipamento equipamento;


    @NotBlank (message = "A solução da ordem de serviço é obrigatória")
    private String solucao;

    public OrdemServico() {
    }

    public OrdemServico(String descricao, LocalDateTime dataAbertura, LocalDateTime dataEncerramento, Equipamento equipamento, String solucao) {
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.dataEncerramento = dataEncerramento;
        this.equipamento = equipamento;
        this.solucao = solucao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }
}
