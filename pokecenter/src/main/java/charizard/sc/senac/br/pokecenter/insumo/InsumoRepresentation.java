package charizard.sc.senac.br.pokecenter.insumo;

import charizard.sc.senac.br.pokecenter.utils.TipoPokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public interface InsumoRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        @NotNull(message = "O campo Nome não pode ser nulo")
        @NotEmpty(message = "O campo Nome não pode ser vazio")
        private String nome;

    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String nome;
        public static Detalhes from(Insumo insumo) {
            return Detalhes.builder()
                    .id(insumo.getId())
                    .nome(insumo.getNome())
                    .build();
        }
    }
    @Data
    @Builder
    class Lista {
        private Long id;
        private String nome;
        private static Lista from (Insumo insumo){
            return Lista.builder()
                    .id(insumo.getId())
                    .nome(String.format("%s", insumo.getNome()))
                    .build();
        }

        public static List<Lista> from(List<Insumo> insumoList) {
            return insumoList
                    .stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
