package charizard.sc.senac.br.pokecenter.paciente;

import charizard.sc.senac.br.pokecenter.pokemon.Pokemon;
import charizard.sc.senac.br.pokecenter.treinador.Treinador;
import charizard.sc.senac.br.pokecenter.treinador.TreinadorRepresentation;
import charizard.sc.senac.br.pokecenter.utils.Genero;
import charizard.sc.senac.br.pokecenter.utils.TipoSanguineo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface PacienteRepresentation {
    @Builder
    @Data
    class Padrao {
        private Long id;
        private String nome;
        private TipoSanguineo tipoSanguineo;
        private Date dataNascimento;
        private String alergia;
        private Genero genero;
//        private TreinadorPaciente treinador;
//        private PokemonPaciente pokemon;
        public static Padrao from(Paciente paciente) {
            return Padrao.builder()
                    .id(paciente.getId())
                    .nome(paciente.getNome())
                    .tipoSanguineo(paciente.getTipoSanguineo())
                    .dataNascimento(paciente.getDataNascimento())
                    .alergia(paciente.getAlergia())
                    .genero(paciente.getGenero())
//                    .treinador(TreinadorPaciente.from(paciente.getTreinador()))
//                    .pokemon(PokemonPaciente.from(paciente.getPokemon()))
                    .build();
        }
        public static List<Padrao> from(List<Paciente> pacienteList) {
            return pacienteList.stream()
                    .map(Padrao::from)
                    .collect(Collectors.toList());
        }
    }
//    @Builder
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    class TreinadorPaciente{
//        private Long id;
//        private String nome;
//        public static TreinadorPaciente from(Treinador treinador){
//            return TreinadorPaciente.builder()
//                    .id(treinador.getId())
//                    .nome(treinador.getNome())
//                    .build();
//        }
//    }
//    @Builder
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    class PokemonPaciente{
//        private Long id;
//        private String nome;
//        public static PokemonPaciente from(Pokemon pokemon){
//            return PokemonPaciente.builder()
//                    .id(pokemon.getId())
//                    .nome(pokemon.getNome())
//                    .build();
//        }
//    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        //        @NotNull(message = "O campo Nome não pode ser nulo")
//        @NotEmpty(message = "O campo Nome não pode ser vazio")
//        private Treinador treinador;
        private Pokemon pokemon;
        private String nome;
        private TipoSanguineo tipoSanguineo;
        private Date dataNascimento;
        private String alergia;
        private Genero genero;

    }

}
