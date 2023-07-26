package com.kb.java.university.service;

import com.kb.java.university.entity.Grade;
import com.kb.java.university.repository.GradeRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }
    public Grade createGrade(Grade grade){

        return null;
    }

}
