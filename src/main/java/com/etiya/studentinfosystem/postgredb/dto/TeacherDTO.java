package com.etiya.studentinfosystem.postgredb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private String firstName;
    private String lastName;
    private String expertiseArea;
}
