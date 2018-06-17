package com.epam.task3.dao.implementation;

import com.epam.task3.dao.CollectionDAO;
import com.epam.task3.entity.Book;
import com.epam.task3.entity.LibraryCollection;
import com.epam.task3.entity.Visitor;
import com.epam.task3.exception.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Specific implementation of {@link CollectionDAO Collection DAO}
 * for visitor entity.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class VisitorCollectionDAO implements CollectionDAO {
    private static final Logger LOG = Logger.getLogger(VisitorCollectionDAO.class);

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
     * @throws EntityNotFoundException
     */
    public Visitor find(int id) throws EntityNotFoundException {
        Map<Integer, Visitor> visitorCollection = LibraryCollection.getInstance().getVisitorCollection();
        return visitorCollection.get(id);
    }

    @Override
    public void save(Object obj) {
        Visitor visitor = (Visitor) obj;
        Map<Integer, Visitor> visitorCollection = LibraryCollection.getInstance().getVisitorCollection();
        visitorCollection.put(visitor.getCardNumber(), visitor);
    }
}
