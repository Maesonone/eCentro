package ecentro;

import java.util.List;

public interface CRUD<T> {
    public T add(T elem);
    public List<T> query();
    public T get(String id);
    public T update(String id, T data);
    public T delete(String id);
}
