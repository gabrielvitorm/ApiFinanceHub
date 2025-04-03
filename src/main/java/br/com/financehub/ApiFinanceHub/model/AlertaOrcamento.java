package br.com.financehub.ApiFinanceHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alerta_orcamento")
public class AlertaOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta_orcamento", nullable = false)
    private Long idAlertaOrcamento;

    @Column(name = "valor_atingido_orcamento", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorAtingidoOrcamento;

    @Column(name = "percentual_atingido_orcamento", precision = 10, scale = 2, nullable = false)
    private BigDecimal percentualAtingidoOrcamento;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_alerta_gerado", nullable = false, updatable = false)
    private LocalDateTime dataAlertaGerado;

    @ManyToOne
    @JoinColumn(name = "ORCAMENTO_id_orcamento", nullable = false)
    private Orcamento orcamento;
}
