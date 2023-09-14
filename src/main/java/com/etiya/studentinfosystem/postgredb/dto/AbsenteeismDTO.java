package com.etiya.studentinfosystem.postgredb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsenteeismDTO {
    private Long id;
    private String date;
    private int duration;
    private int isActive;
    private String studentName;
}
