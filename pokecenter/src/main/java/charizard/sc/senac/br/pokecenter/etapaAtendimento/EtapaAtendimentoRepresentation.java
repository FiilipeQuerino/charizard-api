package charizard.sc.senac.br.pokecenter.etapaAtendimento;

import charizard.sc.senac.br.pokecenter.atendimento.Atendimento;
import charizard.sc.senac.br.pokecenter.atendimento.AtendimentoRepresentation;
import charizard.sc.senac.br.pokecenter.paciente.PacienteRepresentation;
import charizard.sc.senac.br.pokecenter.utils.Etapa;
import charizard.sc.senac.br.pokecenter.utils.Situacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface EtapaAtendimentoRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        private Date dataEntrada;
        private Date dataSaida;
        @NotNull(message = "O campo Situação não pode ser nulo")
        private Etapa etapa;
    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private Date dataEntrada;
        private Date dataSaida;
        private Etapa etapa;

        public static Detalhes from(EtapaAtendimento etapaAtendimento) {
            return Detalhes.builder()
                    .id(etapaAtendimento.getId())
                    .dataEntrada(etapaAtendimento.getDataEntrada())
                    .dataSaida(etapaAtendimento.getDataSaida())
                    .etapa(etapaAtendimento.getEtapa())
                    .build();
        }
    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;
        private Date dataEntrada;
        private Date dataSaida;
        private Etapa etapa;
        private AtendimentoRepresentation.Detalhes atendimento;
        private static Lista from(EtapaAtendimento etapaAtendimento) {
            return Lista.builder()
                            .id(etapaAtendimento.getId())
                            .dataEntrada(etapaAtendimento.getDataEntrada())
                            .dataSaida(etapaAtendimento.getDataSaida())
                            .etapa(etapaAtendimento.getEtapa())
                            .atendimento(AtendimentoRepresentation.Detalhes.from(etapaAtendimento.getAtendimento()))
                            .build();
        }

        public static List<Lista> from(List<EtapaAtendimento> etapaAtendimentoList) {
            return etapaAtendimentoList
                    .stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }

    }
}
