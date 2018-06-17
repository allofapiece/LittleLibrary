package com.epam.task3.service.parser;

import com.epam.task3.dao.implementation.BookCollectionDAO;
import com.epam.task3.entity.Book;
import com.epam.task3.entity.Status;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookHandler extends DefaultHandler {
    private static final Logger LOG = Logger.getLogger(BookHandler.class);
    private BookCollectionDAO bookCollectionDAO;
    private Book book;
    private String element;

    public BookHandler() {
        bookCollectionDAO = new BookCollectionDAO();
    }

    @Override
    public void startDocument(){
        LOG.debug("Started parsing of document - books.xml");
    }

    @Override
    public void endDocument() throws SAXException {
        LOG.debug("Ended parsing of document - books.xml");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;

        switch (element) {
            case "book":
                book = new Book();
                book.setStatus(Status.AVAILABLE);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("book")) {
            bookCollectionDAO.save(book);
        }
        element = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (element) {
            case "ISBN":
                book.setISDN(new String(ch, start, length));
                break;

            case "title":
                book.setTitle(new String(ch, start, length));
                break;

            case "author":
                book.setAuthor(new String(ch, start, length));
                break;

            case "pages":
                book.setPages(Integer.parseInt(new String(ch, start, length)));
                break;
        }
    }
}
