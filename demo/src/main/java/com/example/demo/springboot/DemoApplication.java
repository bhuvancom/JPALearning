package com.example.demo.springboot;

import com.example.demo.springboot.model.Course;
import com.example.demo.springboot.model.Teacher;
import com.example.demo.springboot.repository.CourseRepository;
import com.example.demo.springboot.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (1 == 2) { // If you need initial data, make it true
            Teacher teacher = new Teacher();
            teacher.setFirstName("Akash");
            teacher.setLastName("Srivastava");
            teacher = teacherRepository.save(teacher);
            Course course = new Course();
            course.setTitle("Some title");
            course.setTeacher(teacher);
            courseRepository.save(course);
        }
    }
}
