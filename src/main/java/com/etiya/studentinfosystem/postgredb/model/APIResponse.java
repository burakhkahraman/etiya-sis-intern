package com.etiya.studentinfosystem.postgredb.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponse {
    private String message;
}
