package org.prueba.persistence.cruds;

public interface ReadStudent <Entity, Id, Option>{
    public Entity readStudent(Id id, Option option);
}
