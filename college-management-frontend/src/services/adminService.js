// src/services/adminService.js
import axios from 'axios';

const API_URL = 'http://localhost:9090/api/';
const token = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).token : null;

const instance = axios.create({
  baseURL: API_URL,
  headers: { Authorization: `Bearer ${token}` },
});

// Fetch department, student, instructor, and course counts
export const fetchStats = async () => {
  const [students, instructors, departments, courses] = await Promise.all([
    instance.get('students/count'),
    instance.get('instructors/count'),
    instance.get('departments/count'),
    instance.get('courses/count'),
  ]);

  return {
    students: students.data,
    instructors: instructors.data,
    departments: departments.data,
    courses: courses.data,
  };
};
