package com.epam.task3.dao.implementation;

import com.epam.task3.dao.CollectionDAO;
import com.epam.task3.entity.Book;
import com.epam.task3.entity.BookCollection;
import com.epam.task3.exception.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Specific implementation of {@link CollectionDAO Collection DAO}
 * for book entity.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class BookCollectionDAO implements CollectionDAO {
    private static final Logger LOG = Logger.getLogger(BookCollectionDAO.class);

    /**
     * Default constructor
     */
    public BookCollectionDAO() {
    }

    /**
     * One of the CRUD operations.
     *
     * @param id
     * @return Collection
     * @throws EntityNotFoundException
     */
    public Book find(int id) throws EntityNotFoundException {
        List<Book> bookCollection = BookCollection.getInstance().getCollection();
        return bookCollection.get(id);
    }
}
