import React, { useEffect, useState } from 'react';
import AdminSidebar from '../components/AdminSidebar';
import Card from '../components/Card';
import { getDepartmentCount, getStudentCount, getInstructorCount, getCourseCount } from '../services/adminService';

function AdminDashboard() {
  const [stats, setStats] = useState({
    students: 0,
    instructors: 0,
    departments: 0,
    courses: 0,
    enrollements :0,
  });

  useEffect(() => {
    async function fetchStats() {
      const students = await getStudentCount();
      const instructors = await getInstructorCount();
      const departments = await getDepartmentCount();
      const courses = await getCourseCount();
  
      setStats({ students, instructors, departments, courses });
    }
    fetchStats();
  }, []);

  return (
    <div className="flex">
      <AdminSidebar />
      <div className="flex-1 p-6 bg-gray-100">
        <h1 className="text-3xl font-bold text-gray-800 mb-6">Admin Dashboard</h1>
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
          <Card title="Number of Students" count={stats.students} />
          <Card title="Number of Instructors" count={stats.instructors} />
          <Card title="Number of Departments" count={stats.departments} />
          <Card title="Number of Courses" count={stats.courses} />
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
