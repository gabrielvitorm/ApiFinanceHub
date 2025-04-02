package br.com.financehub.ApiFinanceHub.service;

import br.com.financehub.ApiFinanceHub.model.Transacao;
import br.com.financehub.ApiFinanceHub.model.Usuario;
import br.com.financehub.ApiFinanceHub.repository.TransacaoRepository;
import br.com.financehub.ApiFinanceHub.repository.UsuarioRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.lang.System.out;

@Service
public class ExportacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public byte[] exportarTransacaoParaPdf(Long idUsuario, LocalDateTime dataInicio, LocalDateTime dataFim){
        List<Transacao> transacoes = transacaoRepository.findByUsuarioIdUsuarioAndDataCriacaoBetween(idUsuario, dataInicio, dataFim);

        Optional<Usuario> usuarioBancoDeDados = usuarioRepository.findById(idUsuario);

        if (usuarioBancoDeDados.isEmpty()){
            throw new RuntimeException("Usuário não existe!");
        }

        Usuario usuarioExistente = usuarioBancoDeDados.get();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(out));
             Document document = new Document(pdfDoc)) {

            document.add(new Paragraph("Relatório de Transações"));
            document.add(new Paragraph("Id: " + idUsuario));
            document.add(new Paragraph("Nome: " + usuarioExistente.getNomeUsuario()));
            document.add(new Paragraph("Email: " + usuarioExistente.getEmailUsuario()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            document.add(new Paragraph("Período: " + dataInicio.format(formatter) + " até " + dataFim.format(formatter)));

            Table table = new Table(7);
            table.addCell("ID Transação");
            table.addCell("Nome");
            table.addCell("Descrição");
            table.addCell("Valor");
            table.addCell("Categoria");
            table.addCell("Tipo");
            table.addCell("Data");

            if (transacoes.isEmpty()) {
                document.add(new Paragraph("Nenhuma transação encontrada."));
            } else {
                for (Transacao transacao : transacoes) {
                    table.addCell(String.valueOf(transacao.getIdTransacao()));
                    table.addCell(String.valueOf(transacao.getNomeTransaca()));
                    table.addCell(transacao.getDescricaoTransacao());
                    table.addCell(String.valueOf(transacao.getValor()));
                    table.addCell(String.valueOf(transacao.getTipoCategoria()));
                    table.addCell(String.valueOf(transacao.getTipoTransacao()));
                    table.addCell(transacao.getDataCriacao().format(formatter));
                }
            }

            document.add(table);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }

        return out.toByteArray();
    }
}
