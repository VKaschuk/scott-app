package my.scott.dao;


import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    T getById(int id);
}
