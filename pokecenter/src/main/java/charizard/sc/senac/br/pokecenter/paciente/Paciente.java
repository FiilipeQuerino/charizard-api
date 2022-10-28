package charizard.sc.senac.br.pokecenter.paciente;


import charizard.sc.senac.br.pokecenter.atendimento.Atendimento;
import charizard.sc.senac.br.pokecenter.pokemon.Pokemon;
import charizard.sc.senac.br.pokecenter.treinador.Treinador;
import charizard.sc.senac.br.pokecenter.utils.Genero;
import charizard.sc.senac.br.pokecenter.utils.TipoPokemon;
import charizard.sc.senac.br.pokecenter.utils.TipoSanguineo;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name= "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Atendimento atendimento;
    @ManyToOne
    private Treinador treinador;

//    @ManyToOne
//    private Pokemon pokemon;

    @Column(name="nome")
    @NotNull(message="O nome não pode ser nulo")
    @NotEmpty(message="O nome não pode ser vazio")
    private String nome;

    @Column(name = "tipo_sanguineo")
    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    @Column(name = "nascimento")
    private Date dataNascimento;

    @Column(name = "alergia")
    private String alergia;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;

//    @Column(name = "pokemon")
//    private Pokemon pokemon;
}
