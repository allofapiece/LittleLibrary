package com.epam.task3.service.parser;

import com.epam.task3.dao.implementation.OrderCollectionDAO;
import com.epam.task3.dao.implementation.VisitorCollectionDAO;
import com.epam.task3.entity.Order;
import com.epam.task3.entity.Status;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.concurrent.atomic.AtomicLong;

/**
 * SAX handler for reading orders.xml file and getting information about
 * visitors orders.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class OrderHandler extends DefaultHandler {
    private static final Logger LOG = Logger.getLogger(OrderHandler.class);
    private OrderCollectionDAO orderCollectionDAO;
    private VisitorCollectionDAO visitorCollectionDAO;
    private Order order;
    private String element;
    private AtomicLong atomicLong;

    /**
     * Default constructor
     */
    public OrderHandler() {
        orderCollectionDAO = new OrderCollectionDAO();
        visitorCollectionDAO = new VisitorCollectionDAO();
        atomicLong = new AtomicLong();
    }

    @Override
    public void startDocument(){
        LOG.debug("Started parsing of document - orders.xml");
    }

    @Override
    public void endDocument() throws SAXException {
        LOG.debug("Ended parsing of document - orders.xml");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;

        switch (element) {
            case "order":
                order = new Order();
                order.setOrderId(atomicLong.getAndIncrement());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("order")) {
            orderCollectionDAO.save(order);
        }
        element = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (element) {
            case "visitor":
                order.setVisitorCardNumber(Integer.parseInt(new String(ch, start, length)));
                break;

            case "ISBN":
                order.setISBN(new String(ch, start, length));
                break;
        }
    }
}
