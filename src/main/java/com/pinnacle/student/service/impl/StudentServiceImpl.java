package com.pinnacle.student.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinnacle.student.exception.StudentNotFoundException;
import com.pinnacle.student.model.Student;
import com.pinnacle.student.repository.StudentRepository;
import com.pinnacle.student.service.SStudentService;

@Service
public class StudentServiceImpl implements SStudentService {

    @Autowired
    private StudentRepository repo;

    @Override
    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new StudentNotFoundException("Student with Id : " + id + " Not Found"));
    }

    @Override
    public void deleteStudentById(Long id) {
        repo.delete(getStudentById(id));
    }

    @Override
    public void updateStudent(Student student) {
        repo.save(student);
    }

    @Override
    public List<String> getAllCourses() {
        return repo.findAllCourses();
    }

    @Override
    public List<Student> findStudentsByCourse(String course) {
        return repo.findByCourse(course);
    }

    public List<Student> getStudentsWithBalance() {
        // Fetch students from the database along with their balance (or fee status)
        return repo.findAllWithBfess();
    }
    
 
}
