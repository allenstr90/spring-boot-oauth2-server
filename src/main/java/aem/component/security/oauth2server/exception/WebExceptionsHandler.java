package aem.component.security.oauth2server.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class WebExceptionsHandler {

    @ExceptionHandler({FieldExistException.class})
    public ResponseEntity<Map<String, String>> handleFieldExistsException(FieldExistException e) {
        log.error(e.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
}
