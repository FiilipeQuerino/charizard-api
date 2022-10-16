package charizard.sc.senac.br.pokecenter.leito;

import charizard.sc.senac.br.pokecenter.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j //biblioteca de log (ele indica que pode ser utilizado Log´s, igual o do console)
public class LeitoService {

    private LeitoRepository leitoRepository;

    public Leito criarLeito(LeitoRepresentation.CriarOuAtualizar criar) {

        return this.leitoRepository.save(Leito.builder()
                .identificador(criar.getIdentificador())
                .build());
    }
    public Page<Leito> buscarTodos(Pageable pageable) {
        return this.leitoRepository.findAll(pageable);
    }

    public Page<Leito> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.leitoRepository.findAll(filtroURI, pageable);
    }
    public Leito atualizar(Long idLeito, LeitoRepresentation.CriarOuAtualizar atualizar) {

        Leito leitoParaAtualizar = Leito.builder()
                .id(idLeito)
                .identificador((atualizar.getIdentificador()))
                .build();
        return this.leitoRepository.save(leitoParaAtualizar);
    }
    public Leito buscarUmLeito(Long idLeito) {
        return this.getLeito(idLeito);
    }

    private Leito getLeito(Long idLeito){
        Optional<Leito> leitoAtual =
                this.leitoRepository.findById(idLeito);
        if (leitoAtual.isPresent()) {
            return leitoAtual.get();
        }else {
            throw new NotFoundException("Leito não encontrado");
        }
    }
}
