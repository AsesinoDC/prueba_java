package org.prueba.controller;

import org.prueba.entity.CourseEntity;
import org.prueba.model.CourseModel;
import org.prueba.persistence.IModel.ICourseModel;

import java.util.ArrayList;

public class CourseController {
    private final ICourseModel courseModel = new CourseModel();

    public CourseEntity create(CourseEntity courseEntity) {
        return this.courseModel.create(courseEntity);
    }
    public Boolean delete(int id) {
        return this.courseModel.delete(id);
    }

    public ArrayList<CourseEntity> readAll() {
        return this.courseModel.readAll();
    }

    public CourseEntity readOnlyOne(int id) {
        return this.courseModel.readOnlyOne(id);
    }

    public Integer searchById(int id, String option) {
        return this.courseModel.searchById(id, option);
    }

    public CourseEntity update(CourseEntity courseEntity, int id) {
        return this.courseModel.update(courseEntity, id);
    }
}
