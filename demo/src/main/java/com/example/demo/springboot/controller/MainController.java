package com.example.demo.springboot.controller;

import com.example.demo.springboot.model.Course;
import com.example.demo.springboot.model.Teacher;
import com.example.demo.springboot.repository.CourseRepository;
import com.example.demo.springboot.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/")
    public List<Teacher> home() {
        Map<String, String> map = new HashMap<>();
        return teacherRepository.findAll();
    }

    @GetMapping("/courses")
    public List<Course> allCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/abc")
    public ResponseEntity<Teacher> getT(@RequestParam("last") String lastName, @RequestParam("id") Integer id) {
        return teacherRepository.getTeacher(lastName, id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
