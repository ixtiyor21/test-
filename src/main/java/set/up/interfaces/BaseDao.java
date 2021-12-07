package set.up.interfaces;

public interface BaseDao<T> {
    void create(T obj);

    void delete(T obj);

    void list();
}
