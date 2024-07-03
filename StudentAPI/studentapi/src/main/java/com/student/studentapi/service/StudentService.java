package com.student.studentapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.studentapi.Entity.StudentEntity;
import com.student.studentapi.dto.StudentDTO;
import com.student.studentapi.exception.ResourceNotFoundException;
import com.student.studentapi.repo.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        StudentEntity student = convertDTOToEntity(studentDTO);
        StudentEntity savedStudent = studentRepository.save(student);
        return convertEntityToDTO(savedStudent);
    }

    public StudentDTO getStudentById(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));
        return convertEntityToDTO(student);
    }

    public void deleteStudent(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));
        studentRepository.delete(student);
    }

    private StudentDTO convertEntityToDTO(StudentEntity student) {
        return new StudentDTO(student.getId(), student.getName(), student.getEmail());
    }

    private StudentEntity convertDTOToEntity(StudentDTO studentDTO) {
        return new StudentEntity(studentDTO.getId(), studentDTO.getName(), studentDTO.getEmail());
    }
}
