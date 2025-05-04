package br.com.financehub.ApiFinanceHub.controller;

import br.com.financehub.ApiFinanceHub.dto.ExportacaoPdfDTO;
import br.com.financehub.ApiFinanceHub.service.ExportacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/exportacoes")
@Tag(name = "Exportações", description = "Endpoint para exportar o relatório em PDF")
public class ExportacaoController {

    @Autowired
    private ExportacaoService exportacaoService;

    @Operation(summary = "Endpoint que retorna relatório", description = "Retorna o relatório em PDF de acordo com a data selecionada pelo usuário")
    @PostMapping("/exportar/pdf")
    public ResponseEntity<byte[]> exportarTransacoesParaPdf(@RequestBody ExportacaoPdfDTO exportacaoPdfDTO) {

        byte[] pdfBytes = exportacaoService.exportarTransacaoParaPdf(exportacaoPdfDTO.getIdUsuario(), exportacaoPdfDTO.getDataInicio(), exportacaoPdfDTO.getDataFim());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio_transacoes.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}