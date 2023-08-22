package org.example.exceptions;

public class FooException extends RuntimeException {
    public FooException(String ex){
        System.out.println(ex);
    }
}
