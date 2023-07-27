package com.kb.java.university.repository;

import com.kb.java.university.entity.Grade;
import com.kb.java.university.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

List<Grade> findAllByStudent(Student student);
}
