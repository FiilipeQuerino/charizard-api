package charizard.sc.senac.br.pokecenter.evolucaoAtendimento;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EvolucaoAtendimentoRepository extends PagingAndSortingRepository<EvolucaoAtendimento, Long>,
        QuerydslPredicateExecutor<EvolucaoAtendimento> {

    List<EvolucaoAtendimento> findAll();
}
