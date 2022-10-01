package charizard.sc.senac.br.pokecenter.paciente;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PacienteRepository extends PagingAndSortingRepository<Paciente, Long>,
        QuerydslPredicateExecutor<Paciente> {

    List<Paciente> findAll();
}
