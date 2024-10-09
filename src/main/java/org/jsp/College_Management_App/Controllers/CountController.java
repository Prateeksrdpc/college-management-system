package org.jsp.College_Management_App.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.jsp.College_Management_App.Model.Course;
import org.jsp.College_Management_App.Model.Department;
import org.jsp.College_Management_App.Model.Instructor;
import org.jsp.College_Management_App.Model.Student;
import org.jsp.College_Management_App.Repository.CourseRepository;
import org.jsp.College_Management_App.Repository.DepartmentRepository;
import org.jsp.College_Management_App.Repository.InstructorRepository;
import org.jsp.College_Management_App.Repository.StudentRepository;
import org.jsp.College_Management_App.Services.CourseService;
import org.jsp.College_Management_App.Services.DepartmentService;
import org.jsp.College_Management_App.Services.InstructorService;
import org.jsp.College_Management_App.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/all")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CountController {
	
	@Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/count")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Long>> getAllCoursesCount() {
       Long students=studentRepository.count();
       Long instructor=instructorRepository.count();
       long departments=departmentRepository.count();
       long courses=courseRepository.count();
    	
        List<Long> count=new ArrayList<Long>();
        count.add(students);
        count.add(instructor);
        count.add(departments);
        count.add(courses);

        return ResponseEntity.ok(count);
    }

}
