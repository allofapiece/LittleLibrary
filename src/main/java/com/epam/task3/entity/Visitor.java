package com.epam.task3.entity;

import com.epam.task3.dao.implementation.OrderCollectionDAO;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Class describes peoples, who visit
 * library.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class Visitor extends Thread {
    private static final Logger LOG = Logger.getLogger(Visitor.class);
    private int cardNumber;
    private String firstname;
    private String surname;
    private List<Book> books;

    /**
     * Default constructor
     */
    public Visitor() {
        books = new LinkedList<>();
    }

    /**
     * This method is like the actions of a visitor. He tries to take all the
     * books he wants in his inventory and then starts reading them. Reading
     * occurs alternately with the help of the {@code join} method. After
     * reading the book, the visitor first checks to see if this book is
     * necessary for somebody, then returns the book and marks it as
     * {@code AVAILABLE}.
     */
    @Override
    public void run() {
        List<Order> orders = new OrderCollectionDAO().findAll();

        for (Order order : orders) {
            if (order.getVisitorCardNumber() == this.cardNumber) {
                order.start();
            }
        }

        while(!Thread.interrupted()) {
            if (!books.isEmpty()) {
                for (Book book : books) {
                    LOG.info("Visitor with card number = " + cardNumber +
                            " started reading the book with id = " + book.getBookId());
                    synchronized (book) {
                        book.start();
                        try {
                            book.join();
                        } catch (InterruptedException e) {
                            LOG.error("Visitor cann't read the book", e);
                        }
                        LOG.info("Visitor with card number = " + cardNumber +
                                " finished reading the book with id = " + book.getBookId());

                        for (Order order : orders) {
                            if (order.getISBN().equals(book.getISBN())) {
                                LOG.info("Visitor with card number = " + order.getVisitorCardNumber()
                                        + " wants the book from " + cardNumber);
                                try {
                                    book = order.getBookExchanger().exchange(book);
                                    LOG.info("Transfer from " + cardNumber + " to " + order.getVisitorCardNumber());
                                    break;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (book != null) {
                            LOG.info("NOBODY wants book with id = " + book.getBookId());
                            book.setStatus(Status.AVAILABLE);
                            LOG.info("Book with id = " + book.getBookId() + " returned to library");
                        }
                        books.remove(book);
                    }
                }
            }
        }
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public synchronized void setBooks(List<Book> books) {
        this.books = books;
    }

    public synchronized void addBook(Book book) {
        books.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        if (cardNumber != visitor.cardNumber) return false;
        if (firstname != null ? !firstname.equals(visitor.firstname) : visitor.firstname != null) return false;
        if (surname != null ? !surname.equals(visitor.surname) : visitor.surname != null) return false;
        return books != null ? books.equals(visitor.books) : visitor.books == null;
    }

    @Override
    public int hashCode() {
        int result = cardNumber;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }
}
