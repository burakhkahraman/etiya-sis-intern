package com.etiya.studentinfosystem.postgredb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private String name;
    private String description;
    private String location;
    private Integer maxStudentCount;
}
