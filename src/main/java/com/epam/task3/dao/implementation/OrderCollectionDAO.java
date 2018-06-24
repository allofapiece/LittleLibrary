package com.epam.task3.dao.implementation;

import com.epam.task3.dao.CollectionDAO;
import com.epam.task3.entity.LibraryCollection;
import com.epam.task3.entity.Order;

import java.util.List;

/**
 * Specific implementation of {@link CollectionDAO Collection DAO}
 * for visitor entity.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public class OrderCollectionDAO implements CollectionDAO {

    /**
     * Default constructor
     */
    public OrderCollectionDAO() {
    }

    /**
     * {@inheritDoc}
     */
    public Order find(int id) {
        List<Order> orderCollection = LibraryCollection.getInstance().getOrderCollection();
        Order targetOrder = null;

        for (Order order : orderCollection) {
            if (order.getOrderId() == id) {
                targetOrder = order;
                break;
            }
        }

        return targetOrder;
    }

    /**
     * Modification of find operation, when required
     * all items of collection.
     *
     * @return List
     */
    public List<Order> findAll() {
        return LibraryCollection.getInstance().getOrderCollection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void save(Object obj) {
        Order order = (Order) obj;
        List<Order> orderCollection = LibraryCollection.getInstance().getOrderCollection();
        orderCollection.add(order);
    }

    /**
     * Method, that delete specific order from collection.
     *
     * @param order Object, that need to delete.
     */
    public synchronized void delete(Order order) {
        LibraryCollection.getInstance().getOrderCollection().remove(order);
    }
}
