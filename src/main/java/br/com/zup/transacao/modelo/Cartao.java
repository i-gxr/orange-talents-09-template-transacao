package br.com.zup.transacao.modelo;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tb_cartao")
public class Cartao {

    @Id
    private String id;

    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;

    @Deprecated
    public Cartao() {}

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

}
