package com.mylab.cromero.service;

public class Teacher {

    private Integer id;
    private String name;
    private String email;

    public Teacher(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Teacher(Integer id) {
        this.id = id;
        this.name=null;
        this.email=null;
    }

    public Teacher() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Teacher{");
        sb.append("email='").append(email).append('\'');
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
