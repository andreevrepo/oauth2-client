package net.takedata.spring.service;

import java.util.List;
import java.util.Optional;

public interface ServiceI<E> {
    void add(E e);
    void delete(long id);
    void update(E e);
    Optional<E> get(long id);
    E get(String field);
    List<E> getAll();
}

