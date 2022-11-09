package charizard.sc.senac.br.pokecenter.exceptions;

public class AtendimentoServiceException extends RuntimeException  {
    public AtendimentoServiceException(){
        super();
    }
    public AtendimentoServiceException(final String message){
        super(message);
    }
    public AtendimentoServiceException(final String message, final Throwable cause){
        super(message,cause);
    }
    public AtendimentoServiceException(final Throwable cause){
        super(cause);
    }
}
