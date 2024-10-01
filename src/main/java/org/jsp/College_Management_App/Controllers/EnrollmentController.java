package org.jsp.College_Management_App.Controllers;


import org.jsp.College_Management_App.Exception.BadRequestException;
import org.jsp.College_Management_App.Model.Enrollment;
import org.jsp.College_Management_App.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")

public class EnrollmentController {

	 @Autowired
	    private EnrollmentService enrollmentService;

	    @GetMapping
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
	        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
	        return ResponseEntity.ok(enrollments);
	    }
	    @GetMapping("/count")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Integer> getAllEnrollmentsCount() {
	        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
	        return ResponseEntity.ok(enrollments.size());
	    }

	    @GetMapping("/student/{studentId}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable Long studentId) {
	        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
	        return ResponseEntity.ok(enrollments);
	    }

	    @GetMapping("/course/{courseId}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable Long courseId) {
	        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
	        return ResponseEntity.ok(enrollments);
	    }

	    @GetMapping("/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
	        Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(id);
	        return enrollment.map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Enrollment> enrollStudentInCourse(
	            @RequestParam Long studentId, 
	            @RequestParam Long courseId) {
	        Enrollment enrollment = enrollmentService.enrollStudentInCourse(studentId, courseId);
	        return ResponseEntity.ok(enrollment);
	    }

	    @DeleteMapping("/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
	        enrollmentService.deleteEnrollment(id);
	        return ResponseEntity.noContent().build();
	    }
}

