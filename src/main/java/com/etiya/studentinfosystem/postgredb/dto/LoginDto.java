package com.etiya.studentinfosystem.postgredb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LoginDto {
    @NotEmpty(message = "Email is required")
    @Email(message = "Malformed email")
    private String email;

    @NotEmpty(message = "Password is required")

    private String password;
}
