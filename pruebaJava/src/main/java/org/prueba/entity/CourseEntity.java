package org.prueba.entity;

public class CourseEntity {
    private int id;
    private String name_course;
    private int id_student;

    public CourseEntity() {}

    public CourseEntity(String name_course, int id_student) {
        this.name_course = name_course;
        this.id_student = id_student;
    }

    public CourseEntity(int id, String name_course, int id_student) {
        this.id = id;
        this.name_course = name_course;
        this.id_student = id_student;
    }

    public int getId() {
        return this.id;
    }

    public String getName_course() {
        return this.name_course;
    }

    public int getId_student() {
        return this.id_student;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_course(String name_course) {
        this.name_course = name_course;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    @Override
    public String toString() {
        return "Course {" +
                "id: " + this.id +
                ", name_course: " + this.name_course +
                ", id_student: " + this.id_student +
                '}' + "\n";
    }
}
