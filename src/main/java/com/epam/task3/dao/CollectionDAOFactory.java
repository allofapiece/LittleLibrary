package com.epam.task3.dao;

import com.epam.task3.dao.implementation.BookCollectionDAO;
import com.epam.task3.dao.implementation.VisitorCollectionDAO;

/**
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class CollectionDAOFactory implements Factory {
    /**
     * @param type necessary entity type
     * @return CollectionDAO
     */
    public CollectionDAO create(String type) {
        switch (type) {
            case "book":
                return new BookCollectionDAO();

            case "visitor":
                return new VisitorCollectionDAO();

            default:
                return null;
        }
    }
}
