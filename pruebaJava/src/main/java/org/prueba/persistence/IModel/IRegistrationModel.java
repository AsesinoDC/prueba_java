package org.prueba.persistence.IModel;

import org.prueba.entity.RegistrationEntity;
import org.prueba.persistence.cruds.*;

public interface IRegistrationModel extends
        Create<RegistrationEntity>,
        ReadAll<RegistrationEntity>,
        ReadOnlyOne<RegistrationEntity,Integer>,
        Update<RegistrationEntity, Integer>,
        Delete<Integer>,
        SearchById<Integer, Integer, String>{
}
