package org.prueba.persistence.IModel;

import org.prueba.entity.StudentEntity;
import org.prueba.persistence.cruds.*;

public interface IStudentModel extends
        Create<StudentEntity>,
        ReadAll<StudentEntity>,
        ReadStudent<StudentEntity,String,String>,
        Update<StudentEntity, Integer>,
        Delete<Integer>,
        RegisterStudent<Integer,Integer>,
        SearchById<StudentEntity, StudentEntity,Integer>{
}
