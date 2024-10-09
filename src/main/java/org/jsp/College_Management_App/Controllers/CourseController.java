package org.jsp.College_Management_App.Controllers;


import org.jsp.College_Management_App.Exception.ResourceNotFoundException;
import org.jsp.College_Management_App.Model.Course;
import org.jsp.College_Management_App.Repository.CourseRepository;
import org.jsp.College_Management_App.Services.CourseService;
import org.jsp.College_Management_App.payload.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;
  
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return ResponseEntity.ok(course);
    }
    @GetMapping("/instructor/{instructorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable Long instructorId) {
        List<Course> courses = courseService.getCoursesByInstructorId(instructorId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/department/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Course>> getCoursesByDepartment(@PathVariable Long departmentId) {
        List<Course> courses = courseService.getCoursesByDepartmentId(departmentId);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Course>> getCoursesBystudentId(@PathVariable Long studentId) {
        List<Course> courses = courseService.getCoursesByStudentId(studentId);
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCourse(
    		
             
            @RequestBody CourseRequest courserequest) {
    	
    	Optional<Course> existingCourse = courseRepository.findByName(courserequest.getName());
        if (existingCourse.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Course name is already taken.");
        }
        Course newCourse = courseService.createCourse(courserequest);
        return ResponseEntity.ok(newCourse);
    }
    // Update a course
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody  CourseRequest
    		request) {
        Course updatedCourse = courseService.updateCourse(id, request);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
