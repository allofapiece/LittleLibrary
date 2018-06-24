package com.epam.task3.entity;

/**
 * Status class is a property of {@link Book book entity}.
 * Values of this enumeration describes the
 * current state of the book.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public enum Status {

    /**
     * This book is a available at this
     * moment and can be taken by the visitors.xml.
     */
    AVAILABLE,

    /**
     * This book on hands now and
     * can't be used by other visitors.xml in current
     * reading session, if book owner wont transfer
     * this book to other visitor.
     */
    ON_HANDS,

    /**
     * This book was taken for
     * reading in the reading room of library
     */
    IN_READING_ROOM,
}
