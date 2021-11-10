package br.com.zup.transacao.service;

import br.com.zup.transacao.modelo.*;
import br.com.zup.transacao.repository.*;
import br.com.zup.transacao.response.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;

@Service
public class EscutadorTransacaoService {

    private static Logger logger = LoggerFactory.getLogger(EscutadorTransacaoService.class);

    @Autowired
    private TransacaoRepository repository;

    @Value("${topic.name.consumer")
    private String nomeTopico;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumidor (TransacaoProducerResponse response) {
        try {
            Transacao transacao = response.paraModelo();
            repository.save(transacao);
            logger.info(
                "[" + LocalDateTime.now() + "]"
                    + " Transação: " + transacao + ", de id " + response.getId() + " foi salva com sucesso!");
        }
            catch (Exception e) {
                logger.error(
                    "[" + LocalDateTime.now() + "]"
                            + " Erro: " + e.getMessage()
                );
                throw new RuntimeException("Error: " + e.getMessage());
            }
    }

}
