package com.learnsb.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {

    private int id;
    private String name;
    private String trainer_name;
    private String duration;
    private String startdate;
    private String coursetype;
    private double fees;
    private boolean iscertAvailable;
    private String uniquecode;
}
