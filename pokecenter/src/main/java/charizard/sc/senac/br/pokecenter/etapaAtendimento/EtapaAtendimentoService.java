package charizard.sc.senac.br.pokecenter.etapaAtendimento;

import charizard.sc.senac.br.pokecenter.atendimento.Atendimento;
import charizard.sc.senac.br.pokecenter.atendimento.AtendimentoService;
import charizard.sc.senac.br.pokecenter.atendimento.QAtendimento;
import charizard.sc.senac.br.pokecenter.exceptions.AtendimentoServiceException;
import charizard.sc.senac.br.pokecenter.exceptions.EtapaAtendimentoServiceException;
import charizard.sc.senac.br.pokecenter.exceptions.NotFoundException;
import charizard.sc.senac.br.pokecenter.paciente.Paciente;
import charizard.sc.senac.br.pokecenter.paciente.PacienteService;
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
public class EtapaAtendimentoService {

    private EtapaAtendimentoRepository etapaAtendimentoRepository;

    public EtapaAtendimento criarEtapaAtendimento(AtendimentoService atendimentoService,
                                                  Long idAtendimento,
                                                  EtapaAtendimentoRepresentation.CriarOuAtualizar criar) {

        if(this.verificaAtendimentoFinalizado(idAtendimento).isPresent()){
            throw new EtapaAtendimentoServiceException("Atendimento consta como finalizado, por gentileza avaliar.");
        }

        Atendimento atendimento = atendimentoService.buscarUmAtendimento(idAtendimento);

        return this.etapaAtendimentoRepository.save(EtapaAtendimento.builder()
                .atendimento(atendimento)
                .dataEntrada(criar.getDataEntrada())
                .dataSaida(criar.getDataSaida())
                .etapa(criar.getEtapa())
                .build());
    }
    public Page<EtapaAtendimento> buscarTodos(Pageable pageable) {
        return this.etapaAtendimentoRepository.findAll(pageable);
    }

    public Page<EtapaAtendimento> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.etapaAtendimentoRepository.findAll(filtroURI, pageable);
    }
    public EtapaAtendimento atualizar(Long idAtendimento, EtapaAtendimentoRepresentation.CriarOuAtualizar atualizar) {

        EtapaAtendimento etapaAtendimentoParaAtualizar = EtapaAtendimento.builder()
                .id(idAtendimento)
                .dataEntrada(atualizar.getDataEntrada())
                .dataSaida(atualizar.getDataSaida())
                .etapa(atualizar.getEtapa())
                .build();
        return this.etapaAtendimentoRepository.save(etapaAtendimentoParaAtualizar);
    }
    public EtapaAtendimento buscarUmaEtapaAtendimento(Long idEtapaAtendimento) {
        return this.getEtapaAtendimento(idEtapaAtendimento);
    }

    private EtapaAtendimento getEtapaAtendimento(Long idEtapaAtendimento){
        Optional<EtapaAtendimento> etapaAtendimentoAtual =
                this.etapaAtendimentoRepository.findById(idEtapaAtendimento);
        if (etapaAtendimentoAtual.isPresent()) {
            return etapaAtendimentoAtual.get();
        }else {
            throw new NotFoundException("Etapa Atendimento não encontrado");
        }
    }
    private Optional<EtapaAtendimento> verificaAtendimentoFinalizado(Long idAtendimento){
        BooleanExpression filter = QAtendimento.atendimento.situacao.eq(Situacao.F)
                .and(QAtendimento.atendimento.id.eq(idAtendimento));

        return this.etapaAtendimentoRepository.findOne(filter);
    }
}
