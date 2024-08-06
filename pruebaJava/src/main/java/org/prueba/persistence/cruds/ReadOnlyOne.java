package org.prueba.persistence.cruds;

public interface ReadOnlyOne <Entity, Id>{
    public Entity readOnlyOne(Id id);
}
