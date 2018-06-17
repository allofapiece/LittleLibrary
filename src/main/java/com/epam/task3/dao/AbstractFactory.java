package com.epam.task3.dao;

/**
 * Abstract Factory class, that provides one of the
 * implementations of Data Access Object pattern with
 * {@code create} method
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class AbstractFactory implements Factory {
    /**
     * @param type type of dao implementation
     * @return Factory
     */
    @Override
    public Factory create(String type) {
        switch (type) {
            case "CollectionDAO":
                return new CollectionDAOFactory();

            default:
                return null;
        }
    }
}
