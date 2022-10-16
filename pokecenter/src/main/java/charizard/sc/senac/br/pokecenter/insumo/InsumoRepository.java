package charizard.sc.senac.br.pokecenter.insumo;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface InsumoRepository extends PagingAndSortingRepository<Insumo, Long>,
        QuerydslPredicateExecutor<Insumo> {

    List<Insumo> findAll();
}
