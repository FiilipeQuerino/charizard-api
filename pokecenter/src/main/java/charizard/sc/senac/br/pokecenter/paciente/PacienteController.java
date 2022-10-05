package charizard.sc.senac.br.pokecenter.paciente;



import charizard.sc.senac.br.pokecenter.pokemon.PokemonService;
import charizard.sc.senac.br.pokecenter.treinador.TreinadorService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/treinador/{idTreinador}/pokemon/{idPokemon}/paciente")
@CrossOrigin("*")
@AllArgsConstructor
public class PacienteController {
    private TreinadorService treinadorService;
    private PacienteService pacienteService;

    private PokemonService pokemonService;

    @PostMapping("/")
    public ResponseEntity<PacienteRepresentation.Padrao> cadastrarPaciente(
            @PathVariable Long idTreinador,
            @PathVariable Long idPokemon,
            @Valid @RequestBody PacienteRepresentation.CriarOuAtualizar criar) {

        Paciente paciente =
                this.pacienteService.criarPaciente(treinadorService,
                        idTreinador, pokemonService, idPokemon, criar);
        return ResponseEntity.status(HttpStatus.SC_CREATED)
                .body(PacienteRepresentation.Padrao.from(paciente));
    }
}
