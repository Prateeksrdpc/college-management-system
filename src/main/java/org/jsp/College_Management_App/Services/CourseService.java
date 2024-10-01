package org.jsp.College_Management_App.Services;

import org.jsp.College_Management_App.Exception.ResourceNotFoundException;
import org.jsp.College_Management_App.Model.Course;
import org.jsp.College_Management_App.Model.Department;
import org.jsp.College_Management_App.Model.Instructor;
import org.jsp.College_Management_App.Model.Student;
import org.jsp.College_Management_App.Repository.CourseRepository;
import org.jsp.College_Management_App.Repository.DepartmentRepository;
import org.jsp.College_Management_App.Repository.InstructorRepository;
import org.jsp.College_Management_App.Repository.StudentRepository;
import org.jsp.College_Management_App.payload.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

	@Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private StudentRepository studentRepository;
    public List<Course> getCoursesByInstructorId(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    public List<Course> getCoursesByDepartmentId(Long departmentId) {
        return courseRepository.findByDepartmentId(departmentId);
    }
    public List<Course> getCoursesByStudentId(Long StudentId) {
        return studentRepository.findCoursesByStudentId(StudentId);
    }
    


    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(CourseRequest request) {
    	 
        Instructor instructor = instructorRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Check if the instructor is the department head
        if (department.getHead() != null && department.getHead().equals(instructor)) {
            if (instructor.getCourses().size() >= 1) {
                throw new RuntimeException("Department head can only teach one course.");
            }
        }
        Course course=new Course();
        course.setName(request.getName());
        course.setInstructor(instructor);
        course.setDepartment(department);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Instructor inst=instructorRepository.findById(request.getInstructorId()).orElseThrow(() -> new RuntimeException("instructor not found"));;
        Department dept=departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new RuntimeException("department  not found"));
        course.setName(request.getName());
        course.setInstructor(inst);
        course.setDepartment(dept);

        return courseRepository.save(course);
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public void deleteCourse(Long courseId) {
    	Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // Remove associations with students
        for (Student student : course.getStudents()) {
            student.getCourses().remove(course);
        }
        course.getStudents().clear();

        courseRepository.save(course); // Save the course after clearing associations
        courseRepository.delete(course);
    }
}