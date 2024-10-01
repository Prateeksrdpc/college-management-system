package org.jsp.College_Management_App.Repository;

import org.jsp.College_Management_App.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructorId(Long instructorId);
    List<Course> findByDepartmentId(Long departmentId);
    Optional<Course> findByName(String name); 
    
}
