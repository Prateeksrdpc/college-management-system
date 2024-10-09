package org.jsp.College_Management_App.Controllers;


import org.jsp.College_Management_App.Model.Course;
import org.jsp.College_Management_App.Model.Department;
import org.jsp.College_Management_App.Model.Instructor;
import org.jsp.College_Management_App.Services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructors")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")

public class InstructorController {

	 @Autowired
	    private InstructorService instructorService;

	    // Get all instructors
	    @GetMapping
	    public ResponseEntity<List<Instructor> >getAllInstructors() {
	        List<Instructor> instructor=instructorService.getAllInstructors();
	        return ResponseEntity.ok(instructor);
	    }
	  
	    

	    // Get instructor by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
	        Optional<Instructor> instructor = instructorService.getInstructorById(id);

	        if (instructor.isPresent()) {
	            return ResponseEntity.ok(instructor.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Create a new instructor
	    @PostMapping
	    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor, @RequestParam Long departmentId) {
	        Instructor createdInstructor = instructorService.createInstructor(instructor, departmentId);
	        return ResponseEntity.ok(createdInstructor);
	    }
	    
	 // Assign department head
	    @PutMapping("/department/{departmentId}/head")
	    public ResponseEntity<Department> assignDepartmentHead(@PathVariable Long departmentId, @RequestParam Long instructorId) {
	        Department updatedDepartment = instructorService.assignDepartmentHead(departmentId, instructorId);
	        return ResponseEntity.ok(updatedDepartment);
	    }

	   

	    // Update an instructor
	    @PutMapping("/{id}")
	    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructorDetails) {
	        Instructor updatedInstructor = instructorService.updateInstructor(id, instructorDetails);
	        return ResponseEntity.ok(updatedInstructor);
	    }

	    // Delete an instructor
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
	        instructorService.deleteInstructor(id);
	        return ResponseEntity.noContent().build();
	    }
}

