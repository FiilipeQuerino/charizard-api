package charizard.sc.senac.br.pokecenter.paciente;

import charizard.sc.senac.br.pokecenter.exceptions.NotFoundException;
import charizard.sc.senac.br.pokecenter.pokemon.Pokemon;
import charizard.sc.senac.br.pokecenter.pokemon.PokemonService;
import charizard.sc.senac.br.pokecenter.treinador.Treinador;
import charizard.sc.senac.br.pokecenter.treinador.TreinadorRepresentation;
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
public class PacienteService {

    private PacienteRepository pacienteRepository;

    public Paciente criarPaciente(TreinadorService treinadorService,
                                  Long idTreinador,
                                  PokemonService pokemonService,
                                  PacienteRepresentation.CriarOuAtualizar criar) {

        Treinador treinador = treinadorService.buscarUmTreinador(idTreinador);

        Pokemon pokemon = pokemonService.buscarUmPokemon(criar.getPokemon());

        return this.pacienteRepository.save(Paciente.builder()
                .treinador(treinador)
                .nome(criar.getNome())
                .tipoSanguineo(criar.getTipoSanguineo())
                .dataNascimento(criar.getDataNascimento())
                .alergia(criar.getAlergia())
                .genero(criar.getGenero())
                .pokemon(pokemon)
                .build());
    }
    public Page<Paciente> buscarTodos(Pageable pageable) {
        return this.pacienteRepository.findAll(pageable);
    }

    public Page<Paciente> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.pacienteRepository.findAll(filtroURI, pageable);
    }

    public Paciente atualizar(Long idPaciente, PacienteRepresentation.CriarOuAtualizar atualizar) {


        Paciente pacienteParaAtualizar = Paciente.builder()
                .id(idPaciente)
                .nome(atualizar.getNome())
                .tipoSanguineo(atualizar.getTipoSanguineo())
                .dataNascimento(atualizar.getDataNascimento())
                .alergia(atualizar.getAlergia())
                .genero(atualizar.getGenero())
//                .treinador(atualizar.getTreinador())
//                .pokemon(atualizar.getPokemon())
                .build();
        return this.pacienteRepository.save(pacienteParaAtualizar);
    }
    public Paciente buscarUmPaciente(Long idPaciente) {
        return this.getPaciente(idPaciente);
    }

    private Paciente getPaciente(Long idPaciente){
        Optional<Paciente> pacienteAtual =
                this.pacienteRepository.findById(idPaciente);
        if (pacienteAtual.isPresent()) {
            return pacienteAtual.get();
        }else {
            throw new NotFoundException("Paciente não encontrado");
        }
    }


}
