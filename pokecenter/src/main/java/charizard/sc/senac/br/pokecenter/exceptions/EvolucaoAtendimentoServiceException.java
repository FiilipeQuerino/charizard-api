package charizard.sc.senac.br.pokecenter.exceptions;

public class EvolucaoAtendimentoServiceException extends RuntimeException  {
    public EvolucaoAtendimentoServiceException(){
        super();
    }
    public EvolucaoAtendimentoServiceException(final String message){
        super(message);
    }
    public EvolucaoAtendimentoServiceException(final String message, final Throwable cause){
        super(message,cause);
    }
    public EvolucaoAtendimentoServiceException(final Throwable cause){
        super(cause);
    }
}
