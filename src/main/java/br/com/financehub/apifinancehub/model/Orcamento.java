package br.com.financehub.apifinancehub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "limite_orcamento",precision = 10, scale = 2, nullable = false)
    private BigDecimal limiteOrcamento;

    @Column(name = "mes_referencia", nullable = false)
    private LocalDate mesReferencia;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "USUARIO_id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "ALERTAORCAMENTO_id_alerta_orcamento")
    private List<AlertaOrcamento> alertas;

    @OneToMany
    @JoinColumn(name = "TRANSACAO_id_transacao")
    private List<Transacao> transacoes;
}
