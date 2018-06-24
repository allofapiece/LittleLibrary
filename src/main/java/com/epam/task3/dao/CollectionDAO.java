package com.epam.task3.dao;

/**
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public interface CollectionDAO<T> {
    /**
     * One of the CRUD operations, that finds one element.
     *
     * @param id id of necessary entity
     * @return T
     */
    T find(int id);

    /**
     * Saves object in collection.
     *
     * @param obj Object, that need to save.
     */
    void save(T obj);
}
