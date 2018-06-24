package com.epam.task3.service;

import com.epam.task3.dao.implementation.VisitorCollectionDAO;
import com.epam.task3.entity.Visitor;

import java.util.Iterator;
import java.util.Map;

/**
 * Class is like a working day of library.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class WorkingDayStarter {
    private VisitorCollectionDAO visitorCollectionDAO;

    /**
     * Default constructor
     */
    public WorkingDayStarter() {
        visitorCollectionDAO = new VisitorCollectionDAO();
    }

    /**
     * The main method of this class, that starts the working day.
     * All threads are started here.
     */
    public void startDay() {
        Map<Integer, Visitor> visitors = visitorCollectionDAO.findAll();

        Iterator it = visitors.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry visitorEntry = (Map.Entry) it.next();
            Visitor visitor = (Visitor) visitorEntry.getValue();
            visitor.start();
        }
    }
}
