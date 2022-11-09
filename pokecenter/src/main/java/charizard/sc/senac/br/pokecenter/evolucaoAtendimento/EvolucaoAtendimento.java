package charizard.sc.senac.br.pokecenter.evolucaoAtendimento;


import charizard.sc.senac.br.pokecenter.etapaAtendimento.EtapaAtendimento;
import charizard.sc.senac.br.pokecenter.leito.Leito;
import charizard.sc.senac.br.pokecenter.utils.Etapa;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name= "evolucaoatendimento")
public class EvolucaoAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "dataEntrada")
    private Date dataEntrada;

    @Column(name = "dataSaida")
    private Date dataSaida;

    @ManyToOne
    private EtapaAtendimento etapaatendimento;

    @ManyToOne
    private Leito leito;

//    @ManyToOne
//    private Profissinal profissinal;
}
