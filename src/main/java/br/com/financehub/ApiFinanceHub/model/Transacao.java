package br.com.financehub.ApiFinanceHub.model;

import br.com.financehub.ApiFinanceHub.enums.CategoriaTransacaoEnum;
import br.com.financehub.ApiFinanceHub.enums.TipoTransacaoEnum;
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
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long idTransacao;

    @Column(name = "nome_transacao", nullable = false)
    private String nomeTransaca;

    @Column(name = "descricao_transacao", columnDefinition = "TEXT")
    private String descricaoTransacao;

    @Column(name = "valor_transacao", precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_modificacao", nullable = true)
    private LocalDateTime dataModificacao;

    @Column(name = "tipo_transacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTransacaoEnum tipoTransacao;

    @Column(name = "tipo_categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaTransacaoEnum tipoCategoria;

    @ManyToOne
    @JoinColumn(name = "USUARIOS_id_usuario", nullable = false)
    private Usuario usuario;

}
