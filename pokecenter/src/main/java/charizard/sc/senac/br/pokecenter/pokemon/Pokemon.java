package charizard.sc.senac.br.pokecenter.pokemon;


import charizard.sc.senac.br.pokecenter.paciente.Paciente;
import charizard.sc.senac.br.pokecenter.utils.Genero;
import charizard.sc.senac.br.pokecenter.utils.TipoPokemon;
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
@Table(name= "pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="i_pokemon")
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @Column(name="nome")
    @NotNull(message="O nome não pode ser nulo")
    @NotEmpty(message="O nome não pode ser vazio")
    private String nome;

    @Column(name = "tipo_pokemon")
    @Enumerated(EnumType.STRING)
    private TipoPokemon tipoPokemon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pokemon", orphanRemoval = true)
    List<Paciente> pacienteList = new ArrayList<>();

}
