package com.pinnacle.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinnacle.student.exception.StudentNotFoundException;
import com.pinnacle.student.model.Student;
import com.pinnacle.student.service.SStudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private SStudentService service;

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Student> students = service.getAllStudents();
        model.addAttribute("list", students);
        return "homePage"; // Return homePage.html
    }

    @GetMapping("/register")
    public String showRegistration() {
        return "registerStudentPage"; // Page for registration
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        Student savedStudent = service.saveStudent(student);
        Long id = savedStudent.getId();
        redirectAttributes.addFlashAttribute("message", "Record with id : '" + id + "' is saved successfully!");
        return "redirect:/student/getAllStudents"; // Redirect after saving
    }

    @GetMapping("/getAllStudents")
    public String getAllStudents(@RequestParam(value = "message", required = false) String message, Model model) {
        List<Student> students = service.getAllStudents();
        model.addAttribute("list", students);
        model.addAttribute("message", message);
        return "allStudentsPage"; // Page for all students
    }
    
    @GetMapping("/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Long id
            ) {
       String page = null; 
       try {
       Student student = service.getStudentById(id);
       model.addAttribute("student", student);
       page="editStudentPage";
       } catch (StudentNotFoundException e) {
           e.printStackTrace();
           attributes.addAttribute("message", e.getMessage());
           page="redirect:getAllStudents";
       }
       return page; 
    }

    @PostMapping("/update")
    public String updateStudent(
            @ModelAttribute Student student,
            RedirectAttributes attributes
            ) {
       service.updateStudent(student);
       Long id = student.getId();
       attributes.addAttribute("message", "Student with id: '"+id+"' is updated successfully !");
       return "redirect:getAllStudents";
    }

    @GetMapping("/delete")
    public String deleteStudent(
            @RequestParam Long id,
            RedirectAttributes attributes
            ) {
        try {
        service.deleteStudentById(id);
        attributes.addAttribute("message", "Student with Id : '"+id+"' is removed successfully!");
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:getAllStudents";
    }

    @GetMapping("/selectCourse")
    public String selectCourse(Model model) {
        List<String> courses = service.getAllCourses();  // Fetch all courses
        model.addAttribute("courses", courses);
        return "courseSelect";  // Page for course selection
    }

    @GetMapping("/filterByCourseDetails")
    public String filterByCourseDetails(@RequestParam("course") String course, Model model) {
        List<Student> studentsByCourse = service.findStudentsByCourse(course);
        model.addAttribute("list", studentsByCourse);
        return "studentList";  // Page showing students by course
    }
    
    @GetMapping("/balance")
//    public String showStudentBalance(Model model) {
//        List<Student> studentsWithBalance = service.getStudentsWithBalance();
//        model.addAttribute("studentsWithBalance", studentsWithBalance);
//        System.out.println(studentsWithBalance);
//        return "studentBalance";  // This will be the name of the new Thymeleaf template
//    }
    public String getStudentsWithBalance(Model model) {
        List<Student> studentsWithBalance = service.getStudentsWithBalance();      
        studentsWithBalance.forEach(student -> {
            student.getBfees();
        });
        model.addAttribute("studentsWithBalance", studentsWithBalance);
        return "studentBalance"; 
    }
    

}
