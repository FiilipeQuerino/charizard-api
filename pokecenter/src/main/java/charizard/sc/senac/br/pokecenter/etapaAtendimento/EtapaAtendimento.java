package charizard.sc.senac.br.pokecenter.etapaAtendimento;


import charizard.sc.senac.br.pokecenter.atendimento.Atendimento;
import charizard.sc.senac.br.pokecenter.paciente.Paciente;
import charizard.sc.senac.br.pokecenter.utils.Etapa;
import charizard.sc.senac.br.pokecenter.utils.Situacao;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name= "etapaatendimento")
public class EtapaAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataEntrada")
    private Date dataEntrada;

    @Column(name = "dataSaida")
    private Date dataSaida;

    @Column(name = "etapa")
    @Enumerated(EnumType.STRING)
    private Etapa etapa;
    @ManyToOne
    private Atendimento atendimento;

}
