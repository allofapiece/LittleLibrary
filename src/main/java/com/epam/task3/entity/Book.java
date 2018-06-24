package com.epam.task3.entity;

import org.apache.log4j.Logger;

/**
 * The book entity is a part of subject area.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class Book extends Thread{
    private static final double READING_TIME_COEFFICIENT = 10;
    private static final Logger LOG = Logger.getLogger(Book.class);
    private long bookId;
    private String ISBN;
    private Status status;
    private String title;
    private String author;
    private int pages;

    /**
     * Default constructor
     */
    public Book() {
    }

    /**
     * Implementation of actions of multi-threading. Sleep method
     * is a reading process of the book from real life. The time
     * is determined by multiplying the number of pages by the
     * {@code READING_TIME_COEFFICIENT}
     */
    @Override
    public void run() {
        LOG.info("Started reading the book with id = " + bookId);
        try {
            sleep((long) (pages * READING_TIME_COEFFICIENT));
        } catch (InterruptedException e) {
            LOG.error("Can't read the book with id = " + bookId, e);
        }
        LOG.info("Finished reading the book with id = " + bookId);
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Status getStatus() {
        return status;
    }

    public synchronized void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (ISBN != null ? !ISBN.equals(book.ISBN) : book.ISBN != null) return false;
        if (status != book.status) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        return author != null ? author.equals(book.author) : book.author == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (ISBN != null ? ISBN.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + pages;
        return result;
    }
}
