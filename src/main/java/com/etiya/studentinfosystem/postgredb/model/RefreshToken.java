package com.etiya.studentinfosystem.postgredb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Yenilenmiş tokenları ve onların özelliklerini saklar")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Refresh token'un benzersiz ID'si")
    private Long id;

    @Schema(description = "Refresh token değeri")
    private String token;

    @Schema(description = "Refresh token'a sahip olan kullanıcının email adresi")
    private String email;

    @Schema(description = "Refresh token'ın süresinin dolacağı zaman")
    private Instant expiry;
}
