package charizard.sc.senac.br.pokecenter.evolucaoAtendimento;



import charizard.sc.senac.br.pokecenter.etapaAtendimento.EtapaAtendimentoService;
import charizard.sc.senac.br.pokecenter.leito.LeitoService;
import charizard.sc.senac.br.pokecenter.pokemon.PokemonService;
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
@RequestMapping("api/etapaatendimento/{idEtapaAtendimento}/evolucaoatendimento")
@CrossOrigin("*")
@AllArgsConstructor
public class EvolucaoAtendimentoController {
//    private ProfissionalService profissionalService;
    private LeitoService leitoService;
    private EvolucaoAtendimentoService evolucaoAtendimentoService;
    private EtapaAtendimentoService etapaatendimentoService;

    @PostMapping("/")
    public ResponseEntity<EvolucaoAtendimentoRepresentation.Detalhes> cadastrarEvolucaoAtendimento(
            @PathVariable Long idEtapaAtendimento,
            @Valid @RequestBody EvolucaoAtendimentoRepresentation.CriarOuAtualizar criar) {

        EvolucaoAtendimento evolucaoAtendimento =
                this.evolucaoAtendimentoService.criarEvolucaoAtendimento(etapaatendimentoService, idEtapaAtendimento, //profissionalService
                        leitoService,criar);

        return ResponseEntity.status(HttpStatus.SC_CREATED)
                .body(EvolucaoAtendimentoRepresentation.Detalhes.from(evolucaoAtendimento));
    }
    @GetMapping("/")
    public ResponseEntity<List<EvolucaoAtendimentoRepresentation.Lista>> buscarEvolucaoAtendimento(
            @QuerydslPredicate(root = EvolucaoAtendimento.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name="paginaSelecionada", defaultValue = "0") int paginaSelecionada) {

        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<EvolucaoAtendimento> evolucaoatendimentoList = Objects.isNull(filtroURI)?
                this.evolucaoAtendimentoService.buscarTodos(pageable):
                this.evolucaoAtendimentoService.buscarTodos(filtroURI, pageable);

        List<EvolucaoAtendimentoRepresentation.Lista> listaFinal = EvolucaoAtendimentoRepresentation.Lista.from(evolucaoatendimentoList.getContent());
        return ResponseEntity.ok(listaFinal);
    }

    @GetMapping("/{idEvolucaoAtendimento}")
    public ResponseEntity<EvolucaoAtendimentoRepresentation.Detalhes> buscarUmaEvolucaoAtendimento(
            @PathVariable Long idEvolucaoAtendimento) {

        EvolucaoAtendimento evolucaoAtendimento = this.evolucaoAtendimentoService.buscarUmaEvolucaoAtendimento(idEvolucaoAtendimento);

        EvolucaoAtendimentoRepresentation.Detalhes detalhes =
                EvolucaoAtendimentoRepresentation.Detalhes
                        .from(evolucaoAtendimento);

        return ResponseEntity
                .ok(detalhes);
    }
    @PutMapping("/{idEvolucaoAtendimento}")
    public ResponseEntity<EvolucaoAtendimentoRepresentation.Detalhes> atualizarEvolucaoAtendimento(
            @PathVariable Long idEvolucaoAtendimento,
            @RequestBody EvolucaoAtendimentoRepresentation.CriarOuAtualizar atualizar) {

        EvolucaoAtendimento evolucaoAtendimentoAtualizado =
                this.evolucaoAtendimentoService.atualizar(idEvolucaoAtendimento, atualizar);

        EvolucaoAtendimentoRepresentation.Detalhes detalhes =
                EvolucaoAtendimentoRepresentation.Detalhes
                        .from(evolucaoAtendimentoAtualizado);
        return ResponseEntity
                .ok(detalhes);
    }
}
