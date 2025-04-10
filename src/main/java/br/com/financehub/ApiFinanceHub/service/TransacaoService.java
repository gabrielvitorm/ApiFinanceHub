package br.com.financehub.ApiFinanceHub.service;

import br.com.financehub.ApiFinanceHub.enums.CategoriaTransacaoEnum;
import br.com.financehub.ApiFinanceHub.model.Transacao;
import br.com.financehub.ApiFinanceHub.model.Usuario;
import br.com.financehub.ApiFinanceHub.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    public void criarTransacao(Transacao transacao){
        transacaoRepository.save(transacao);
    }

    public List<Transacao> listarTodasTransacoes(){
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> listarTransacaoPorId(Long idTransacao){
        return transacaoRepository.findById(idTransacao);
    }

    public Optional<Transacao> listarPorTipoCategoria(CategoriaTransacaoEnum tipoCategoria) {
        return transacaoRepository.findByTipoCategoria(tipoCategoria);
    }

    public void deletarTransacaoPorId(Long idTransacao){
        if (transacaoRepository.existsById(idTransacao)){
            transacaoRepository.deleteById(idTransacao);
        }else {
            throw new RuntimeException("Transação não encontrada!");
        }
    }
    public void atualizarTransacaoPorId(Long idTransacao, Transacao transacao){
        Optional<Transacao> trasacaoBancoDeDados = listarTransacaoPorId(idTransacao);

        if (trasacaoBancoDeDados.isEmpty()){
            throw new RuntimeException("Transação não encontrado no Banco de dados");
        }

        Transacao trasacaoEditado = trasacaoBancoDeDados.get();

        trasacaoEditado.setNomeTransaca(transacao.getNomeTransaca());
        trasacaoEditado.setDescricaoTransacao(transacao.getDescricaoTransacao());
        trasacaoEditado.setValor(transacao.getValor());
        trasacaoEditado.setTipoTransacao(transacao.getTipoTransacao());
        trasacaoEditado.setTipoCategoria(transacao.getTipoCategoria());
        trasacaoEditado.setDataModificacao(transacao.getDataModificacao());

        transacaoRepository.save(trasacaoEditado);
    }
}
