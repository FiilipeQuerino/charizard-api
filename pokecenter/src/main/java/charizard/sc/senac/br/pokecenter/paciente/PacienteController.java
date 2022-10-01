package charizard.sc.senac.br.pokecenter.paciente;



import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
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
@RequestMapping("api/paciente")
@CrossOrigin("*")
@AllArgsConstructor
public class PacienteController {

    private PacienteService pacienteService;

    @PostMapping("/")
    public ResponseEntity<PacienteRepresentation.Detalhes> createPaciente(
            @RequestBody @Valid PacienteRepresentation.CriarOuAtualizar criar){

        Paciente paciente = this.pacienteService.criarPaciente(criar);

        PacienteRepresentation.Detalhes detalhes =
                PacienteRepresentation.Detalhes.from(paciente);

        return ResponseEntity
                .ok(detalhes);
    }
    @GetMapping("/")
    public ResponseEntity<List<PacienteRepresentation.Lista>> buscarPaciente(
            @QuerydslPredicate(root = Paciente.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name="paginaSelecionada", defaultValue = "0") int paginaSelecionada) {

        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina); //Pesquisar o que/como usar o sort

        Page<Paciente> pacienteList = Objects.isNull(filtroURI)?
                this.pacienteService.buscarTodos(pageable):
                this.pacienteService.buscarTodos(filtroURI, pageable);

        List<PacienteRepresentation.Lista> listaFinal = PacienteRepresentation.Lista.from(pacienteList.getContent());
        return ResponseEntity.ok(listaFinal);
    }
    @PutMapping("/{idPaciente}")
    public ResponseEntity<PacienteRepresentation.Detalhes> atualizarPaciente(@PathVariable Long idPaciente, @RequestBody PacienteRepresentation.CriarOuAtualizar atualizar) {
        Paciente pacienteAtualizado = this.pacienteService.atualizar(idPaciente, atualizar);
        PacienteRepresentation.Detalhes detalhes = PacienteRepresentation.Detalhes.from(pacienteAtualizado);

        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/{idPaciente}")
    public ResponseEntity<PacienteRepresentation.Detalhes> buscarUmPokemon(
            @PathVariable Long idPaciente) {
        Paciente paciente = this.pacienteService.buscarUmPaciente(idPaciente);

        PacienteRepresentation.Detalhes detalhes =
                PacienteRepresentation.Detalhes
                        .from(paciente);
        return ResponseEntity
                .ok(detalhes);
    }
}
