package br.com.zup.transacao.exception;

public class CartaoNaoEncontradoException extends BussinessException {

    public CartaoNaoEncontradoException() {
        super("O cartão informado não foi encontrado!");
    }

}
