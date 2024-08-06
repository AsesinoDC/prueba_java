package org.prueba.persistence.cruds;

public interface SearchById <Search,Id,Option>{
    public Search searchById(Id id, Option option);
}
