package org.jsp.College_Management_App.Services;

import org.jsp.College_Management_App.Model.Course;
import org.jsp.College_Management_App.Model.Enrollment;
import org.jsp.College_Management_App.Model.Student;
import org.jsp.College_Management_App.Repository.CourseRepository;
import org.jsp.College_Management_App.Repository.EnrollmentRepository;
import org.jsp.College_Management_App.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

	 @Autowired
	    private EnrollmentRepository enrollmentRepository;

	    @Autowired
	    private CourseRepository courseRepository;

	    @Autowired
	    private StudentRepository studentRepository;

	    public List<Enrollment> getAllEnrollments() {
	        return enrollmentRepository.findAll();
	    }

	    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
	        return enrollmentRepository.findByStudentId(studentId);
	    }

	    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
	        return enrollmentRepository.findByCourseId(courseId);
	    }

	    public Optional<Enrollment> getEnrollmentById(Long id) {
	        return enrollmentRepository.findById(id);
	    }

	    public Enrollment enrollStudentInCourse(Long studentId, Long courseId) {
	        Student student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new RuntimeException("Student not found"));

	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> new RuntimeException("Course not found"));

	        // Check if the student is already enrolled in the course
	        if (enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId) != null) {
	            throw new RuntimeException("Student is already enrolled in this course");
	        }

	        Enrollment enrollment = new Enrollment();
	        enrollment.setStudent(student);
	        enrollment.setCourse(course);
	        
	        return enrollmentRepository.save(enrollment);
	    }

	    public void deleteEnrollment(Long id) {
	        enrollmentRepository.deleteById(id);
	    }
}
