package com.kb.java.university.data;

import com.kb.java.university.entity.Grade;
import com.kb.java.university.entity.Student;
import com.kb.java.university.entity.Teacher;

import java.util.List;

public class InitData {
    static Student student1 = Student.builder().studentId(1L).name("Ntest1").lastName("Ltest1").email("email1@test.com").build();
    static Student student2 = Student.builder().studentId(2L).name("Ntest2").lastName("Ltest2").email("email2@test.com").build();
    static Student student3 = Student.builder().studentId(3L).name("Ntest3").lastName("Ltest3").email("email3@test.com").build();
    static Student student4 = Student.builder().studentId(4L).name("Ntest4").lastName("Ltest4").email("email4@test.com").build();
    static Teacher teacher1 = Teacher.builder().teacherId(1L).name("TNtest1").lastName("TLtest1").email("temail1@test.com").build();
    static Teacher teacher2 = Teacher.builder().teacherId(2L).name("TNtest2").lastName("TLtest2").email("temail2@test.com").build();
    static Teacher teacher3 = Teacher.builder().teacherId(3L).name("TNtest3").lastName("TLtest3").email("temail3@test.com").build();
    static Teacher teacher4 = Teacher.builder().teacherId(4L).name("TNtest4").lastName("TLtest4").email("temail4@test.com").build();
    static Grade grade1 = Grade.builder().gradeId(1L).gradeValue(5).student(student1).teacher(teacher1).build();
    static Grade grade2 = Grade.builder().gradeId(2L).gradeValue(6).student(student1).teacher(teacher2).build();
    static Grade grade3 = Grade.builder().gradeId(3L).gradeValue(3).student(student1).teacher(teacher3).build();
    static Grade grade4 = Grade.builder().gradeId(4L).gradeValue(4).student(student2).teacher(teacher4).build();
    static Grade grade5 = Grade.builder().gradeId(5L).gradeValue(5).student(student2).teacher(teacher1).build();
    static Grade grade6 = Grade.builder().gradeId(6L).gradeValue(6).student(student3).teacher(teacher2).build();
    static Grade grade7 = Grade.builder().gradeId(7L).gradeValue(4).student(student3).teacher(teacher3).build();
    static Grade grade8 = Grade.builder().gradeId(8L).gradeValue(4).student(student1).teacher(teacher4).build();

    public static List<Student> studentsList() {
        return List.of(student1, student2, student3, student4);
    }

    public static List<Teacher> teachersList() {
        return List.of(teacher1, teacher2, teacher3, teacher4);
    }

    public static List<Grade> gradesList() {
        return List.of(grade1, grade2, grade3, grade4, grade5, grade6, grade7, grade8);
    }

}
