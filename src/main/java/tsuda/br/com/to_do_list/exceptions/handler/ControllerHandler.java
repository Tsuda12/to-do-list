package tsuda.br.com.to_do_list.exceptions.handler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tsuda.br.com.to_do_list.exceptions.GenericBusinessRuleException;
import tsuda.br.com.to_do_list.exceptions.GenericNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(GenericBusinessRuleException.class)
    public ResponseEntity<Map<String, Object>> handleGenericBusinessRuleException(GenericBusinessRuleException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(GenericNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleGenericNotFoundException(GenericNotFoundException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .toList();

        Map<String, Object> body = new HashMap<>();
        body.put("message", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
