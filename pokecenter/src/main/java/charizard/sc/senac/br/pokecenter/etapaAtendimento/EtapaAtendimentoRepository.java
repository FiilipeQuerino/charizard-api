package charizard.sc.senac.br.pokecenter.etapaAtendimento;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EtapaAtendimentoRepository extends PagingAndSortingRepository<EtapaAtendimento, Long>,
        QuerydslPredicateExecutor<EtapaAtendimento> {

    List<EtapaAtendimento> findAll();
}
