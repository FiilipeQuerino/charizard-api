package charizard.sc.senac.br.pokecenter.pokemon;



import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/pokemon")
@CrossOrigin("*")
@AllArgsConstructor
public class PokemonController {

    private PokemonService pokemonService;

    @PostMapping("/")
    public ResponseEntity<PokemonRepresentation.Detalhes> createPokemon(
            @RequestBody @Valid PokemonRepresentation.CriarOuAtualizar criar){

        Pokemon pokemon = this.pokemonService.criarPokemon(criar);

        PokemonRepresentation.Detalhes detalhes =
                PokemonRepresentation.Detalhes.from(pokemon);

        return ResponseEntity
                .ok(detalhes);
    }
    @GetMapping("/")
    public ResponseEntity<List<PokemonRepresentation.Lista>> buscarPokemon(
            @QuerydslPredicate(root = Pokemon.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name="paginaSelecionada", defaultValue = "0") int paginaSelecionada) {

        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina); //Pesquisar o que/como usar o sort

        Page<Pokemon> pokemonList = Objects.isNull(filtroURI)?
                this.pokemonService.buscarTodos(pageable):
                this.pokemonService.buscarTodos(filtroURI, pageable);

        List<PokemonRepresentation.Lista> listaFinal = PokemonRepresentation.Lista.from(pokemonList.getContent());
        return ResponseEntity.ok(listaFinal);
    }
    @PutMapping("/{idPokemon}")
    public ResponseEntity<PokemonRepresentation.Detalhes> atualizarPokemon(@PathVariable Long idPokemon, @RequestBody PokemonRepresentation.CriarOuAtualizar atualizar) {
        Pokemon pokemonAtualizado = this.pokemonService.atualizar(idPokemon, atualizar);
        PokemonRepresentation.Detalhes detalhes = PokemonRepresentation.Detalhes.from(pokemonAtualizado);

        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/{idPokemon}")
    public ResponseEntity<PokemonRepresentation.Detalhes> buscarUmPokemon(
            @PathVariable Long idPokemon) {
        Pokemon pokemon = this.pokemonService.buscarUmPokemon(idPokemon);

        PokemonRepresentation.Detalhes detalhes =
                PokemonRepresentation.Detalhes
                        .from(pokemon);
        return ResponseEntity
                .ok(detalhes);
    }
}
