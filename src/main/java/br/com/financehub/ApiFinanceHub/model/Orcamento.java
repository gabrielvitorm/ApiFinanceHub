package br.com.financehub.ApiFinanceHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orcamento")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orcamento", nullable = false)
    private Long idOrcamento;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "limite_mensal", nullable = false)
    private double limiteMensal;

    @Column(name = "gasto_atual", nullable = false)
    private double gastoAtual;

    @Column(name = "meta_economia")
    private Double metaEconomia;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_modificacao", updatable = false)
    private LocalDateTime dataModificacao;
}
