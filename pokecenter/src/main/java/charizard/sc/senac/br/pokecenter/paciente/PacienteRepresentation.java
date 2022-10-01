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
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        @NotNull(message = "O campo Nome não pode ser nulo")
        @NotEmpty(message = "O campo Nome não pode ser vazio")
        private String nome;
        private TipoSanguineo tipoSanguineo;
        private Date dataNascimento;
        private String alergia;
        private Genero genero;
        private Treinador treinador;
        private Pokemon pokemon;

    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String nome;
        private TipoSanguineo tipoSanguineo;
        private Date dataNascimento;
        private String alergia;
        private Genero genero;
        private Treinador treinador;
        private Pokemon pokemon;
        public static Detalhes from(Paciente paciente) {
            return Detalhes.builder()
                    .id(paciente.getId())
                    .nome(paciente.getNome())
                    .tipoSanguineo(paciente.getTipoSanguineo())
                    .dataNascimento(paciente.getDataNascimento())
                    .alergia(paciente.getAlergia())
                    .genero(paciente.getGenero())
                    .treinador(paciente.getTreinador())
                    .pokemon(paciente.getPokemon())
                    .build();
        }
    }
    @Data
    @Builder
    class Lista {
        private Long id;
        private String nome;
        private TipoSanguineo tipoSanguineo;
        private Date dataNascimento;
        private String alergia;
        private Genero genero;
        private Treinador treinador;
        private Pokemon pokemon;
        private static Lista from (Paciente paciente){
            return Lista.builder()
                    .id(paciente.getId())
                    .nome(String.format("%s", paciente.getNome()))
                    .tipoSanguineo(paciente.getTipoSanguineo())
                    .dataNascimento(paciente.getDataNascimento())
                    .alergia(paciente.getAlergia())
                    .genero(paciente.getGenero())
                    .treinador(paciente.getTreinador())
                    .pokemon(paciente.getPokemon())
                    .build();
        }

        public static List<Lista> from(List<Paciente> pacienteList) {
            return pacienteList
                    .stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
