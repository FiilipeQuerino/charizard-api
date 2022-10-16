package charizard.sc.senac.br.pokecenter.leito;



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
@RequestMapping("api/leito")
@CrossOrigin("*")
@AllArgsConstructor
public class LeitoController {

    private LeitoService leitoService;

    @PostMapping("/")
    public ResponseEntity<LeitoRepresentation.Detalhes> createLeito(
            @RequestBody @Valid LeitoRepresentation.CriarOuAtualizar criar){

        Leito leito = this.leitoService.criarLeito(criar);

        LeitoRepresentation.Detalhes detalhes =
                LeitoRepresentation.Detalhes.from(leito);

        return ResponseEntity
                .ok(detalhes);
    }
    @GetMapping("/")
    public ResponseEntity<List<LeitoRepresentation.Lista>> buscarLeito(
            @QuerydslPredicate(root = Leito.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name="paginaSelecionada", defaultValue = "0") int paginaSelecionada) {

        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina); //Pesquisar o que/como usar o sort

        Page<Leito> leitoList = Objects.isNull(filtroURI)?
                this.leitoService.buscarTodos(pageable):
                this.leitoService.buscarTodos(filtroURI, pageable);

        List<LeitoRepresentation.Lista> listaFinal = LeitoRepresentation.Lista.from(leitoList.getContent());
        return ResponseEntity.ok(listaFinal);
    }
    @PutMapping("/{idLeito}")
    public ResponseEntity<LeitoRepresentation.Detalhes> atualizarLeito(@PathVariable Long idLeito, @RequestBody LeitoRepresentation.CriarOuAtualizar atualizar) {
        Leito leitoAtualizado = this.leitoService.atualizar(idLeito, atualizar);
        LeitoRepresentation.Detalhes detalhes = LeitoRepresentation.Detalhes.from(leitoAtualizado);

        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/{idLeito}")
    public ResponseEntity<LeitoRepresentation.Detalhes> buscarUmLeito(
            @PathVariable Long idLeito) {
        Leito leito = this.leitoService.buscarUmLeito(idLeito);

        LeitoRepresentation.Detalhes detalhes =
                LeitoRepresentation.Detalhes
                        .from(leito);
        return ResponseEntity
                .ok(detalhes);
    }
}
