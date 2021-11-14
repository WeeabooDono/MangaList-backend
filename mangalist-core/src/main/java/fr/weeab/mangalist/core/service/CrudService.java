package fr.weeab.mangalist.core.service;

import java.util.Collection;

public interface CrudService<E> {

    Collection<E> findAll();

    E findById(Long id);

    E save(E entity);

    E update(Long id,E entity);

    void deleteById(Long id);
}
