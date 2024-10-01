package org.jsp.College_Management_App.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id") 
    @JsonIgnore
    private Department department;
    

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courses;
    
   
  
    
    

   
}
