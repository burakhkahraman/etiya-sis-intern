package com.etiya.studentinfosystem.postgredb.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse{

        private int statusCode;
        private String message;
}
