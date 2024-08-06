package org.prueba.persistence.cruds;

public interface Update <Entity, Id>{
    public Entity update(Entity entity, Id id);
}
