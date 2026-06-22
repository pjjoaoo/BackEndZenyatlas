package com.itb.tcc.mif3an.pizzaria.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class ErrorMessage {
    private LocalDateTime timestamp;
    private String[] message;
    private HttpStatus tittle;
    private int status;

    public ErrorMessage(LocalDateTime timestamp, String[] message,HttpStatus tittle ) {
        this.timestamp = timestamp;
        this.message = message;
        this.tittle = tittle;
        this.status = tittle.value();
    }
}
