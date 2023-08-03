package com.kb.java.university.dto;

import com.kb.java.university.entity.Student;

public class ResponseSingleton {
    private static final ResponseSingleton instance=new ResponseSingleton();

    private ResponseSingleton() {
    }
    public static ResponseSingleton getInstance(){

        return instance;
    }

    public static StudentResponse studentResponse(Student student) {
        return new StudentResponse(student.getName(), student.getLastName(), student.getEmail());
    }



//    private static ResponseSingleton INSTANCE = null;

//    public static ResponseSingleton getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new ResponseSingleton();
//        }
//        return INSTANCE;
//    }


}
