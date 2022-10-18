package charizard.sc.senac.br.pokecenter.profissional;


import charizard.sc.senac.br.pokecenter.utils.TipoProfissional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public interface ProfissionalRepresentation {


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class criarOuAtualizar{

        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;
        private TipoProfissional tipoProfissional;

    }

    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String nome;
        private TipoProfissional tipoProfissional;
        public static Detalhes from(Profissional profissional) {
            return Detalhes.builder()
                    .id(profissional.getId())
                    .nome(profissional.getNome())
                    .tipoProfissional(profissional.getTipoProfissional())
                    .build();
        }
    }


    @Data
    @Builder
    class Lista {
        private Long id;
        private String nome;
        private TipoProfissional tipoProfissional;
        private static ProfissionalRepresentation.Lista from (Profissional profissional){
            return Lista.builder()
                    .id(profissional.getId())
                    .nome(String.format("%s", profissional.getNome()))
                    .tipoProfissional(profissional.getTipoProfissional())
                    .build();
        }
        public static List<ProfissionalRepresentation.Lista> from(List<Profissional> profissionalList) {
            return profissionalList
                    .stream()
                    .map(ProfissionalRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }


    }
}


