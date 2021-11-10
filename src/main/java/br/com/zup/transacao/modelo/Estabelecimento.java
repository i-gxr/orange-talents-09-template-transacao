package br.com.zup.transacao.modelo;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tb_estabelecimento")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cidade;

    @NotBlank
    private String endereco;

    @Deprecated
    public Estabelecimento() {}

    public Estabelecimento(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

}
