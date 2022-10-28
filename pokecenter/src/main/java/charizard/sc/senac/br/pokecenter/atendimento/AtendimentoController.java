package charizard.sc.senac.br.pokecenter.atendimento;



import charizard.sc.senac.br.pokecenter.paciente.Paciente;
import charizard.sc.senac.br.pokecenter.paciente.PacienteRepresentation;
import charizard.sc.senac.br.pokecenter.paciente.PacienteService;
import charizard.sc.senac.br.pokecenter.pokemon.PokemonService;
import charizard.sc.senac.br.pokecenter.treinador.Treinador;
import charizard.sc.senac.br.pokecenter.treinador.TreinadorRepresentation;
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
@RequestMapping("api/paciente/{idPaciente}/atendimento")
@CrossOrigin("*")
@AllArgsConstructor
public class AtendimentoController {

    private AtendimentoService atendimentoService;
    private PacienteService pacienteService;

    @PostMapping("/")
    public ResponseEntity<AtendimentoRepresentation.Detalhes> cadastrarAtendimento(
            @PathVariable Long idPaciente,
            @Valid @RequestBody AtendimentoRepresentation.CriarOuAtualizar criar) {

        Atendimento atendimento =
                this.atendimentoService.criarAtendimento(pacienteService, idPaciente, criar);
        return ResponseEntity.status(HttpStatus.SC_CREATED)
                .body(AtendimentoRepresentation.Detalhes.from(atendimento));
    }
    @GetMapping("/")
    public ResponseEntity<List<AtendimentoRepresentation.Lista>> buscarAtendimento(
            @QuerydslPredicate(root = Atendimento.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name="paginaSelecionada", defaultValue = "0") int paginaSelecionada) {

        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Atendimento> atendimentoList = Objects.isNull(filtroURI)?
                this.atendimentoService.buscarTodos(pageable):
                this.atendimentoService.buscarTodos(filtroURI, pageable);

        List<AtendimentoRepresentation.Lista> listaFinal = AtendimentoRepresentation.Lista.from(atendimentoList.getContent());
        return ResponseEntity.ok(listaFinal);
    }

    @GetMapping("/{idAtendimento}")
    public ResponseEntity<AtendimentoRepresentation.Detalhes> buscarUmAtendimento(
            @PathVariable Long idAtendimento) {

        Atendimento atendimento = this.atendimentoService.buscarUmAtendimento(idAtendimento);

        AtendimentoRepresentation.Detalhes detalhes =
                AtendimentoRepresentation.Detalhes
                        .from(atendimento);

        return ResponseEntity
                .ok(detalhes);
    }
    @PutMapping("/{idAtendimento}")
    public ResponseEntity<AtendimentoRepresentation.Detalhes> atualizarAtendimento(
            @PathVariable Long idAtendimento,
            @RequestBody AtendimentoRepresentation.CriarOuAtualizar atualizar) {

        Atendimento atendimentoAtualizado =
                this.atendimentoService.atualizar(idAtendimento, atualizar);

        AtendimentoRepresentation.Detalhes detalhes =
                AtendimentoRepresentation.Detalhes
                        .from(atendimentoAtualizado);
        return ResponseEntity
                .ok(detalhes);
    }
}
