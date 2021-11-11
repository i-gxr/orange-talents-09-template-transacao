package br.com.zup.transacao.controller;

import br.com.zup.transacao.exception.*;
import br.com.zup.transacao.modelo.*;
import br.com.zup.transacao.repository.*;
import br.com.zup.transacao.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping(value = "/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @GetMapping("/cartoes")
    public List<TransacaoCartaoResponse> buscarUltimasTransacoes(@RequestParam String cartaoId) {
        if (!repository.existsByCartaoId(cartaoId))
            throw new CartaoNaoEncontradoException();

        List<Transacao> transacoes = repository.findByCartaoId(
                cartaoId, PageRequest.of(
                        0,
                        10,
                        Sort.by("efetivadaEm").descending()
                )
        );
        return transacoes.stream().map(Transacao::paraTransacaoCartaoResponse).collect(Collectors.toList());
    }

}
