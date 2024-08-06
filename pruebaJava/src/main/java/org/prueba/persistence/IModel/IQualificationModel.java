package org.prueba.persistence.IModel;

import org.prueba.entity.CourseEntity;
import org.prueba.entity.QualificationEntity;
import org.prueba.persistence.cruds.*;

public interface IQualificationModel extends
        Create<QualificationEntity>,
        ReadAll<QualificationEntity>,
        ReadOnlyOne<QualificationEntity,Integer>,
        Update<QualificationEntity, Integer>,
        Delete<Integer>,
        SearchById<Integer, Integer, String>{
}
