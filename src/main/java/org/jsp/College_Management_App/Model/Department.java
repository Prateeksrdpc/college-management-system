package org.jsp.College_Management_App.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data // Lombok: Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok: Generates no-args constructor
@AllArgsConstructor // Lombok: Generates all-args constructor
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
   
    private List<Instructor> instructors;

    // Department Head (One instructor can be the head)
    @OneToOne
    
    private Instructor head;
}
