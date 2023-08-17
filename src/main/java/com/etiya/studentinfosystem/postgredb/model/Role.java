package com.etiya.studentinfosystem.postgredb.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @Column(unique = true)
    private String shortCode;
     //Getter and Setter method


    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
}
