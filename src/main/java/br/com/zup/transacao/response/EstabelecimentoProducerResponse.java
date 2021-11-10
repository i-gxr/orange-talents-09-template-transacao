package br.com.zup.transacao.response;

import br.com.zup.transacao.modelo.*;

public class EstabelecimentoProducerResponse {

    private String nome;
    private String cidade;
    private String endereco;

    @Deprecated
    public EstabelecimentoProducerResponse() {}

    public EstabelecimentoProducerResponse(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public Estabelecimento paraModelo() {
        return new Estabelecimento(this.nome, this.cidade, this.endereco);
    }
}
