package br.com.financehub.ApiFinanceHub.service;

import br.com.financehub.ApiFinanceHub.enums.CategoriaTransacaoEnum;
import br.com.financehub.ApiFinanceHub.model.Transacao;
import br.com.financehub.ApiFinanceHub.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
