package com.epam.task3.entity;

import java.util.*;

/**
 * This class stores all books and visitors.xml information
 * of library. {@code LibraryCollection} is a Singleton
 * and has synchronized method for multi-threading.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class LibraryCollection {
    private static volatile LibraryCollection instance;

    private List<Book> bookCollection;
    private Map<Integer, Visitor> visitorCollection;
    private List<Order> orderCollection;

    /**
     * Private default constructor
     */
    private LibraryCollection() {
        bookCollection = new LinkedList<>();
        visitorCollection = new HashMap<>();
        orderCollection = new LinkedList<>();
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

    public List<Order> getOrderCollection() {
        return orderCollection;
    }

    public synchronized void setOrderCollection(List<Order> orderCollection) {
        this.orderCollection = orderCollection;
    }
}