package charizard.sc.senac.br.pokecenter.utils;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public enum Genero {
    M,
    F;

    @Data
    @Getter
    @Setter
    @Builder
    public static class Paginacao {
        private Integer tamanhoPagina;
        private Integer paginaSelecionada;
        private Boolean proximaPagina;
        private List<?> conteudo;
    }
}
