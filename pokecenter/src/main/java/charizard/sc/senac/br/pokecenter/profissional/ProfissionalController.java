package charizard.sc.senac.br.pokecenter.profissional;


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
@RequestMapping("api/profissional")
@CrossOrigin("*")
@AllArgsConstructor
public class ProfissionalController {
    private ProfissionalService profissionalService;
    @PostMapping("/")
    public ResponseEntity<ProfissionalRepresentation.Detalhes> createProfissional(
            @RequestBody @Valid ProfissionalRepresentation.criarOuAtualizar criar){

        Profissional profissional = this.profissionalService.criarProfissional(criar);

        ProfissionalRepresentation.Detalhes detalhes =
                ProfissionalRepresentation.Detalhes.from(profissional);


        return ResponseEntity.ok(detalhes);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProfissionalRepresentation.Lista>> buscarProfissional(
            @QuerydslPredicate(root = Profissional.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name= "paginaSelecionada", defaultValue = "0") int paginaSelecionada){

            Pageable pageable = PageRequest.of(paginaSelecionada,tamanhoPagina);

            Page<Profissional> profissionalList = Objects.isNull(filtroURI)?
                    this.profissionalService.buscarTodos(pageable):
                    this.profissionalService.buscarTodos(filtroURI,pageable);

        List<ProfissionalRepresentation.Lista> listaFinal = ProfissionalRepresentation.Lista.from(profissionalList.getContent());
        return ResponseEntity.ok(listaFinal);

    }

    @PutMapping("/{idProfissional}")
    public ResponseEntity<ProfissionalRepresentation.Detalhes> atualizarProfissional(@PathVariable Long idProfissional, @RequestBody ProfissionalRepresentation.criarOuAtualizar atualizar){
        Profissional profissionalAtualizado = this.profissionalService.atualizar(idProfissional,atualizar);
        ProfissionalRepresentation.Detalhes detalhes = ProfissionalRepresentation.Detalhes.from(profissionalAtualizado);


        return ResponseEntity.ok(detalhes);
    }

    @GetMapping("/{idProfissional}")
    public ResponseEntity<ProfissionalRepresentation.Detalhes> buscarUmProfissional(
            @PathVariable Long idProfissional) {
        Profissional profissional = this.profissionalService.buscarUmProfissional(idProfissional);

        ProfissionalRepresentation.Detalhes detalhes =
                ProfissionalRepresentation.Detalhes
                        .from(profissional);
        return ResponseEntity
                .ok(detalhes);
    }
}





