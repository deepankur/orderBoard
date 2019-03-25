package model;

import tec.uom.se.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.List;

import static model.Order.Type;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static tec.uom.se.unit.Units.KILOGRAM;

final public class OrderDetails {

    static private final Quantity<Mass> Zero_Weight = Quantities.getQuantity(0.0, KILOGRAM);


    private final PricePerKg pricePerKg;
    private final Type orderType;
    private final Quantity<Mass> quantity;

    public OrderDetails(Quantity<Mass> quantity, PricePerKg pricePerKg, Type orderType) {
        this.quantity = quantity;
        this.orderType = orderType;
        this.pricePerKg = pricePerKg;
    }

    public OrderDetails(OrderPrice orderPrice, List<Quantity<Mass>> quantities) {
        this(total(quantities), orderPrice.pricePerKg(), orderPrice.orderType());
    }

    private static Quantity<Mass> total(List<Quantity<Mass>> quantities) {
        return quantities.stream().reduce(Zero_Weight, (acc, q) -> acc.add(q));
    }

    public Type orderType() {
        return this.orderType;
    }

    public PricePerKg pricePerKg() {
        return this.pricePerKg;
    }


    @Override
    public String toString() {
        return "OrderDetails{" +
                "pricePerKg=" + pricePerKg +
                ", orderType=" + orderType +
                ", quantity=" + quantity +
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

}
