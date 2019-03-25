package util;

import model.Order;
import model.OrderDetails;

import java.util.Comparator;

import static java.util.Comparator.comparing;

public class OrderComparator implements Comparator<OrderDetails> {
    static private final Comparator<OrderDetails> byOrderType = comparing(OrderDetails::orderType);
    static private final Comparator<OrderDetails> byPriceAscending = comparing(OrderDetails::pricePerKg);
    static private final Comparator<OrderDetails> byPriceDescending = byPriceAscending.reversed();

    @Override
    public int compare(OrderDetails left, OrderDetails right) {
        if (left.orderType() != right.orderType()) {
            return byOrderType.compare(left, right);
        }

        if (left.orderType() == Order.Type.Sell) {
            return byPriceAscending.compare(left, right);
        }

        if (left.orderType() == Order.Type.Buy) {
            return byPriceDescending.compare(left, right);
        }

        return 0;
    }
}
