package com.epam.task3.service.parser;

import com.epam.task3.dao.implementation.BookCollectionDAO;
import com.epam.task3.dao.implementation.VisitorCollectionDAO;
import com.epam.task3.entity.Book;
import com.epam.task3.entity.Status;
import com.epam.task3.entity.Visitor;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX handler for reading visitor.xml file and getting information about
 * library visitors.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class VisitorHandler extends DefaultHandler {
    private static final Logger LOG = Logger.getLogger(VisitorHandler.class);
    private VisitorCollectionDAO visitorCollectionDAO;
    private BookCollectionDAO bookCollectionDAO;
    private Visitor visitor;
    private Book book;
    private String element;

    /**
     * Default constructor
     */
    public VisitorHandler() {
        visitorCollectionDAO = new VisitorCollectionDAO();
        bookCollectionDAO = new BookCollectionDAO();
    }

    @Override
    public void startDocument(){
        LOG.debug("Started parsing of document - visitors.xml.xml");
    }

    @Override
    public void endDocument() throws SAXException {
        LOG.debug("Ended parsing of document - visitors.xml.xml");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;

        switch (element) {
            case "visitor":
                visitor = new Visitor();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "visitor":
                visitorCollectionDAO.save(visitor);
                break;

            case "book":
                book.setStatus(Status.ON_HANDS);
                visitor.addBook(book);
                break;
        }
        element = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (element) {
            case "cardnumber":
                visitor.setCardNumber(Integer.parseInt(new String(ch, start, length)));
                break;

            case "name":
                visitor.setName(new String(ch, start, length));
                break;

            case "surname":
                visitor.setSurname(new String(ch, start, length));
                break;

            case "bookid":
                book = bookCollectionDAO.findById(Long.parseLong(new String(ch, start, length)));
                break;
        }
    }
}
