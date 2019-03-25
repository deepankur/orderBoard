package service;

import model.Order;
import model.OrderDetails;
import model.OrderPrice;
import util.OrderComparator;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class OrderBoardImpl implements OrderBoard {
    private final List<Order> orders = new ArrayList<>();
    private final Comparator<OrderDetails> byTypeAndPrice = new OrderComparator();


    @Override
    public void register(Order order) {
        orders.add(order);
    }

    @Override
    public void cancel(Order order) {
        orders.remove(order);
    }

    @Override
    public List<OrderDetails> getOrders() {
        List<OrderDetails> sortedOrders = orders.stream().
                collect(groupingBy(OrderPrice::forOrder, mapping(Order::quantity, toList()))).
                entrySet().stream().
                map(toOrderDetails()).
                sorted(byTypeAndPrice).
                collect(toList());

        summarizeOrderType(sortedOrders, Order.Type.Buy);
        summarizeOrderType(sortedOrders, Order.Type.Sell);


        return sortedOrders;
    }


    private Function<Map.Entry<OrderPrice, List<Quantity<Mass>>>, OrderDetails> toOrderDetails() {
        return entry -> new OrderDetails(entry.getKey(), entry.getValue());
    }

    private void summarizeOrderType(List<OrderDetails> orderSummaries, Order.Type type) {
        orderSummaries.stream().filter(o -> o.orderType() == type).forEach(System.out::println);
    }

}
