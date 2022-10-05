package charizard.sc.senac.br.pokecenter.paciente;

import charizard.sc.senac.br.pokecenter.exceptions.NotFoundException;
import charizard.sc.senac.br.pokecenter.pokemon.Pokemon;
import charizard.sc.senac.br.pokecenter.pokemon.PokemonService;
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
@Slf4j //biblioteca de log (ele indica que pode ser utilizado Log´s, igual o do console)
public class PacienteService {

    private PacienteRepository pacienteRepository;

    public Paciente criarPaciente(TreinadorService treinadorService,
                                  Long idTreinador,
                                  PokemonService pokemonService,
                                  Long idPokemon,
                                  PacienteRepresentation.CriarOuAtualizar criar) {

        Treinador treinador = treinadorService.buscarUmTreinador(idTreinador);

        Pokemon pokemon = pokemonService.buscarUmPokemon(idPokemon);

        return this.pacienteRepository.save(Paciente.builder()
                .treinador(treinador)
                .pokemon(pokemon)
                .nome(criar.getNome())
                .tipoSanguineo(criar.getTipoSanguineo())
                .dataNascimento(criar.getDataNascimento())
                .alergia(criar.getAlergia())
                .genero(criar.getGenero())
                .build());
    }
}
