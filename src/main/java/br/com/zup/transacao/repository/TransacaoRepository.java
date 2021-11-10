package br.com.zup.transacao.repository;

import br.com.zup.transacao.modelo.*;
import org.springframework.data.repository.*;

public interface TransacaoRepository extends CrudRepository<Transacao, String> {

}
