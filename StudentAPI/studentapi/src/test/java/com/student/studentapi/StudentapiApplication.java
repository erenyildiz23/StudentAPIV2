package com.student.studentapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.student.studentapi.Entity.StudentEntity;
import com.student.studentapi.repo.StudentRepository;

@SpringBootApplication
public class StudentapiApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(StudentapiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Başlangıç verilerini ekle
        studentRepository.save(new StudentEntity("Eren", "eren@example.com"));
        studentRepository.save(new StudentEntity("Ahmet", "ahmet@example.com"));
        studentRepository.save(new StudentEntity("Mehmet", "mehmet@example.com"));
    }
}
