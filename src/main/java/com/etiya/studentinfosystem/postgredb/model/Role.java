package com.etiya.studentinfosystem.postgredb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Kullanıcı rollerini tanımlar")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Rolu tanımlayan benzersiz ID")
    private Long id;

    @Column(unique = true)
    @Schema(description = "Rolün adı (örn. ADMIN, USER)")
    private String name;
}
