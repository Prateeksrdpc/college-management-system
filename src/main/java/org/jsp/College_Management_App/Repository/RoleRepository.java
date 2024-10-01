package org.jsp.College_Management_App.Repository;

import org.jsp.College_Management_App.Model.ERole;
import org.jsp.College_Management_App.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}