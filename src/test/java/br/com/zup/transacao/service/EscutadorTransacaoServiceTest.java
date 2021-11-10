package br.com.zup.transacao.service;

import br.com.zup.transacao.modelo.*;
import br.com.zup.transacao.repository.*;
import br.com.zup.transacao.response.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import javax.transaction.*;
import java.math.*;
import java.time.*;
import java.util.*;

@SpringBootTest
@Transactional
class EscutadorTransacaoServiceTest {

    @Autowired
    private EscutadorTransacaoService escutadorTransacaoService;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Test
    void consumidorDeveSalvarTransacaoQuandoTransacaoProducerResponseForRecebido() {
        TransacaoProducerResponse response = new TransacaoProducerResponse(
                UUID.randomUUID().toString(),
                new BigDecimal("199.90"),
                new EstabelecimentoProducerResponse(
                        "Restaurante Zup",
                        "São Paulo",
                        "Rua Zupper, 2021 - Brooklyn"
                ),
                new CartaoProducerResponse(
                        UUID.randomUUID().toString(),
                        "zupper@email.com"
                ),
                LocalDateTime.now()
        );
        escutadorTransacaoService.consumidor(response);
        Optional<Transacao> transacao = transacaoRepository.findById(response.getId());
        Assertions.assertEquals(response.getId(), transacao.get().getId());
    }

    @Test
    void consumidorDeveLancarExceptionQuandoTransacaoProducerResponseRecebidoForInvalido() {
        TransacaoProducerResponse response = new TransacaoProducerResponse();
        Assertions.assertThrows(RuntimeException.class, () -> {
            escutadorTransacaoService.consumidor(response);
        });
    }

}