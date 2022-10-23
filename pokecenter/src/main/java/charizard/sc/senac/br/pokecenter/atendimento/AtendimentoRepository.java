package charizard.sc.senac.br.pokecenter.atendimento;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AtendimentoRepository extends PagingAndSortingRepository<Atendimento, Long>,
        QuerydslPredicateExecutor<Atendimento> {

    List<Atendimento> findAll();
}
