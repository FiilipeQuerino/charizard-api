package charizard.sc.senac.br.pokecenter.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Situacao {
    A("Andamento"),
    F("Finalizado");
    private String value;
}
