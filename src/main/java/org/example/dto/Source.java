package org.example.dto;

public class Source{
    public Source( String name, String fullName){
        this.name = name;
        this.fullName = fullName;
    }
    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String fullName;
    private String name;

}
