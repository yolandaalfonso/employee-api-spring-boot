package dev.yolanda.employeeAPI.implementations;

import java.util.List;

public interface IGenericService<T, S> {
    public List<T> getEntities();
    public T storeEntity(S dto);
    //public T showById(Long id);
    List<T> getEntitiesOrdered();
    List<T> getPendientes();
    T marcarComoAtendida(Long id, String tecnico);
}
