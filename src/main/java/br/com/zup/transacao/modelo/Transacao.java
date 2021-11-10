package br.com.zup.transacao.modelo;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "tb_transacao")
public class Transacao {

    @Id
    private String id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @NotNull
    private LocalDateTime efetivadaEm;

    @Deprecated
    public Transacao() {}

    public Transacao(String id, BigDecimal valor, Estabelecimento estabelecimento, Cartao cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

}
