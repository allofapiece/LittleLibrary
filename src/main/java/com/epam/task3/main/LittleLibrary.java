package com.epam.task3.main;

import com.epam.task3.service.parser.BookHandler;
import com.epam.task3.service.parser.VisitorHandler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class LittleLibrary {
    private static final Logger LOG = Logger.getLogger(LittleLibrary.class);

    public static void main(String[] args) {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();

        BookHandler bookHandler = new BookHandler();
        VisitorHandler visitorHandler = new VisitorHandler();

        try {
            SAXParser saxParser = parserFactory.newSAXParser();
            saxParser.parse(new File("fixtures/books.xml"), bookHandler);
            saxParser.parse(new File("fixtures/visitors.xml"), visitorHandler);
        } catch (ParserConfigurationException | SAXException e) {
            LOG.error("Indicated a serious configuration error.", e);
        } catch (IOException e) {
            LOG.error("Can't parse files.", e);
        }
    }
}
