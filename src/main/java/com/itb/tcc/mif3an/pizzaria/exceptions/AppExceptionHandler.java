package com.itb.tcc.mif3an.pizzaria.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class AppExceptionHandler {

    private static final ZoneId zoneId_BRASIL = ZoneId.of("America/Sao_Paulo");
    private static final Map<Class <? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = new HashMap<>();

    static {
        EXCEPTION_STATUS_MAP.put(BadRequest.class, HttpStatus.BAD_REQUEST);
        EXCEPTION_STATUS_MAP.put(NotFound.class, HttpStatus.NOT_FOUND);
        EXCEPTION_STATUS_MAP.put(Unauthorized.class, HttpStatus.UNAUTHORIZED);
        EXCEPTION_STATUS_MAP.put(Forbidden.class, HttpStatus.FORBIDDEN);
        EXCEPTION_STATUS_MAP.put(AccessDeniedException.class, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleAllException (Exception exception, HttpStatus request) {

        // 👇 COLOQUE EXATAMENTE AQUI, NA PRIMEIRA LINHA DO MÉTODO!
        exception.printStackTrace();

        HttpStatus status = EXCEPTION_STATUS_MAP.getOrDefault(exception.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);

        String message = (exception instanceof Forbidden || exception instanceof AccessDeniedException)
                ? "Você não tem permissão para acessar este recurso!"
                : (exception.getLocalizedMessage() != null ? exception.getLocalizedMessage() : exception.toString());

        LocalDateTime now = LocalDateTime.now(zoneId_BRASIL);
        String[] messages = message.split(":");

        ErrorMessage ErrorMessage = new ErrorMessage(now, messages, status);

        return new ResponseEntity<>(ErrorMessage, new HttpHeaders(), status);

    }

}
