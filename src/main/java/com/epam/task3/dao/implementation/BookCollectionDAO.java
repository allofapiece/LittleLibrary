package com.epam.task3.dao.implementation;

import com.epam.task3.dao.CollectionDAO;
import com.epam.task3.entity.Book;
import com.epam.task3.entity.LibraryCollection;
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
        List<Book> bookCollection = LibraryCollection.getInstance().getBookCollection();
        return bookCollection.get(id);
    }

    public Book findByISDN(String ISDN) {
        List<Book> bookCollection = LibraryCollection.getInstance().getBookCollection();
        Book targetBook = null;

        for (Book book : bookCollection) {
            if (book.getISDN().equals(ISDN)) {
                targetBook = book;
                break;
            }
        }

        return targetBook;
    }

    @Override
    public void save(Object obj) {
        Book book = (Book) obj;
        List<Book> bookCollection = LibraryCollection.getInstance().getBookCollection();
        bookCollection.add(book);
    }
}
