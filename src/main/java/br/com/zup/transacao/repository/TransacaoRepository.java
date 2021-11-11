package br.com.zup.transacao.repository;

import br.com.zup.transacao.modelo.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface TransacaoRepository extends JpaRepository<Transacao, String> {

    boolean existsByCartaoId(String cartaoId);

    List<Transacao> findByCartaoId(String cartaoId, Pageable pageable);

}
