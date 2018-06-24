package com.epam.task3.dao.implementation;

import com.epam.task3.dao.CollectionDAO;
import com.epam.task3.entity.Book;
import com.epam.task3.entity.LibraryCollection;

import java.util.List;

/**
 * Specific implementation of {@link CollectionDAO Collection DAO}
 * for book entity.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class BookCollectionDAO implements CollectionDAO {

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
     */
    public Book find(int id){
        List<Book> bookCollection = LibraryCollection.getInstance().getBookCollection();
        return bookCollection.get(id);
    }

    /**
     * Modification of find operation, when required
     * find book with specific id.
     *
     * @return List
     */
    public Book findById(long id) {
        List<Book> bookCollection = LibraryCollection.getInstance().getBookCollection();
        Book targetBook = null;

        for (Book book : bookCollection) {
            if (book.getBookId() == id) {
                targetBook = book;
                break;
            }
        }

        return targetBook;
    }

    /**
     * Modification of find operation, when required
     * all items of collection.
     *
     * @return List
     */
    public List<Book> findAll() {
        return LibraryCollection.getInstance().getBookCollection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void save(Object obj) {
        Book book = (Book) obj;
        List<Book> bookCollection = LibraryCollection.getInstance().getBookCollection();
        bookCollection.add(book);
    }
}
