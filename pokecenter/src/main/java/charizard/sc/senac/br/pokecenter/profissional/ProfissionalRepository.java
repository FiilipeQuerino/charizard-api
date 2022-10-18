package charizard.sc.senac.br.pokecenter.profissional;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProfissionalRepository extends PagingAndSortingRepository<Profissional, Long>,
        QuerydslPredicateExecutor<Profissional> {

    List<Profissional> findAll();

}

