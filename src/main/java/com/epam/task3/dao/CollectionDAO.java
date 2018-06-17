package com.epam.task3.dao;

import com.epam.task3.exception.EntityNotFoundException;

/**
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public interface CollectionDAO<T> {
    /**
     * @param id id of necessary entity
     * @return T
     * @throws EntityNotFoundException
     */
    T find(int id) throws EntityNotFoundException;
}
