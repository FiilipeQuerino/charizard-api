package charizard.sc.senac.br.pokecenter.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum TipoSanguineo {
    A_POS("A+"),
    B_POS("B+"),
    AB_POS("AB+"),
    O_POS("O+"),
    A_NEG("A-"),
    B_NEG("B-"),
    AB_NEG("AB-"),
    O_NEG("O-");

    private String value;

}
