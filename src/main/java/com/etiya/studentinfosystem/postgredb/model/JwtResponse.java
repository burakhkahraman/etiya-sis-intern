package com.etiya.studentinfosystem.postgredb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "JWT için yanıt modeli. Access ve refresh token'ları içerir.")
public class JwtResponse {

    @Schema(description = "Token tipi. Bu değer genellikle 'Bearer' olarak belirlenir.", example = "Bearer")
    private final String tokenType = "Bearer";

    @Schema(description = "Kullanıcıya verilen erişim tokenı.", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;

    @Schema(description = "Kullanıcının oturumunun süresini yenilemek için kullanılan token.", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;
}
