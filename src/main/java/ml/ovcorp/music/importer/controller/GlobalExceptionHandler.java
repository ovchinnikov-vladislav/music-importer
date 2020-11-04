package ml.ovcorp.music.importer.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return "";
    }

    @Data
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ExceptionEntity {
        @JsonIgnore
        private HttpStatus status;
        private String code;
        private String message;
    }
}
