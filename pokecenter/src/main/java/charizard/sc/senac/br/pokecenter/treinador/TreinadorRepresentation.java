package charizard.sc.senac.br.pokecenter.treinador;


import charizard.sc.senac.br.pokecenter.utils.Genero;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface TreinadorRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        @NotNull(message = "O campo Nome não pode ser nulo")
        @NotEmpty(message = "O campo Nome não pode ser vazio")
        private String nome;
//        @NotNull(message = "O campo Data de Nascimento não pode ser nulo")
//        @NotEmpty(message = "O campo Data de Nascimento não pode ser vazio")
        private Date dataNascimento;
//        @NotNull(message = "O campo Telefone não pode ser nulo")
//        @NotEmpty(message = "O campo Telefone não pode ser vazio")
        private int telefone;

        private Genero genero;
    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String nome;
        private Date dataNascimento;
        private int telefone;
        private Genero genero;

        public static Detalhes from(Treinador treinador) {
            return Detalhes.builder()
                    .id(treinador.getId())
                    .nome(treinador.getNome())
                    .dataNascimento(treinador.getDataNascimento())
                    .telefone(treinador.getTelefone())
                    .genero(treinador.getGenero())
                    .build();
        }
    }
    @Data
    @Builder
    class Lista {
        private Long id;
        private String nome;
        private Date dataNascimento;
        private int telefone;
        private Genero genero;

        private static Lista from (Treinador treinador){
            return Lista.builder()
                            .id(treinador.getId())
                            .nome(String.format("%s", treinador.getNome()))
                            .dataNascimento(treinador.getDataNascimento())
                            .telefone(treinador.getTelefone())
                            .genero(treinador.getGenero())
                            .build();
        }

        public static List<Lista> from(List<Treinador> treinadorList) {
            return treinadorList
                    .stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }
    }

}
