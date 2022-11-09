package charizard.sc.senac.br.pokecenter.paciente;

import charizard.sc.senac.br.pokecenter.atendimento.Atendimento;
import charizard.sc.senac.br.pokecenter.atendimento.AtendimentoRepresentation;
import charizard.sc.senac.br.pokecenter.pokemon.Pokemon;
import charizard.sc.senac.br.pokecenter.pokemon.PokemonRepresentation;
import charizard.sc.senac.br.pokecenter.treinador.Treinador;
import charizard.sc.senac.br.pokecenter.treinador.TreinadorRepresentation;
import charizard.sc.senac.br.pokecenter.utils.Genero;
import charizard.sc.senac.br.pokecenter.utils.Situacao;
import charizard.sc.senac.br.pokecenter.utils.TipoSanguineo;
import com.fasterxml.jackson.annotation.JsonInclude;
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
        private PokemonRepresentation.Detalhes pokemon;
        public static Padrao from(Paciente paciente) {
            return Padrao.builder()
                    .id(paciente.getId())
                    .nome(paciente.getNome())
                    .tipoSanguineo(paciente.getTipoSanguineo())
                    .dataNascimento(paciente.getDataNascimento())
                    .alergia(paciente.getAlergia())
                    .genero(paciente.getGenero())
                    .pokemon(PokemonRepresentation.Detalhes.from(paciente.getPokemon()))
                    .build();
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
        private Long pokemon;

    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;
        private String nome;
        private TipoSanguineo tipoSanguineo;
        private Date dataNascimento;
        private String alergia;
        private Genero genero;
        private PokemonRepresentation.Detalhes pokemon;
        private static Lista from(Paciente paciente) {
            return Lista.builder()
                    .id(paciente.getId())
                    .nome(paciente.getNome())
                    .tipoSanguineo(paciente.getTipoSanguineo())
                    .dataNascimento(paciente.getDataNascimento())
                    .alergia(paciente.getAlergia())
                    .genero(paciente.getGenero())
                    .pokemon(PokemonRepresentation.Detalhes.from(paciente.getPokemon()))
                    .build();
        }

        public static List<PacienteRepresentation.Lista> from(List<Paciente> pacienteList) {
            return pacienteList
                    .stream()
                    .map(PacienteRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }


}
