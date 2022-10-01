package charizard.sc.senac.br.pokecenter.treinador;

import charizard.sc.senac.br.pokecenter.exceptions.NotFoundException;
import charizard.sc.senac.br.pokecenter.exceptions.TreinadorServiceException;
import com.querydsl.core.types.Predicate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j //biblioteca de log (ele indica que pode ser utilizado Log´s, igual o do console)
public class TreinadorService {
    private TreinadorRepository treinadorRepository;
    public Treinador criarTreinador(TreinadorRepresentation.CriarOuAtualizar criar) {

        return this.treinadorRepository.save(Treinador.builder()
                .nome(criar.getNome())
                .dataNascimento(criar.getDataNascimento())
                .telefone(criar.getTelefone())
                .genero(criar.getGenero())
                .build());
    }
    public Page<Treinador> buscarTodos(Pageable pageable) {
        return this.treinadorRepository.findAll(pageable);
    }

    public Page<Treinador> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.treinadorRepository.findAll(filtroURI, pageable);
    }
    public Treinador atualizar(Long idTreinador, TreinadorRepresentation.CriarOuAtualizar atualizar) {

        Treinador treinadorParaAtualizar = Treinador.builder()
                .id(idTreinador)
                .nome((atualizar.getNome()))
                .dataNascimento(atualizar.getDataNascimento())
                .telefone(atualizar.getTelefone())
                .genero(atualizar.getGenero())
                .build();
        return this.treinadorRepository.save(treinadorParaAtualizar);
    }
    public Treinador buscarUmTreinador(Long idTreinador) {
        return this.getTreinador(idTreinador);
    }

    private Treinador getTreinador(Long idTreinador){
        Optional<Treinador> treinadorAtual =
                this.treinadorRepository.findById(idTreinador);
        if (treinadorAtual.isPresent()) {
            return treinadorAtual.get();
        }else {
            throw new NotFoundException("Treinador não encontrado");
        }
    }
}
