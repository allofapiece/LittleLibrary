package com.epam.task3.main;

import com.epam.task3.service.WorkingDayStarter;
import com.epam.task3.service.parser.BookHandler;
import com.epam.task3.service.parser.OrderHandler;
import com.epam.task3.service.parser.VisitorHandler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Starting application class.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class LittleLibrary {
    private static final Logger LOG = Logger.getLogger(LittleLibrary.class);
    private static final String BOOKS_XML_PATH = "src/main/resources/fixtures/books.xml";
    private static final String VISITORS_XML_PATH = "src/main/resources/fixtures/visitors.xml";
    private static final String ORDER_XML_PATH = "src/main/resources/fixtures/orders.xml";

    public static void main(String[] args) {
        loadMainData();
        new WorkingDayStarter().startDay();
    }

    /**
     * This method load the main information for running application. For
     * reading information from XML files was used SAX parser.
     */
    public static void loadMainData() {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();

        BookHandler bookHandler = new BookHandler();
        VisitorHandler visitorHandler = new VisitorHandler();
        OrderHandler orderHandler = new OrderHandler();

        try {
            SAXParser saxParser = parserFactory.newSAXParser();
            saxParser.parse(new File(BOOKS_XML_PATH), bookHandler);
            saxParser.parse(new File(VISITORS_XML_PATH), visitorHandler);
            saxParser.parse(new File(ORDER_XML_PATH), orderHandler);
        } catch (ParserConfigurationException | SAXException e) {
            LOG.error("Indicated a serious configuration error.", e);
        } catch (IOException e) {
            LOG.error("Can't parse files.", e);
        }
    }
}
