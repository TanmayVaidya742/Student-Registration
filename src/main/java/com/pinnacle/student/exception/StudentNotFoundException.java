package com.pinnacle.student.exception;

public class StudentNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StudentNotFoundException() {
        super();
    }

    public StudentNotFoundException(String customMessage) {
        super(customMessage);
    }
}
