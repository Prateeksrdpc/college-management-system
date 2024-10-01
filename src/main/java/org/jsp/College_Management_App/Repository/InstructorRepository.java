package org.jsp.College_Management_App.Repository;

import org.jsp.College_Management_App.Model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    List<Instructor> findByDepartmentId(Long departmentId);
}