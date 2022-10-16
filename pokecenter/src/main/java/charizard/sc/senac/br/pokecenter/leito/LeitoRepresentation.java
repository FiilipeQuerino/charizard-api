package charizard.sc.senac.br.pokecenter.leito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public interface LeitoRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        @NotNull(message = "O campo Identificador não pode ser nulo")
        @NotEmpty(message = "O campo Identificador não pode ser vazio")
        private String identificador;

    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String identificador;
        public static Detalhes from(Leito leito) {
            return Detalhes.builder()
                    .id(leito.getId())
                    .identificador(leito.getIdentificador())
                    .build();
        }
    }
    @Data
    @Builder
    class Lista {
        private Long id;
        private String identificador;
        private static Lista from (Leito leito){
            return Lista.builder()
                    .id(leito.getId())
                    .identificador(String.format("%s", leito.getIdentificador()))
                    .build();
        }

        public static List<Lista> from(List<Leito> leitoList) {
            return leitoList
                    .stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
