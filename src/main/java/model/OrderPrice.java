package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

final public class OrderPrice {
    private final PricePerKg pricePerKg;
    private final Order.Type type;

    public OrderPrice(PricePerKg pricePerKg, Order.Type type) {
        this.pricePerKg = pricePerKg;
        this.type = type;
    }

    public static OrderPrice forOrder(Order order) {
        return new OrderPrice(order.pricePerKg(), order.type());
    }

    public PricePerKg pricePerKg() {
        return pricePerKg;
    }

    public Order.Type orderType() {
        return type;
    }

    @Override
    public String toString() {
        return "OrderPrice{" +
                "pricePerKg=" + pricePerKg +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}