package dev.yolanda.employeeAPI.implementations;

import java.util.List;

public interface IGenericService<T> {
    public List<T> getEntities();
}
