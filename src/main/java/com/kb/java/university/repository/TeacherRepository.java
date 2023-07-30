package com.kb.java.university.repository;

import com.kb.java.university.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>  {
    Optional<Teacher> findByLastName(String lastName);


}
