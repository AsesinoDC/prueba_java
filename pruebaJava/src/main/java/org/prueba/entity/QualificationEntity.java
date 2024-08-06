package org.prueba.entity;

public class QualificationEntity {
    private int id;
    private int id_courses;
    private int id_registration;
    private String description;
    private int quality;

    public QualificationEntity() {}

    public QualificationEntity(int id_courses, int id_registration,String description, int quality) {
        this.id_courses = id_courses;
        this.id_registration = id_registration;
        this.description = description;
        this.quality = quality;
    }

    public QualificationEntity(int id, int id_courses, int id_registration, String description, int quality) {
        this.id = id;
        this.id_courses = id_courses;
        this.id_registration = id_registration;
        this.description = description;
        this.quality = quality;
    }

    public int getId() {
        return this.id;
    }

    public int getId_courses() {
        return this.id_courses;
    }

    public int getId_registration() {
        return this.id_registration;
    }

    public String getDescription() {
        return this.description;
    }

    public int getQuality() {
        return this.quality;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_courses(int id_courses) {
        this.id_courses = id_courses;
    }

    public void setId_registration(int id_registration) {
        this.id_registration = id_registration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "Qualification {" +
                "id: " + this.id +
                ", id_courses: " + this.id_courses +
                ", id_registration: " + this.id_registration +
                ", description: " + this.description +
                ", quality: " + this.quality +
                '}' + "\n";
    }
}
