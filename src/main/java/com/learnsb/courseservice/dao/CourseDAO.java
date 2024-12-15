package com.learnsb.courseservice.dao;


import com.learnsb.courseservice.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseDAO extends CrudRepository<CourseEntity,Integer> {

}
