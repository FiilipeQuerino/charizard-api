package charizard.sc.senac.br.pokecenter.pokemon;

import charizard.sc.senac.br.pokecenter.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j //biblioteca de log (ele indica que pode ser utilizado Log´s, igual o do console)
public class PokemonService {

    private PokemonRepository pokemonRepository;

    public Pokemon criarPokemon(PokemonRepresentation.CriarOuAtualizar criar) {

        return this.pokemonRepository.save(Pokemon.builder()
                .nome(criar.getNome())
                .tipoPokemon(criar.getTipoPokemon())
                .build());
    }
    public Page<Pokemon> buscarTodos(Pageable pageable) {
        return this.pokemonRepository.findAll(pageable);
    }

    public Page<Pokemon> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.pokemonRepository.findAll(filtroURI, pageable);
    }
    public Pokemon atualizar(Long idPokemon, PokemonRepresentation.CriarOuAtualizar atualizar) {

        Pokemon pokemonParaAtualizar = Pokemon.builder()
                .id(idPokemon)
                .nome((atualizar.getNome()))
                .tipoPokemon(atualizar.getTipoPokemon())
                .build();
        return this.pokemonRepository.save(pokemonParaAtualizar);
    }
    public Pokemon buscarUmPokemon(Long idPokemon) {
        return this.getPokemon(idPokemon);
    }

    private Pokemon getPokemon(Long idPokemon){
        Optional<Pokemon> pokemonAtual =
                this.pokemonRepository.findById(idPokemon);
        if (pokemonAtual.isPresent()) {
            return pokemonAtual.get();
        }else {
            throw new NotFoundException("Pokemon não encontrado");
        }
    }
}
