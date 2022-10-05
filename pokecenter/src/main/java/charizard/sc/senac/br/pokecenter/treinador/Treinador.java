package charizard.sc.senac.br.pokecenter.treinador;

import charizard.sc.senac.br.pokecenter.paciente.Paciente;
import charizard.sc.senac.br.pokecenter.utils.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "treinador")
public class Treinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nascimento")
    private Date dataNascimento;

    @Column(name = "telefone")
    private int telefone;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "treinador", orphanRemoval = true)
    List<Paciente> pacienteList = new ArrayList<>();*/
}
