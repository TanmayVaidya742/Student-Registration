package com.pinnacle.student.service;

import java.util.List;
import com.pinnacle.student.model.Student;

public interface SStudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    void deleteStudentById(Long id);
    void updateStudent(Student student);
    
    List<String> getAllCourses();
    List<Student> findStudentsByCourse(String course);
    List<Student> getStudentsWithBalance();
}
