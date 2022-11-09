package charizard.sc.senac.br.pokecenter.atendimento;

import charizard.sc.senac.br.pokecenter.paciente.Paciente;
import charizard.sc.senac.br.pokecenter.paciente.PacienteRepresentation;
import charizard.sc.senac.br.pokecenter.treinador.Treinador;
import charizard.sc.senac.br.pokecenter.treinador.TreinadorRepresentation;
import charizard.sc.senac.br.pokecenter.utils.Genero;
import charizard.sc.senac.br.pokecenter.utils.Situacao;
import charizard.sc.senac.br.pokecenter.utils.TipoPokemon;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface AtendimentoRepresentation {
    //    idPaciente: fk paciente,
//    situacao: enum (ANDAMENTO/FINALIZADO);
//    data_inicio: Date;
//    data_fim: Date;
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        private Date dataInicio;
        private Date dataFim;
        @NotNull(message = "O campo Situação não pode ser nulo")
        private Situacao situacao;
    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private Date dataInicio;
        private Date dataFim;
        private Situacao situacao;
//        private PacienteAtendimento pacienteAtendimento;

        public static Detalhes from(Atendimento atendimento) {
            return Detalhes.builder()
                    .id(atendimento.getId())
                    .dataInicio(atendimento.getDataInicio())
                    .dataFim(atendimento.getDataFim())
                    .situacao(atendimento.getSituacao())
//                    .pacienteAtendimento(PacienteAtendimento.from(atendimento.getPaciente())
                    .build();
        }
    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;
        private Date dataInicio;
        private Date dataFim;
        private Situacao situacao;
        private PacienteRepresentation.Padrao paciente;
        private static AtendimentoRepresentation.Lista from(Atendimento atendimento) {
            return AtendimentoRepresentation.Lista.builder()
                            .id(atendimento.getId())
                            .dataInicio(atendimento.getDataInicio())
                            .dataFim(atendimento.getDataFim())
                            .situacao(atendimento.getSituacao())
                            .paciente(PacienteRepresentation.Padrao.from(atendimento.getPaciente()))
                            .build();
        }

        public static List<AtendimentoRepresentation.Lista> from(List<Atendimento> atendimentoList) {
            return atendimentoList
                    .stream()
                    .map(AtendimentoRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }

    }
}
