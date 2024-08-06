package org.prueba.entity;

import org.prueba.persistence.enums.StatusStudent;

public class StudentEntity {
    private int id;
    private String name;
    private String last_name;
    private String email;
    private StatusStudent status;

    private int registration;

    public StudentEntity() {}

    public StudentEntity(String name, String last_name, String email, StatusStudent status) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.status = status;
    }

    public StudentEntity(int id, String name, String last_name, String email, StatusStudent status) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.status = status;
    }


    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public String getEmail() {
        return this.email;
    }

    public StatusStudent getStatus() {
        return this.status;
    }

    public int getRegistration() {
        return this.registration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(StatusStudent status) {
        this.status = status;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Student {" +
                "id: " + this.id +
                ", name: " + this.name +
                ", last_name: " + this.last_name +
                ", email='" + this.email +
                ", status: " + this.status +
                '}' + "\n";
    }
}
