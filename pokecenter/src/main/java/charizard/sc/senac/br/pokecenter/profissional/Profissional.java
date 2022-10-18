package charizard.sc.senac.br.pokecenter.profissional;


import charizard.sc.senac.br.pokecenter.utils.TipoProfissional;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name="profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_profissional")
    private Long id;

    @Column(name="nome")
    @NotNull(message="O nome não pode ser nulo")
    @NotEmpty(message="O nome não pode ser vazio")
    private String nome;

    @Column(name="tipoProfissional")
    @Enumerated(EnumType.STRING)
    private TipoProfissional tipoProfissional;

}
