package br.com.financehub.apifinancehub.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrcamentoDTO {

    private BigDecimal limiteOrcamento;

    private Long idUsuario;

    private LocalDate mesReferencia;
}
