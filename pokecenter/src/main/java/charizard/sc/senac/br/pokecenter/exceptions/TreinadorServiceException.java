package charizard.sc.senac.br.pokecenter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TreinadorServiceException extends RuntimeException  {
    public TreinadorServiceException(){
        super();
    }
    public TreinadorServiceException(final String message){
        super(message);
    }
    public TreinadorServiceException(final String message, final Throwable cause){
        super(message,cause);
    }
    public TreinadorServiceException(final Throwable cause){
        super(cause);
    }
}
