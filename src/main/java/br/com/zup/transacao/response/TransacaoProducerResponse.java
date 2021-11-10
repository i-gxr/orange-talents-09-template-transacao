package br.com.zup.transacao.response;

import br.com.zup.transacao.modelo.*;

import java.math.*;
import java.time.*;

public class TransacaoProducerResponse {

    private String id;
    private BigDecimal valor;
    private EstabelecimentoProducerResponse estabelecimento;
    private CartaoProducerResponse cartao;
    private LocalDateTime efetivadaEm;

    @Deprecated
    public TransacaoProducerResponse() {}

    public TransacaoProducerResponse(String id, BigDecimal valor, EstabelecimentoProducerResponse estabelecimento, CartaoProducerResponse cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoProducerResponse getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoProducerResponse getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao paraModelo() {
        return new Transacao(
            this.id,
            this.valor,
            this.estabelecimento.paraModelo(),
            this.cartao.paraModelo(),
            this.efetivadaEm
        );
    }

}
