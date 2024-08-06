package org.prueba.controller;

import org.prueba.entity.RegistrationEntity;
import org.prueba.model.RegistrationModel;
import org.prueba.persistence.IModel.IRegistrationModel;

import java.util.ArrayList;

public class RegistrationController{

    private final IRegistrationModel registrationModel = new RegistrationModel();

    public RegistrationEntity create(RegistrationEntity registrationEntity) {
        return this.registrationModel.create(registrationEntity);
    }

    public Boolean delete(int id) {
        return this.registrationModel.delete(id);
    }

    public ArrayList<RegistrationEntity> readAll() {
        return this.registrationModel.readAll();
    }

    public RegistrationEntity readOnlyOne(int id) {
        return this.registrationModel.readOnlyOne(id);
    }

    public Integer searchById(Integer id, String option) {
        return this.registrationModel.searchById(id, option);
    }

    public RegistrationEntity update(RegistrationEntity registrationEntity, int id) {
        return this.registrationModel.update(registrationEntity, id);
    }
}
