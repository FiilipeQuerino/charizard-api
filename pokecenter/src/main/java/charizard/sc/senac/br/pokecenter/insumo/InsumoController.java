package charizard.sc.senac.br.pokecenter.insumo;



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
@RequestMapping("api/insumo")
@CrossOrigin("*")
@AllArgsConstructor
public class InsumoController {

    private InsumoService insumoService;

    @PostMapping("/")
    public ResponseEntity<InsumoRepresentation.Detalhes> createInsumo(
            @RequestBody @Valid InsumoRepresentation.CriarOuAtualizar criar){

        Insumo insumo = this.insumoService.criarInsumo(criar);

        InsumoRepresentation.Detalhes detalhes =
                InsumoRepresentation.Detalhes.from(insumo);

        return ResponseEntity
                .ok(detalhes);
    }
    @GetMapping("/")
    public ResponseEntity<List<InsumoRepresentation.Lista>> buscarInsumo(
            @QuerydslPredicate(root = Insumo.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name="paginaSelecionada", defaultValue = "0") int paginaSelecionada) {

        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina); //Pesquisar o que/como usar o sort

        Page<Insumo> insumoList = Objects.isNull(filtroURI)?
                this.insumoService.buscarTodos(pageable):
                this.insumoService.buscarTodos(filtroURI, pageable);

        List<InsumoRepresentation.Lista> listaFinal = InsumoRepresentation.Lista.from(insumoList.getContent());
        return ResponseEntity.ok(listaFinal);
    }
    @PutMapping("/{idInsumo}")
    public ResponseEntity<InsumoRepresentation.Detalhes> atualizarInsumo(@PathVariable Long idInsumo, @RequestBody InsumoRepresentation.CriarOuAtualizar atualizar) {
        Insumo insumoAtualizado = this.insumoService.atualizar(idInsumo, atualizar);
        InsumoRepresentation.Detalhes detalhes = InsumoRepresentation.Detalhes.from(insumoAtualizado);

        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/{idInsumo}")
    public ResponseEntity<InsumoRepresentation.Detalhes> buscarUmInsumo(
            @PathVariable Long idInsumo) {
        Insumo insumo = this.insumoService.buscarUmInsumo(idInsumo);

        InsumoRepresentation.Detalhes detalhes =
                InsumoRepresentation.Detalhes
                        .from(insumo);
        return ResponseEntity
                .ok(detalhes);
    }
}
