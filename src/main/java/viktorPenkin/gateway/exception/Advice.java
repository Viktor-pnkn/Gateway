package viktorPenkin.gateway.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;

@RestControllerAdvice
public class Advice {

    @ExceptionHandler(ConnectException.class)
    public String handleConnection() {
        return "Some problems  occurred with connection...";
    }


    @ExceptionHandler(IncorrectNumberException.class)
    public String handleNumberFormat(IncorrectNumberException e) {
        return e.getMessage();
    }

}
