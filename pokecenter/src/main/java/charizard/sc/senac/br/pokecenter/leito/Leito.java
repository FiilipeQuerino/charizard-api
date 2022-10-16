package charizard.sc.senac.br.pokecenter.leito;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name= "leito")
public class Leito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    private Paciente paciente;

    @Column(name="identificador")
    private String identificador;

}
