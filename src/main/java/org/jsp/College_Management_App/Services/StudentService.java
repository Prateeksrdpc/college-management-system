package org.jsp.College_Management_App.Services;

import org.jsp.College_Management_App.Model.Course;
import org.jsp.College_Management_App.Model.Student;
import org.jsp.College_Management_App.Repository.CourseRepository;
import org.jsp.College_Management_App.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
	  @Autowired
	    private StudentRepository studentRepository;

	    @Autowired
	    private CourseRepository courseRepository;

	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    public Optional<Student> getStudentById(Long id) {
	        return studentRepository.findById(id);
	    }

	    public Student createStudent(Student student) {
	    List<Course>course=student.getCourses();
	    List<Course> addcourse=new ArrayList<Course>();
	    Student s=new Student();
	    for(Course c:course)
	    {
	    	Course cou=courseRepository.findById(c.getId()).get();
	    	addcourse.add(cou);
	    }
	    s.setCourses(addcourse);
	    s.setEmail(student.getEmail());
	    s.setName(student.getName());
	   
	        return studentRepository.save(s);
	    }

	    public Student updateStudent(Long id, Student studentDetails) {
	        Optional<Student> studentOptional = studentRepository.findById(id);

	        if (studentOptional.isPresent()) {
	            Student student = studentOptional.get();
	            student.setName(studentDetails.getName());
	            student.setEmail(studentDetails.getEmail());
	            student.setCourses(studentDetails.getCourses());
	            return studentRepository.save(student);
	        } else {
	            throw new RuntimeException("Student not found with id: " + id);
	        }
	    }

	    public void deleteStudent(Long id) {
	        studentRepository.deleteById(id);
	    }

	    public Student enrollInCourse(Long studentId, Long courseId) {
	        Student student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new RuntimeException("Student not found"));

	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> new RuntimeException("Course not found"));

	        student.getCourses().add(course);
	        return studentRepository.save(student);
	    }
}