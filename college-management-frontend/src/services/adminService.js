// src/services/adminService.js
import axios from 'axios';

const API_URL = 'http://localhost:9090/api/';

const token = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')).token : null; // Get token from localStorage

const instance = axios.create({
  baseURL: API_URL,
  headers: { Authorization: `Bearer ${token}` } // Include token in Authorization header
});

// Example service functions
export const getDepartmentCount = async () => {
  const response = await instance.get('departments/count');
    return response.data;
};

export const getStudentCount = async () => {
  const response = await instance.get('students/count');
    return response.data;
};

export const getInstructorCount = async () => {
  const response = await instance.get('instructors/count');
    return response.data;
};

export const getCourseCount = async () => {
  const response = await instance.get('courses/count');
    return response.data;
};
export const getEnrollemnetCount = async () => {
  const response = await instance.get('enrollments/count');
    return response.data;
};

// Other service functions as needed...
