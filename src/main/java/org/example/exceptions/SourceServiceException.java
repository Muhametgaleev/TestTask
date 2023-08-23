package org.example.exceptions;

public class SourceServiceException extends RuntimeException {
    private SourceServiceException(String ex){
        super(ex);
    }

    public static SourceServiceException invalidSource(String nameOrigin, String fullNameOrigin, String nameDestination, String fullnameDestination) {
        return new SourceServiceException("Invalid source with name " + nameOrigin + " or " + nameDestination + " and full name " + fullNameOrigin + " or " + fullnameDestination);
    }
}