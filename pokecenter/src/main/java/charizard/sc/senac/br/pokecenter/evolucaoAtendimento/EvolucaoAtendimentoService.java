package charizard.sc.senac.br.pokecenter.evolucaoAtendimento;

import charizard.sc.senac.br.pokecenter.atendimento.Atendimento;
import charizard.sc.senac.br.pokecenter.atendimento.AtendimentoService;
import charizard.sc.senac.br.pokecenter.atendimento.QAtendimento;
import charizard.sc.senac.br.pokecenter.etapaAtendimento.EtapaAtendimento;
import charizard.sc.senac.br.pokecenter.etapaAtendimento.EtapaAtendimentoService;
import charizard.sc.senac.br.pokecenter.etapaAtendimento.QEtapaAtendimento;
import charizard.sc.senac.br.pokecenter.exceptions.AtendimentoServiceException;
import charizard.sc.senac.br.pokecenter.exceptions.EvolucaoAtendimentoServiceException;
import charizard.sc.senac.br.pokecenter.exceptions.NotFoundException;
import charizard.sc.senac.br.pokecenter.leito.Leito;
import charizard.sc.senac.br.pokecenter.leito.LeitoService;
import charizard.sc.senac.br.pokecenter.pokemon.Pokemon;
import charizard.sc.senac.br.pokecenter.pokemon.PokemonService;
import charizard.sc.senac.br.pokecenter.utils.Etapa;
import charizard.sc.senac.br.pokecenter.utils.Situacao;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EvolucaoAtendimentoService {

    private EvolucaoAtendimentoRepository evolucaoAtendimentoRepository;

    public EvolucaoAtendimento criarEvolucaoAtendimento(EtapaAtendimentoService etapaatendimentoService,
                                                        Long idEtapaAtendimento,
//                                                      ProfissionalService profissionalService,
                                                        LeitoService leitoService,
                                                        EvolucaoAtendimentoRepresentation.CriarOuAtualizar criar) {

        if(this.verificaEvolucaoAtendimentoSaida(idEtapaAtendimento).isPresent()){
            throw new EvolucaoAtendimentoServiceException("Já houve a saída do Paciente, por gentileza avaliar.");
        }

        EtapaAtendimento etapaatendimento = etapaatendimentoService.buscarUmaEtapaAtendimento(idEtapaAtendimento);

        Leito leito = leitoService.buscarUmLeito(criar.getLeito());

//        Profissional profissional = profissinalService.buscarUmProfissional(criar.getProfissional());
        return this.evolucaoAtendimentoRepository.save(EvolucaoAtendimento.builder()
                .etapaatendimento(etapaatendimento)
                .observacao(criar.getObservacao())
                .dataEntrada(criar.getDataEntrada())
                .dataSaida(criar.getDataSaida())
                .leito(leito)
                .build());
    }
    public Page<EvolucaoAtendimento> buscarTodos(Pageable pageable) {
        return this.evolucaoAtendimentoRepository.findAll(pageable);
    }

    public Page<EvolucaoAtendimento> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.evolucaoAtendimentoRepository.findAll(filtroURI, pageable);
    }
    public EvolucaoAtendimento atualizar(Long idAtendimento, EvolucaoAtendimentoRepresentation.CriarOuAtualizar atualizar) {

        EvolucaoAtendimento evolucaoAtendimentoParaAtualizar = EvolucaoAtendimento.builder()
                .id(idAtendimento)
                .observacao(atualizar.getObservacao())
                .dataEntrada(atualizar.getDataEntrada())
                .dataSaida(atualizar.getDataSaida())
                .build();
        return this.evolucaoAtendimentoRepository.save(evolucaoAtendimentoParaAtualizar);
    }
    public EvolucaoAtendimento buscarUmaEvolucaoAtendimento(Long idEvolucaoAtendimento) {
        return this.getEvolucaoAtendimento(idEvolucaoAtendimento);
    }

    private EvolucaoAtendimento getEvolucaoAtendimento(Long idEvolucaoAtendimento){
        Optional<EvolucaoAtendimento> evolucaoAtendimentoAtual =
                this.evolucaoAtendimentoRepository.findById(idEvolucaoAtendimento);
        if (evolucaoAtendimentoAtual.isPresent()) {
            return evolucaoAtendimentoAtual.get();
        }else {
            throw new NotFoundException("Evolucao do Atendimento não encontrado");
        }
    }
    private Optional<EvolucaoAtendimento> verificaEvolucaoAtendimentoSaida(Long idEtapaAtendimento){
        BooleanExpression filter = QEvolucaoAtendimento.evolucaoAtendimento.etapaatendimento.etapa.eq(Etapa.S)
                .and(QEvolucaoAtendimento.evolucaoAtendimento.etapaatendimento.id.eq(idEtapaAtendimento));

        return this.evolucaoAtendimentoRepository.findOne(filter);
    }

}
