package charizard.sc.senac.br.pokecenter.evolucaoAtendimento;

import charizard.sc.senac.br.pokecenter.atendimento.AtendimentoRepresentation;
import charizard.sc.senac.br.pokecenter.etapaAtendimento.EtapaAtendimento;
import charizard.sc.senac.br.pokecenter.etapaAtendimento.EtapaAtendimentoRepresentation;
import charizard.sc.senac.br.pokecenter.leito.Leito;
import charizard.sc.senac.br.pokecenter.leito.LeitoRepresentation;
import charizard.sc.senac.br.pokecenter.pokemon.PokemonRepresentation;
import charizard.sc.senac.br.pokecenter.utils.Etapa;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface EvolucaoAtendimentoRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar {

        private String observacao;
        private Date dataEntrada;
        private Date dataSaida;
        private Long leito;
        //        private Long profissional;
    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String observacao;
        private Date dataEntrada;
        private Date dataSaida;
        private LeitoRepresentation.Detalhes leito;
//        private ProfissionalRepresentation.Detalhes profissional;
        private EtapaAtendimentoRepresentation.Detalhes etapaAtendimento;

        public static Detalhes from(EvolucaoAtendimento evolucaoAtendimento) {
            return Detalhes.builder()
                    .id(evolucaoAtendimento.getId())
                    .observacao(evolucaoAtendimento.getObservacao())
                    .dataEntrada(evolucaoAtendimento.getDataEntrada())
                    .dataSaida(evolucaoAtendimento.getDataSaida())
                    .leito(LeitoRepresentation.Detalhes.from(evolucaoAtendimento.getLeito()))
//                    .profissional(ProfissionalRepresentation.Detalhes.from(evolucaoAtendimento.getProfissional()))
                    .etapaAtendimento(EtapaAtendimentoRepresentation.Detalhes.from(evolucaoAtendimento.getEtapaatendimento()))
                    .build();
        }
    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;
        private String observacao;
        private Date dataEntrada;
        private Date dataSaida;
        private EtapaAtendimentoRepresentation.Detalhes etapaatendimento;
        private LeitoRepresentation.Detalhes leito;
//        private ProfissionalRepresentation.Detalhes profissional;
        private static Lista from(EvolucaoAtendimento evolucaoAtendimento) {
            return Lista.builder()
                            .id(evolucaoAtendimento.getId())
                            .observacao(evolucaoAtendimento.getObservacao())
                            .dataEntrada(evolucaoAtendimento.getDataEntrada())
                            .dataSaida(evolucaoAtendimento.getDataSaida())
                            .leito(LeitoRepresentation.Detalhes.from(evolucaoAtendimento.getLeito()))
//                            .profissional(ProfissionalRepresentation.Detalhes.from(evolucaoAtendimento.getProfissional()))
                            .etapaatendimento(EtapaAtendimentoRepresentation.Detalhes.from(evolucaoAtendimento.getEtapaatendimento()))
                            .build();
        }

        public static List<Lista> from(List<EvolucaoAtendimento> evolucaoAtendimentoList) {
            return evolucaoAtendimentoList
                    .stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }

    }
}
