package charizard.sc.senac.br.pokecenter.etapaAtendimento;



import charizard.sc.senac.br.pokecenter.atendimento.Atendimento;
import charizard.sc.senac.br.pokecenter.atendimento.AtendimentoService;
import charizard.sc.senac.br.pokecenter.paciente.PacienteService;
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
@RequestMapping("api/atendimento/{idAtendimento}/etapaatendimento")
@CrossOrigin("*")
@AllArgsConstructor
public class EtapaAtendimentoController {

    private EtapaAtendimentoService etapaAtendimentoService;
    private AtendimentoService atendimentoService;

    @PostMapping("/")
    public ResponseEntity<EtapaAtendimentoRepresentation.Detalhes> cadastrarEtapaAtendimento(
            @PathVariable Long idAtendimento,
            @Valid @RequestBody EtapaAtendimentoRepresentation.CriarOuAtualizar criar) {

        EtapaAtendimento etapaAtendimento =
                this.etapaAtendimentoService.criarEtapaAtendimento(atendimentoService, idAtendimento, criar);
        return ResponseEntity.status(HttpStatus.SC_CREATED)
                .body(EtapaAtendimentoRepresentation.Detalhes.from(etapaAtendimento));
    }
    @GetMapping("/")
    public ResponseEntity<List<EtapaAtendimentoRepresentation.Lista>> buscarEtapaAtendimento(
            @QuerydslPredicate(root = EtapaAtendimento.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name="paginaSelecionada", defaultValue = "0") int paginaSelecionada) {

        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<EtapaAtendimento> atendimentoList = Objects.isNull(filtroURI)?
                this.etapaAtendimentoService.buscarTodos(pageable):
                this.etapaAtendimentoService.buscarTodos(filtroURI, pageable);

        List<EtapaAtendimentoRepresentation.Lista> listaFinal = EtapaAtendimentoRepresentation.Lista.from(atendimentoList.getContent());
        return ResponseEntity.ok(listaFinal);
    }

    @GetMapping("/{idEtapaAtendimento}")
    public ResponseEntity<EtapaAtendimentoRepresentation.Detalhes> buscarUmaEtapaAtendimento(
            @PathVariable Long idEtapaAtendimento) {

        EtapaAtendimento etapaAtendimento = this.etapaAtendimentoService.buscarUmaEtapaAtendimento(idEtapaAtendimento);

        EtapaAtendimentoRepresentation.Detalhes detalhes =
                EtapaAtendimentoRepresentation.Detalhes
                        .from(etapaAtendimento);

        return ResponseEntity
                .ok(detalhes);
    }
    @PutMapping("/{idEtapaAtendimento}")
    public ResponseEntity<EtapaAtendimentoRepresentation.Detalhes> atualizarEtapaAtendimento(
            @PathVariable Long idEtapaAtendimento,
            @RequestBody EtapaAtendimentoRepresentation.CriarOuAtualizar atualizar) {

        EtapaAtendimento etapaAtendimentoAtualizado =
                this.etapaAtendimentoService.atualizar(idEtapaAtendimento, atualizar);

        EtapaAtendimentoRepresentation.Detalhes detalhes =
                EtapaAtendimentoRepresentation.Detalhes
                        .from(etapaAtendimentoAtualizado);
        return ResponseEntity
                .ok(detalhes);
    }
}
