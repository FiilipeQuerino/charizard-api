package charizard.sc.senac.br.pokecenter.exceptions;

public class EtapaAtendimentoServiceException extends RuntimeException  {
    public EtapaAtendimentoServiceException(){
        super();
    }
    public EtapaAtendimentoServiceException(final String message){
        super(message);
    }
    public EtapaAtendimentoServiceException(final String message, final Throwable cause){
        super(message,cause);
    }
    public EtapaAtendimentoServiceException(final Throwable cause){
        super(cause);
    }
}
