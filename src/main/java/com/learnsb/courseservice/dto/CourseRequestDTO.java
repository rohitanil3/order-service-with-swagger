package com.learnsb.courseservice.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "trainer_name is mandatory")
    private String trainer_name;
    private String duration;
    private String startdate;
    private String coursetype;
    private double fees;
    private boolean iscertAvailable;


}
