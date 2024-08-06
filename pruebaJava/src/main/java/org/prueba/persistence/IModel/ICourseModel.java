package org.prueba.persistence.IModel;

import org.prueba.entity.CourseEntity;
import org.prueba.persistence.cruds.*;

public interface ICourseModel extends
        Create<CourseEntity>,
        ReadAll<CourseEntity>,
        ReadOnlyOne<CourseEntity,Integer>,
        Update<CourseEntity, Integer>,
        Delete<Integer>,
        SearchById<Integer, Integer, String>{
}
