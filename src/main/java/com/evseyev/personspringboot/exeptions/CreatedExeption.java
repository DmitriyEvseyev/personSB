package com.evseyev.personspringboot.exeptions;

public class CreatedExeption extends RuntimeException {
    public CreatedExeption() {
        super();
    }

    public CreatedExeption(String message) {
        super(message);
    }
}