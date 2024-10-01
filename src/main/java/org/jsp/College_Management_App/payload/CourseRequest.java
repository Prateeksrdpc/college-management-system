package org.jsp.College_Management_App.payload;

import java.util.Set;

import org.jsp.College_Management_App.Model.Role;
import org.jsp.College_Management_App.Model.User;

import lombok.Data;

@Data
public class CourseRequest {
	 private String name;
	    private Long departmentId;
	    private Long instructorId;
}
