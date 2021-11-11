package br.com.zup.transacao.response;

import com.fasterxml.jackson.annotation.*;

import java.math.*;
import java.time.*;

public class TransacaoCartaoResponse {

    @JsonProperty
    private String id;

    @JsonProperty
    private BigDecimal valor;

    @JsonProperty
    private EstabelecimentoResponse estabelecimento;

    @JsonProperty
    private LocalDateTime efetivadaEm;

    public TransacaoCartaoResponse(String id, BigDecimal valor, EstabelecimentoResponse estabelecimento, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.efetivadaEm = efetivadaEm;
    }

}
