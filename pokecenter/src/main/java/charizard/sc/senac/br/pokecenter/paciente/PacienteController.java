package charizard.sc.senac.br.pokecenter.paciente;



import charizard.sc.senac.br.pokecenter.pokemon.PokemonService;
import charizard.sc.senac.br.pokecenter.treinador.Treinador;
import charizard.sc.senac.br.pokecenter.treinador.TreinadorRepresentation;
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
    @GetMapping("/")
    public ResponseEntity<List<PacienteRepresentation.Padrao>> buscarPaciente(
            @QuerydslPredicate(root = Paciente.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name="paginaSelecionada", defaultValue = "0") int paginaSelecionada) {

        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina); //Pesquisar o que/como usar o sort

        Page<Paciente> pacienteList = Objects.isNull(filtroURI)?
                this.pacienteService.buscarTodos(pageable):
                this.pacienteService.buscarTodos(filtroURI, pageable);

        List<PacienteRepresentation.Padrao> listaFinal = PacienteRepresentation.Padrao.from(pacienteList.getContent());
        return ResponseEntity.ok(listaFinal);
    }
    @GetMapping("/{idPaciente}")
    public ResponseEntity<PacienteRepresentation.Padrao> buscarUmPaciente(
            @PathVariable Long idPaciente) {

        Paciente paciente = this.pacienteService.buscarUmPaciente(idPaciente);

        PacienteRepresentation.Padrao detalhes =
                PacienteRepresentation.Padrao
                        .from(paciente);

        return ResponseEntity
                .ok(detalhes);

    }
    @PutMapping("/{idPaciente}")
    public ResponseEntity<PacienteRepresentation.Padrao> atualizarPaciente(
            @PathVariable Long idPaciente,
            @RequestBody PacienteRepresentation.CriarOuAtualizar atualizar) {

        Paciente pacienteAtualizado =
                this.pacienteService.atualizar(idPaciente, atualizar);

        PacienteRepresentation.Padrao detalhes =
                PacienteRepresentation.Padrao
                        .from(pacienteAtualizado);

        return ResponseEntity
                .ok(detalhes);


    }
}
