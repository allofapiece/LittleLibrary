package com.epam.task3.dao;

import com.epam.task3.dao.implementation.BookCollectionDAO;

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

            default:
                return null;
        }
    }
}
