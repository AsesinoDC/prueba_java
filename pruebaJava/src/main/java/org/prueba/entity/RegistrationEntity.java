package org.prueba.entity;

public class RegistrationEntity {
    private int id;
    private int id_student;
    private int id_courses;

    public RegistrationEntity() {}

    public RegistrationEntity(int id_student, int id_courses) {
        this.id_student = id_student;
        this.id_courses = id_courses;
    }

    public RegistrationEntity(int id, int id_student, int id_courses) {
        this.id = id;
        this.id_student = id_student;
        this.id_courses = id_courses;
    }

    public int getId() {
        return this.id;
    }

    public int getId_student() {
        return this.id_student;
    }

    public int getId_courses() {
        return this.id_courses;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public void setId_courses(int id_courses) {
        this.id_courses = id_courses;
    }

    @Override
    public String toString() {
        return "Registration {" +
                "id: " + this.id +
                ", id_student: " + this.id_student +
                ", id_courses: " + this.id_courses +
                '}' + "\n";
    }
}
