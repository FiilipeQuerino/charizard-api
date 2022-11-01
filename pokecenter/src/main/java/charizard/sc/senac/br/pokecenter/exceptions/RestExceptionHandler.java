package charizard.sc.senac.br.pokecenter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {//vai receber os exceptions para serem mapeados

    @ExceptionHandler(TreinadorServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RespostaErro erroTreinamentoSerivce(TreinadorServiceException treinadorServiceException, HttpServletRequest request){
        return RespostaErro.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .message(treinadorServiceException.getMessage())
                .path(request.getContextPath()+request.getServletPath())
                .build();
    }
    @ExceptionHandler(AtendimentoServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RespostaErro erroAtendimentoSerivce(AtendimentoServiceException atendimentoServiceException, HttpServletRequest request){
        return RespostaErro.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .message(atendimentoServiceException.getMessage())
                .path(request.getContextPath()+request.getServletPath())
                .build();
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public RespostaErro erroNotFound(NotFoundException notFoundException, HttpServletRequest request){
        return RespostaErro.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(notFoundException.getMessage())
                .path(request.getContextPath()+request.getServletPath())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public RespostaErro errorPayload(MethodArgumentNotValidException exception, HttpServletRequest request){

        BindingResult result = exception.getBindingResult();

        return RespostaErro.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .message(result.getFieldErrors().get(0).getDefaultMessage())
                .path(request.getContextPath() + request.getServletPath())
                .build();


    }
}
