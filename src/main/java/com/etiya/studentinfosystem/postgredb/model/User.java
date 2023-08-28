package com.etiya.studentinfosystem.postgredb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Sistemdeki kullanıcıları temsil eden model")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Kullanıcının benzersiz ID'si")
    private Long id;

    @Schema(description = "Kullanıcının ismi")
    private String username;

    @Schema(description = "Kullanıcının e-posta adresi")
    private String email;

    @Schema(description = "Kullanıcının şifresi", hidden = true) // Şifrenin dökümantasyonda gösterilmemesi için hidden=true kullanıldı.
    private String password;

    @Schema(description = "Kullanıcının profil fotoğrafı path'i")
    private String pfp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name="id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
    )
    @Schema(description = "Kullanıcının sahip olduğu roller")
    private Set<Role> roles;
}
