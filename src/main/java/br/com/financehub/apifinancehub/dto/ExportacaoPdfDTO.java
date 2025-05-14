package br.com.financehub.apifinancehub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExportacaoPdfDTO {

    private Long idUsuario;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;
}
