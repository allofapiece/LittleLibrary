package com.epam.task3.dao.implementation;

import com.epam.task3.dao.CollectionDAO;
import com.epam.task3.entity.LibraryCollection;
import com.epam.task3.entity.Visitor;

import java.util.Map;

/**
 * Specific implementation of {@link CollectionDAO Collection DAO}
 * for visitor entity.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class VisitorCollectionDAO implements CollectionDAO {

    /**
     * Default constructor
     */
    public VisitorCollectionDAO() {
    }

    /**
     * One of the CRUD operations.
     *
     * @param id
     * @return Collection
     */
    public Visitor find(int id){
        Map<Integer, Visitor> visitorCollection = LibraryCollection.getInstance().getVisitorCollection();
        return visitorCollection.get(id);
    }

    /**
     * Modification of find operation, when required
     * all items of collection.
     *
     * @return Map
     */
    public Map<Integer, Visitor> findAll() {
        return LibraryCollection.getInstance().getVisitorCollection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void save(Object obj) {
        Visitor visitor = (Visitor) obj;
        Map<Integer, Visitor> visitorCollection = LibraryCollection.getInstance().getVisitorCollection();
        visitorCollection.put(visitor.getCardNumber(), visitor);
    }
}
