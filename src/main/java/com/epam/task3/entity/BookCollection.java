package com.epam.task3.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * This class stores all books of library.
 * {@code BookCollection} is a Singleton and
 * has synchronized method for multi-threading.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class BookCollection {
    private static volatile BookCollection instance;
    private List<Book> bookCollection = new LinkedList<>();

    /**
     * Private default constructor
     */
    private BookCollection() {
    }

    /**
     * The main part of Singleton implementation, that
     * returns the {@code BookCollection} instance.
     *
     * @return BookCollection
     */
    public static BookCollection getInstance() {
        if (instance == null)
            synchronized (BookCollection.class) {
                if (instance == null)
                    instance = new BookCollection();
            }
        return instance;
    }

    public List<Book> getCollection() {
        return bookCollection;
    }

    public synchronized void setCollection(List<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    public Book getBook(int index) {
        return bookCollection.get(index);
    }

    public synchronized void add(Book book) {
        bookCollection.add(book);
    }
}