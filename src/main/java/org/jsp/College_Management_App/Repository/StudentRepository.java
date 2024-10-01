package org.jsp.College_Management_App.Repository;


import java.util.List;

import org.jsp.College_Management_App.Model.Course;
import org.jsp.College_Management_App.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	 @Query("SELECT s.courses FROM Student s WHERE s.id = :studentId")
	    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);
	
}
