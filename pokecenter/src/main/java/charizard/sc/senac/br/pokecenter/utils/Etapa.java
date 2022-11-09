package charizard.sc.senac.br.pokecenter.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Etapa {
    E("Entrada"),
    T("Triagem"),
    A("Andamento"),
    S("Saida");
    private String value;
}
