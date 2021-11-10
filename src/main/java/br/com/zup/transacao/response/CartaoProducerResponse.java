package br.com.zup.transacao.response;

import br.com.zup.transacao.modelo.*;

public class CartaoProducerResponse {

    private String id;
    private String email;

    @Deprecated
    public CartaoProducerResponse() {}

    public CartaoProducerResponse(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao paraModelo() {
        return new Cartao(this.id, this.email);
    }

}
