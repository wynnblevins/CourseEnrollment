package com.wynnblevins.CourseEnrollment.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}