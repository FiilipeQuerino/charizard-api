package charizard.sc.senac.br.pokecenter.atendimento;

import charizard.sc.senac.br.pokecenter.exceptions.NotFoundException;
import charizard.sc.senac.br.pokecenter.paciente.Paciente;
import charizard.sc.senac.br.pokecenter.paciente.PacienteService;
import charizard.sc.senac.br.pokecenter.treinador.Treinador;
import charizard.sc.senac.br.pokecenter.treinador.TreinadorService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AtendimentoService {

    private AtendimentoRepository atendimentoRepository;

    public Atendimento criarAtendimento(PacienteService pacienteService,
                                        Long idPaciente,
                                        AtendimentoRepresentation.CriarOuAtualizar criar) {

        Paciente paciente = pacienteService.buscarUmPaciente(idPaciente);

        return this.atendimentoRepository.save(Atendimento.builder()
//                .paciente(paciente)
                .dataInicio(criar.getDataInicio())
                .dataFim(criar.getDataFim())
                .situacao(criar.getSituacao())
                .build());
    }
    public Page<Atendimento> buscarTodos(Pageable pageable) {
        return this.atendimentoRepository.findAll(pageable);
    }

    public Page<Atendimento> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.atendimentoRepository.findAll(filtroURI, pageable);
    }
    public Atendimento atualizar(Long idAtendimento, AtendimentoRepresentation.CriarOuAtualizar atualizar) {

        Atendimento atendimentoParaAtualizar = Atendimento.builder()
                .id(idAtendimento)
                .dataInicio(atualizar.getDataInicio())
                .dataFim(atualizar.getDataFim())
                .situacao(atualizar.getSituacao())
                .build();
        return this.atendimentoRepository.save(atendimentoParaAtualizar);
    }
    public Atendimento buscarUmAtendimento(Long idAtendimento) {
        return this.getAtendimento(idAtendimento);
    }

    private Atendimento getAtendimento(Long idAtendimento){
        Optional<Atendimento> atendimentoAtual =
                this.atendimentoRepository.findById(idAtendimento);
        if (atendimentoAtual.isPresent()) {
            return atendimentoAtual.get();
        }else {
            throw new NotFoundException("Atendimento não encontrado");
        }
    }
}
