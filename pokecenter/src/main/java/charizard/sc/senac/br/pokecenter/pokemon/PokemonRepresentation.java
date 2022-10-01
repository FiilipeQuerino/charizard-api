package charizard.sc.senac.br.pokecenter.pokemon;

import charizard.sc.senac.br.pokecenter.utils.TipoPokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public interface PokemonRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        @NotNull(message = "O campo Nome não pode ser nulo")
        @NotEmpty(message = "O campo Nome não pode ser vazio")
        private String nome;
        private TipoPokemon tipoPokemon;
    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String nome;
        private TipoPokemon tipoPokemon;
        public static PokemonRepresentation.Detalhes from(Pokemon pokemon) {
            return PokemonRepresentation.Detalhes.builder()
                    .id(pokemon.getId())
                    .nome(pokemon.getNome())
                    .tipoPokemon(pokemon.getTipoPokemon())
                    .build();
        }
    }
    @Data
    @Builder
    class Lista {
        private Long id;
        private String nome;
        private TipoPokemon tipoPokemon;
        private static PokemonRepresentation.Lista from (Pokemon pokemon){
            return PokemonRepresentation.Lista.builder()
                    .id(pokemon.getId())
                    .nome(String.format("%s", pokemon.getNome()))
                    .tipoPokemon(pokemon.getTipoPokemon())
                    .build();
        }

        public static List<PokemonRepresentation.Lista> from(List<Pokemon> pokemonList) {
            return pokemonList
                    .stream()
                    .map(PokemonRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
