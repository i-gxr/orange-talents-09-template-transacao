package br.com.zup.transacao.controller;

import br.com.zup.transacao.modelo.*;
import br.com.zup.transacao.repository.*;
import br.com.zup.transacao.response.*;
import br.com.zup.transacao.util.*;
import com.google.gson.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import java.math.*;
import java.time.*;
import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacaoRepository repository;

    @Autowired
    private Gson gson;

    private String uri;

    @BeforeEach
    void setUp() {
        this.uri = "/api/transacoes";
    }

    @Test
    void buscarUltimasTransacoesDeveRetornarStatusCode200QuandoRequisicaoForValida() throws Exception {
        String cartaoId = "1921-1063-9349-1322";

        Transacao transacao = Mockito.mock(Transacao.class);

        TransacaoCartaoResponse transacaoCartaoResponse = new TransacaoCartaoResponse(
                UUID.randomUUID().toString(),
                new BigDecimal("199.90"),
                new EstabelecimentoResponse(
                        "Café Zup",
                        "São Paulo",
                        "R. Zupper, 101 - Brooklyn"
                ),
                LocalDateTime.now()
        );

        List<Transacao> transacoes = List.of(
                transacao, transacao, transacao, transacao, transacao,
                transacao, transacao, transacao, transacao, transacao
        );

        Mockito.when(repository.existsByCartaoId(Mockito.any())).thenReturn(true);
        Mockito.when(repository.findByCartaoId(Mockito.anyString(), Mockito.any())).thenReturn(transacoes);
        Mockito.when(transacao.paraTransacaoCartaoResponse()).thenReturn(transacaoCartaoResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .param("cartaoId", cartaoId)
                .characterEncoding("utf-8")
        ).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void buscarUltimasTransacoesDeveRetornarListTransacaoCartaoResponse() throws Exception {
        String cartaoId = "1921-1063-9349-1322";

        Transacao transacao = Mockito.mock(Transacao.class);

        TransacaoCartaoResponse transacaoCartaoResponse = new TransacaoCartaoResponse(
                UUID.randomUUID().toString(),
                new BigDecimal("199.90"),
                new EstabelecimentoResponse(
                        "Café Zup",
                        "São Paulo",
                        "R. Zupper, 101 - Brooklyn"
                ),
                LocalDateTime.now()
        );

        List<Transacao> transacoes = List.of(
                transacao, transacao, transacao, transacao, transacao,
                transacao, transacao, transacao, transacao, transacao
        );

        List<TransacaoCartaoResponse> transacoesCartaoResponse = List.of(
                transacaoCartaoResponse, transacaoCartaoResponse,
                transacaoCartaoResponse, transacaoCartaoResponse,
                transacaoCartaoResponse, transacaoCartaoResponse,
                transacaoCartaoResponse, transacaoCartaoResponse,
                transacaoCartaoResponse, transacaoCartaoResponse
        );

        Mockito.when(repository.existsByCartaoId(Mockito.any())).thenReturn(true);
        Mockito.when(repository.findByCartaoId(Mockito.anyString(), Mockito.any())).thenReturn(transacoes);
        Mockito.when(transacao.paraTransacaoCartaoResponse()).thenReturn(transacaoCartaoResponse);

        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .param("cartaoId", cartaoId)
                .characterEncoding("utf-8")
        ).andExpect(MockMvcResultMatchers.content().json(gson.toJson(transacoesCartaoResponse)));
    }

    @ParameterizedTest
    @NullSource
    void buscarUltimasTransacoesDeveRetornarStatusCode404QuandoCartaoIdInvalido(String cartaoId) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .param("cartaoId", cartaoId)
        ).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = "1000")
    void buscarUltimasTransacoesDeveRetornarStatusCode404QuandoCartaoIdNaoExistir(String cartaoId) throws Exception {
        Mockito.when(repository.existsByCartaoId(Mockito.any())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/cartoes")
                .contentType(MediaType.APPLICATION_JSON)
                .param("cartaoId", cartaoId)
        ).andExpect(MockMvcResultMatchers.status().is(404));
    }

}