package com.epam.task3.entity;

import java.util.List;

/**
 * Class describes peoples, who visit
 * library.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class Visitor {
    private int cardNumber;
    private String name;
    private String surname;
    private List<Book> books;

    /**
     * Default constructor
     */
    public Visitor() {
    }

    public Visitor(int cardNumber, String name, String surname, List<Book> books) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.surname = surname;
        this.books = books;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        if (cardNumber != visitor.cardNumber) return false;
        if (name != null ? !name.equals(visitor.name) : visitor.name != null) return false;
        if (surname != null ? !surname.equals(visitor.surname) : visitor.surname != null) return false;
        return books != null ? books.equals(visitor.books) : visitor.books == null;
    }

    @Override
    public int hashCode() {
        int result = cardNumber;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }
}
