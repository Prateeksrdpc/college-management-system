package org.jsp.College_Management_App.Repository;

import java.util.List;

import org.jsp.College_Management_App.Model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);
}
