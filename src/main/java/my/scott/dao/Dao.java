package my.scott.dao;


import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> getAll();
    Optional<T> getById(int id);
    int insert(T t);
    boolean update(T t);
    boolean delete(T t);
    
}
