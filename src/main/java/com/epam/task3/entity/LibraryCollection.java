package com.epam.task3.entity;

import java.util.*;

/**
 * This class stores all books and visitors information
 * of library. {@code LibraryCollection} is a Singleton
 * and has synchronized method for multi-threading.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class LibraryCollection {
    private static volatile LibraryCollection instance;

    private List<Book> bookCollection = new LinkedList<>();
    private Map<Integer, Visitor> visitorCollection = new HashMap<>();

    /**
     * Private default constructor
     */
    private LibraryCollection() {
    }

    /**
     * The main part of Singleton implementation, that
     * returns the {@code LibraryCollection} instance.
     *
     * @return LibraryCollection
     */
    public static LibraryCollection getInstance() {
        if (instance == null)
            synchronized (LibraryCollection.class) {
                if (instance == null)
                    instance = new LibraryCollection();
            }
        return instance;
    }

    public List<Book> getBookCollection() {
        return bookCollection;
    }

    public synchronized void setBookCollection(List<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    public Map<Integer, Visitor> getVisitorCollection() {
        return visitorCollection;
    }

    public synchronized void setVisitorCollection(Map<Integer, Visitor> visitorCollection) {
        this.visitorCollection = visitorCollection;
    }

    public Book getBook(int index) {
        return bookCollection.get(index);
    }

    public synchronized void add(Book book) {
        bookCollection.add(book);
    }
}