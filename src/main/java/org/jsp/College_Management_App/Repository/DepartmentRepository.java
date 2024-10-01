package org.jsp.College_Management_App.Repository;

import java.util.Optional;

import org.jsp.College_Management_App.Model.Department;
import org.jsp.College_Management_App.Model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findByHead(Instructor head);
}