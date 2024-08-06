package org.prueba.controller;

import org.prueba.entity.QualificationEntity;
import org.prueba.model.QualificationModel;
import org.prueba.persistence.IModel.IQualificationModel;

import java.util.ArrayList;

public class QualificationController {
    private final IQualificationModel qualificationModel = new QualificationModel();

    public QualificationEntity create(QualificationEntity qualificationEntity) {
        return this.qualificationModel.create(qualificationEntity);
    }

    public Boolean delete(int id) {
        return this.qualificationModel.delete(id);
    }

    public ArrayList<QualificationEntity> readAll() {
        return this.qualificationModel.readAll();
    }

    public QualificationEntity readOnlyOne(int id) {
        return this.qualificationModel.readOnlyOne(id);
    }

    public Integer searchById(int id, String option) {
        return this.qualificationModel.searchById(id, option);
    }

    public QualificationEntity update(QualificationEntity qualificationEntity, int id) {
        return this.qualificationModel.update(qualificationEntity, id);
    }
}
