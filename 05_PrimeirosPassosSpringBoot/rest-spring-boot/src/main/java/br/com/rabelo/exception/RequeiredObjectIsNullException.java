package br.com.rabelo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequeiredObjectIsNullException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    
    public RequeiredObjectIsNullException(String ex) {
        super(ex);
    }
    
    public RequeiredObjectIsNullException() {
    	super("Não é permitido persistir um objeto nulo!");
    }
    
}
