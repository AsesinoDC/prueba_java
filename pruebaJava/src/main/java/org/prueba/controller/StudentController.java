package org.prueba.controller;

import org.prueba.entity.StudentEntity;
import org.prueba.model.StudentModel;
import org.prueba.persistence.IModel.IStudentModel;

import java.util.ArrayList;

public class StudentController{
    private final IStudentModel studentModel = new StudentModel();

    public StudentEntity create(StudentEntity studentEntity) {
        return this.studentModel.create(studentEntity);
    }

    public Boolean delete(int id) {
        return this.studentModel.delete(id);
    }

    public ArrayList<StudentEntity> readAll() {
        return this.studentModel.readAll();
    }

    public StudentEntity readStudent(String id, String option) {
        return this.studentModel.readStudent(id, option);
    }

    public StudentEntity update(StudentEntity studentEntity, int id) {
        return this.studentModel.update(studentEntity,id);
    }

    public StudentEntity searchById(StudentEntity student,int id){
        return this.studentModel.searchById(student, id);
    }

    public Integer registerStudent(int id){
        return this.studentModel.registerStudent(id);
    }
}
