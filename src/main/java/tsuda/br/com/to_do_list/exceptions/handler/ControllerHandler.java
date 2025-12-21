package tsuda.br.com.to_do_list.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tsuda.br.com.to_do_list.exceptions.GenericBusinessRuleException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(GenericBusinessRuleException.class)
    public ResponseEntity<Map<String, Object>> handleGenericBusinessRuleException(GenericBusinessRuleException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
