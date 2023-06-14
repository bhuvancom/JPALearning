package com.example.demo.springboot.repository;

import com.example.demo.springboot.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("from Teacher where lastName = :lastName and id = :id")
    Optional<Teacher> getTeacher(String lastName, Integer id);
}
