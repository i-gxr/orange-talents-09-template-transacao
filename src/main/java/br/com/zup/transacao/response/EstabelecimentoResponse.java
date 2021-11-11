package br.com.zup.transacao.response;

import com.fasterxml.jackson.annotation.*;

public class EstabelecimentoResponse {

    @JsonProperty
    private String nome;

    @JsonProperty
    private String cidade;

    @JsonProperty
    private String endereco;

    public EstabelecimentoResponse(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

}
