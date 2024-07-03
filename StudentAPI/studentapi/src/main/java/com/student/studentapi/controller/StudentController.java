package com.student.studentapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.studentapi.Entity.StudentEntity;
import com.student.studentapi.dto.StudentDTO;
import com.student.studentapi.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDetails) {
        studentDetails.setId(id);
        return studentService.saveStudent(studentDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PatchMapping("/{id}")
    public StudentDTO updateStudentEmail(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        StudentDTO student = studentService.getStudentById(id);
        if (updates.containsKey("email")) {
            student.setEmail(updates.get("email"));
        }
        return studentService.saveStudent(student);
    }
}
