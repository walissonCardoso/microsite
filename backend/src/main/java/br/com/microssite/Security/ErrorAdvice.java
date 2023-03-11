package br.com.microssite.Security;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorAdvice {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> error404() {
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> error400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        var processedList = errors.stream().map(ErrorDataValidation::new).toList();
        
        return ResponseEntity.badRequest().body(processedList);
    }
    
    private record ErrorDataValidation(String field, String message) {
        public ErrorDataValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
