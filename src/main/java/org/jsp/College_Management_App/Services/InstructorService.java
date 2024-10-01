package org.jsp.College_Management_App.Services;

import org.jsp.College_Management_App.Model.Course;
import org.jsp.College_Management_App.Model.Department;
import org.jsp.College_Management_App.Model.Instructor;
import org.jsp.College_Management_App.Repository.CourseRepository;
import org.jsp.College_Management_App.Repository.DepartmentRepository;
import org.jsp.College_Management_App.Repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {


    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    
    private  CourseRepository CourseRepository;

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor createInstructor(Instructor instructor, Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            instructor.setDepartment(department.get());
            return instructorRepository.save(instructor);
        } else {
            throw new RuntimeException("Department not found with id: " + departmentId);
        }
    }

    public Instructor updateInstructor(Long id, Instructor instructorDetails) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(id);

        if (instructorOptional.isPresent()) {
            Instructor instructor = instructorOptional.get();
            instructor.setName(instructorDetails.getName());
            instructor.setEmail(instructorDetails.getEmail());
            return instructorRepository.save(instructor);
        } else {
            throw new RuntimeException("Instructor not found with id: " + id);
        }
    }
    // Assign instructor as department head
    public Department assignDepartmentHead(Long departmentId, Long instructorId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        department.setHead(instructor);
        return departmentRepository.save(department);
    }

   

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }}
