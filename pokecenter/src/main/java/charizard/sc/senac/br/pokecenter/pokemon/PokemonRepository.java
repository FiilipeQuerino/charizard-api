package charizard.sc.senac.br.pokecenter.pokemon;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Long>,
        QuerydslPredicateExecutor<Pokemon> {

    List<Pokemon> findAll();
}
