package com.learnsb.courseservice.controller;

import com.learnsb.courseservice.dto.CourseRequestDTO;
import com.learnsb.courseservice.dto.CourseResponseDTO;
import com.learnsb.courseservice.dto.ServiceResponse;
import com.learnsb.courseservice.exception.CustomDAOException;
import com.learnsb.courseservice.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ServiceResponse<CourseResponseDTO> create(@Valid @RequestBody CourseRequestDTO dto){
       CourseResponseDTO dto1= courseService.save(dto);
       return new ServiceResponse<>(HttpStatus.CREATED, dto1);
    }

    @GetMapping("/course/{id}")
    public ServiceResponse<CourseResponseDTO>  findbyId(@PathVariable Integer id) throws CustomDAOException {
        CourseResponseDTO dto1= courseService.findbyId(id);
        return new ServiceResponse<>(HttpStatus.OK, dto1);
    }

    @GetMapping("/course")
    public ServiceResponse<List<CourseResponseDTO>> findAll(){
        List<CourseResponseDTO> all = courseService.findAll();
        return new ServiceResponse<>(HttpStatus.OK,all);
    }

    @DeleteMapping("/course/{id}")
    public ServiceResponse<CourseResponseDTO>  deletebyId(@PathVariable Integer id) throws CustomDAOException {
        courseService.delete(id);
        return new ServiceResponse<>(HttpStatus.OK, null);
    }

    @PutMapping("/course/{id}")
    public ServiceResponse<CourseResponseDTO>  modify(@PathVariable Integer id ,@Valid @RequestBody CourseRequestDTO dto){
        CourseResponseDTO courseResponseDTO = courseService.modifyCourse(dto, id);
        return new ServiceResponse<>(HttpStatus.OK, courseResponseDTO);
    }
}
