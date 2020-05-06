package br.com.curso.mc.resource.HandlerException;

import br.com.curso.mc.exception.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFound
            , HttpServletRequest request){
        StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), objectNotFound.getMessage(),
                Calendar.getInstance());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> dataViolation(ConstraintViolationException constraintViolationException
            , HttpServletRequest request){
            StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(),constraintViolationException.getCause().getMessage(),Calendar.getInstance());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationViolation(MethodArgumentNotValidException e
            , HttpServletRequest request){

            ValidationError validationViolation = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de validação !",Calendar.getInstance());

            for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
                validationViolation.addError(fieldError.getField(),fieldError.getDefaultMessage());
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationViolation);
    }
}
