package charizard.sc.senac.br.pokecenter.insumo;

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
public class InsumoService {

    private InsumoRepository insumoRepository;

    public Insumo criarInsumo(InsumoRepresentation.CriarOuAtualizar criar) {

        return this.insumoRepository.save(Insumo.builder()
                .nome(criar.getNome())
                .build());
    }
    public Page<Insumo> buscarTodos(Pageable pageable) {
        return this.insumoRepository.findAll(pageable);
    }

    public Page<Insumo> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.insumoRepository.findAll(filtroURI, pageable);
    }
    public Insumo atualizar(Long idInsumo, InsumoRepresentation.CriarOuAtualizar atualizar) {

        Insumo insumoParaAtualizar = Insumo.builder()
                .id(idInsumo)
                .nome((atualizar.getNome()))
                .build();
        return this.insumoRepository.save(insumoParaAtualizar);
    }
    public Insumo buscarUmInsumo(Long idInsumo) {
        return this.getInsumo(idInsumo);
    }

    private Insumo getInsumo(Long idInsumo){
        Optional<Insumo> insumoAtual =
                this.insumoRepository.findById(idInsumo);
        if (insumoAtual.isPresent()) {
            return insumoAtual.get();
        }else {
            throw new NotFoundException("Insumo não encontrado");
        }
    }
}
