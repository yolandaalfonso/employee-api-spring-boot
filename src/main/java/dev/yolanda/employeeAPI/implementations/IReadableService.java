package dev.yolanda.employeeAPI.implementations;

import java.util.List;

public interface IReadableService<T> {
    public List<T> getEntities();
}
