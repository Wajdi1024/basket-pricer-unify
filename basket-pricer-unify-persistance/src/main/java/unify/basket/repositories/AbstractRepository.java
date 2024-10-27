package unify.basket.repositories;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository <T, ID>{

    Optional<T> findById(ID id);

    List<T> findAll();

    T insertOne(T t);

    T updateOne(T t);
    boolean deleteById(ID id);

    boolean delete(T t);

}
