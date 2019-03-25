package model;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

final public class Order {
    private final UserId userId;
    private final Quantity<Mass> quantity;
    private final PricePerKg pricePerKg;
    private final Type orderType;
    public Order(UserId userId, Quantity<Mass> quantity, PricePerKg pricePerKg, Type orderType) {
        this.userId = userId;
        this.quantity = quantity;
        this.pricePerKg = pricePerKg;
        this.orderType = orderType;
    }

    public Quantity<Mass> quantity() {
        return quantity;
    }

    public PricePerKg pricePerKg() {
        return pricePerKg;
    }

    public Type type() {
        return orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", quantity=" + quantity +
                ", pricePerKg=" + pricePerKg +
                ", orderType=" + orderType +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    public enum Type {Buy, Sell}
}
