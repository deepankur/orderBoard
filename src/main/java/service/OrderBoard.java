package service;

import model.Order;
import model.OrderDetails;

import java.util.List;

public interface OrderBoard {

    void register(Order order);

    void cancel(Order order);

    List<OrderDetails> getOrders();
}
