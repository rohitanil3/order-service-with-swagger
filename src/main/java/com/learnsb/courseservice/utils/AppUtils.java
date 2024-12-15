package com.learnsb.courseservice.utils;

import com.learnsb.courseservice.dto.CourseRequestDTO;
import com.learnsb.courseservice.dto.CourseResponseDTO;
import com.learnsb.courseservice.entity.CourseEntity;

import java.util.UUID;

public class AppUtils {

    public static CourseEntity mapRequestDTOtoCourseEntity(CourseRequestDTO courseRequestDTO)
    {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseRequestDTO.getName());
        courseEntity.setTrainer_name(courseRequestDTO.getTrainer_name());
        courseEntity.setDuration(courseRequestDTO.getDuration());
        courseEntity.setStartdate(courseRequestDTO.getStartdate());
        courseEntity.setCoursetype(courseRequestDTO.getCoursetype());
        courseEntity.setFees(courseRequestDTO.getFees());
        courseEntity.setIscertAvailable(courseRequestDTO.isIscertAvailable());
        return courseEntity;
    }

    public static CourseResponseDTO mapCourseEntitytoResponseDTO(CourseEntity courseEntity){
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setId(courseEntity.getId());
        courseResponseDTO.setName(courseEntity.getName());
        courseResponseDTO.setTrainer_name(courseEntity.getTrainer_name());
        courseResponseDTO.setDuration(courseEntity.getDuration());
        courseResponseDTO.setStartdate(courseEntity.getStartdate());
        courseResponseDTO.setCoursetype(courseEntity.getCoursetype());
        courseResponseDTO.setFees(courseEntity.getFees());
        courseResponseDTO.setIscertAvailable(courseEntity.isIscertAvailable());
        courseResponseDTO.setUniquecode(UUID.randomUUID().toString());
        return courseResponseDTO;


    }
}
