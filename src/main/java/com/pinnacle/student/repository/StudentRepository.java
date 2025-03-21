package com.pinnacle.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pinnacle.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Query to find all distinct courses
    @Query("SELECT DISTINCT s.course FROM Student s")
    List<String> findAllCourses();

    // Query to get students by their course
    List<Student> findByCourse(String course);
  
    @Query("SELECT s FROM Student s")
    List<Student> findAllWithBfess();


}
