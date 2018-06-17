package com.epam.task3.entity;

/**
 * The book entity is a part of subject area.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class Book {
    private int id;
    private String ISDN;
    private Status status;
    private String title;
    private String author;
    private int pages;

    /**
     * Default constructor
     */
    public Book() {
    }

    public Book(int id, String ISDN, Status status, String title, String author, int pages) {
        this.id = id;
        this.ISDN = ISDN;
        this.status = status;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getISDN() {
        return ISDN;
    }

    public void setISDN(String ISDN) {
        this.ISDN = ISDN;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

        if (id != book.id) return false;
        if (ISDN != null ? !ISDN.equals(book.ISDN) : book.ISDN != null) return false;
        if (status != book.status) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        return author != null ? author.equals(book.author) : book.author == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ISDN != null ? ISDN.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
