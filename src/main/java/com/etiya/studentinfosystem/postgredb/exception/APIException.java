package com.etiya.studentinfosystem.postgredb.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
public class APIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public APIException(String message) {
        super(message);
    }

    public APIException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
