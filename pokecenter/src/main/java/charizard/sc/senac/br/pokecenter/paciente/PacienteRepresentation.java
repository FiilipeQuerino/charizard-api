package charizard.sc.senac.br.pokecenter.paciente;

import charizard.sc.senac.br.pokecenter.pokemon.Pokemon;
import charizard.sc.senac.br.pokecenter.treinador.Treinador;
import charizard.sc.senac.br.pokecenter.utils.Genero;
import charizard.sc.senac.br.pokecenter.utils.TipoPokemon;
import charizard.sc.senac.br.pokecenter.utils.TipoSanguineo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface PacienteRepresentation {
    @Builder
    @Data
    class Padrao {
        private Long id;
        //        @NotNull(message = "O campo Nome não pode ser nulo")
//        @NotEmpty(message = "O campo Nome não pode ser vazio")
        private String nome;
        private TipoSanguineo tipoSanguineo;
        private Date dataNascimento;
        private String alergia;
        private Genero genero;
        //private Treinador treinador;


        public static Padrao from(Paciente paciente) {
            return Padrao.builder()
                    .id(paciente.getId())
                    .nome(paciente.getNome())
                    .tipoSanguineo(paciente.getTipoSanguineo())
                    .dataNascimento(paciente.getDataNascimento())
                    .alergia(paciente.getAlergia())
                    .genero(paciente.getGenero())
                    .build();
        }

        public static List<Padrao> from(List<Paciente> pacienteList) {
            return pacienteList.stream()
                    .map(Padrao::from)
                    .collect(Collectors.toList());
        }

    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        //        @NotNull(message = "O campo Nome não pode ser nulo")
//        @NotEmpty(message = "O campo Nome não pode ser vazio")
        private String nome;
        private TipoSanguineo tipoSanguineo;
        private Date dataNascimento;
        private String alergia;
        private Genero genero;

    }
}
