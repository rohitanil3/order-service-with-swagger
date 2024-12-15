package com.learnsb.courseservice.service;


import com.learnsb.courseservice.dao.CourseDAO;
import com.learnsb.courseservice.dto.CourseRequestDTO;
import com.learnsb.courseservice.dto.CourseResponseDTO;
import com.learnsb.courseservice.entity.CourseEntity;
import com.learnsb.courseservice.exception.CustomDAOException;
import com.learnsb.courseservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    @Autowired
    CourseDAO courseDAO;


    public CourseResponseDTO save(CourseRequestDTO requestDTO){

        CourseEntity courseEntity = AppUtils.mapRequestDTOtoCourseEntity(requestDTO);
        courseEntity=courseDAO.save(courseEntity);
        CourseResponseDTO courseResponseDTO = AppUtils.mapCourseEntitytoResponseDTO(courseEntity);
        return courseResponseDTO;

    }

    public List<CourseResponseDTO> findAll(){

        Iterable<CourseEntity> courseEntities = courseDAO.findAll();
        return StreamSupport.stream(courseEntities.spliterator(),false)
                .map(courseEntity -> AppUtils.mapCourseEntitytoResponseDTO(courseEntity))
                .collect(Collectors.toList());
    }

    public CourseResponseDTO findbyId(Integer id) throws CustomDAOException {
        CourseResponseDTO courseResponseDTO=null;
        Optional<CourseEntity> byId = courseDAO.findById(id);
        if(byId.isPresent())
          courseResponseDTO = AppUtils.mapCourseEntitytoResponseDTO(byId.get());
        else
            throw new CustomDAOException("Find with a valid ID");
        return courseResponseDTO;

    }

    public void delete(Integer id) throws CustomDAOException {
        Optional<CourseEntity> byId = courseDAO.findById(id);
        if(byId.isPresent())
            courseDAO.deleteById(id);
        else
            throw new CustomDAOException("Exception while deleting ");
        }


    public CourseResponseDTO modifyCourse(CourseRequestDTO courseRequestDTO, Integer id){

        CourseEntity courseEntity = courseDAO.findById(id).get();
        courseEntity.setName(courseRequestDTO.getName());
        courseEntity.setTrainer_name(courseRequestDTO.getTrainer_name());
        courseEntity.setDuration(courseRequestDTO.getDuration());
        courseEntity.setStartdate(courseRequestDTO.getStartdate());
        courseEntity.setCoursetype(courseRequestDTO.getCoursetype());
        courseEntity.setFees(courseRequestDTO.getFees());
        courseEntity.setIscertAvailable(courseRequestDTO.isIscertAvailable());
        courseEntity=courseDAO.save(courseEntity);
        CourseResponseDTO courseResponseDTO = AppUtils.mapCourseEntitytoResponseDTO(courseEntity);
        return courseResponseDTO;
    }
}
