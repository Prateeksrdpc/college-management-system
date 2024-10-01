package org.jsp.College_Management_App.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
   
    private Department department;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
   
    private Instructor instructor;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    @JsonIgnore
    private List<Student> students;
    
   
}