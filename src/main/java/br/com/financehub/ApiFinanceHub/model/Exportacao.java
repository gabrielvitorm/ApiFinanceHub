package br.com.financehub.ApiFinanceHub.model;

import br.com.financehub.ApiFinanceHub.enums.TipoExportacaoEnum;
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
@Table(name = "exportacao")
public class Exportacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exportacao", nullable = false)
    private Long idExportacao;

    @Column(name = "tipo_exportacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoExportacaoEnum tipoExportacaoEnum;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_exportacao", nullable = true)
    private LocalDateTime dataExportacao;

    @ManyToOne
    @JoinColumn(name = "USUARIOS_id_usuario", nullable = false)
    private Usuario usuario;
}
