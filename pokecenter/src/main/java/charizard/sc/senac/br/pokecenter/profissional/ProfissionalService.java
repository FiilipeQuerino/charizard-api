package charizard.sc.senac.br.pokecenter.profissional;


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
@Slf4j
public class ProfissionalService {


    private ProfissionalRepository profissionalRepository;

    public Profissional criarProfissional(ProfissionalRepresentation.criarOuAtualizar criar){

           return this.profissionalRepository.save(Profissional.builder()
                           .nome(criar.getNome())
                           .tipoProfissional(criar.getTipoProfissional())

                   .build());

    }

    public Page<Profissional> buscarTodos(Pageable pageable) {
        return this.profissionalRepository.findAll(pageable);
    }

    public Page<Profissional> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.profissionalRepository.findAll(filtroURI, pageable);
    }

    public Profissional atualizar(Long idProfissional, ProfissionalRepresentation.criarOuAtualizar atualizar) {

        Profissional profissionalAtualizar = Profissional.builder()
                .id(idProfissional)
                .nome((atualizar.getNome()))
                .tipoProfissional(atualizar.getTipoProfissional())
                .build();
        return this.profissionalRepository.save(profissionalAtualizar);
    }

    public Profissional buscarUmProfissional(Long idProfissional) {
                return this.getProfissional(idProfissional);
    }
    private Profissional getProfissional(Long idProfissional){
        Optional<Profissional> profissionalAtual = this.profissionalRepository.findById(idProfissional);

        if(profissionalAtual.isPresent()){
            return profissionalAtual.get();

        }else {
            throw new NotFoundException("Profissional não encontrado");
        }
    }

}



