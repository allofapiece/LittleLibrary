package com.epam.task3.entity;

import com.epam.task3.dao.implementation.BookCollectionDAO;
import com.epam.task3.dao.implementation.OrderCollectionDAO;
import com.epam.task3.dao.implementation.VisitorCollectionDAO;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;

/**
 * Class describes visitors orders,
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class Order extends Thread {
    private static final Logger LOG = Logger.getLogger(Order.class);
    private long orderId;
    private int visitorCardNumber;
    private String ISBN;
    private Exchanger<Book> bookExchanger;

    /**
     * Default constructor
     */
    public Order() {
        bookExchanger = new Exchanger<>();
    }

    /**
     * This method is like an assistant who is trying to find a book for a
     * visitor. If there is no book available, then she searches for it from
     * other visitors. When there is such a visitor, she waits while he reads
     * and uses the {@link Exchanger} class to transfer the book to the right
     * person.
     */
    @Override
    public void run() {
        BookCollectionDAO bookCollectionDAO = new BookCollectionDAO();
        VisitorCollectionDAO visitorCollectionDAO = new VisitorCollectionDAO();
        OrderCollectionDAO orderCollectionDAO = new OrderCollectionDAO();
        List<Book> books = bookCollectionDAO.findAll();
        Map<Integer, Visitor> visitors = visitorCollectionDAO.findAll();
        Visitor orderingVisitor = visitors.get(this.visitorCardNumber);

        for (Book book : books) {
            if (book.getISBN().equals(this.ISBN) && book.getStatus() == Status.AVAILABLE) {
                book.setStatus(Status.ON_HANDS);
                orderingVisitor.addBook(book);
                orderCollectionDAO.delete(this);
                return;
            }
        }

        for (Map.Entry<Integer, Visitor> visitorEntry : visitors.entrySet()) {
            Visitor visitor = visitorEntry.getValue();
            for (Book book : visitor.getBooks()) {
                if (book.getISBN().equals(this.ISBN)) {
                    try {
                        book = bookExchanger.exchange(null);
                        visitor.addBook(book);
                        orderCollectionDAO.delete(this);
                        return;
                    } catch (InterruptedException e) {
                        LOG.error("Changing error.", e);
                    }
                }
            }
        }
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getVisitorCardNumber() {
        return visitorCardNumber;
    }

    public void setVisitorCardNumber(int visitorCardNumber) {
        this.visitorCardNumber = visitorCardNumber;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Exchanger<Book> getBookExchanger() {
        return bookExchanger;
    }

    public void setBookExchanger(Exchanger<Book> bookExchanger) {
        this.bookExchanger = bookExchanger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (visitorCardNumber != order.visitorCardNumber) return false;
        return ISBN != null ? ISBN.equals(order.ISBN) : order.ISBN == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + visitorCardNumber;
        result = 31 * result + (ISBN != null ? ISBN.hashCode() : 0);
        return result;
    }
}
