package br.com.financehub.ApiFinanceHub.controller;

import br.com.financehub.ApiFinanceHub.service.ExportacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transacoes")
public class ExportacaoController {

    @Autowired
    private ExportacaoService exportacaoService;

    @GetMapping("/exportar/pdf")
    public ResponseEntity<byte[]> exportarTransacoesParaPdf(
            @RequestParam("idUsuario") Long idUsuario,
            @RequestParam("dataInicio") String dataInicio,
            @RequestParam("dataFim") String dataFim) {

        LocalDateTime inicio = LocalDateTime.parse(dataInicio);
        LocalDateTime fim = LocalDateTime.parse(dataFim);

        byte[] pdfBytes = exportacaoService.exportarTransacaoParaPdf(idUsuario, inicio, fim);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio_transacoes.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}