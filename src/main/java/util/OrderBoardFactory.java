package util;

import model.Order;
import model.PricePerKg;
import model.UserId;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

import static model.PricePerKg.of;
import static org.joda.money.CurrencyUnit.GBP;
import static tec.uom.se.quantity.Quantities.getQuantity;
import static tec.uom.se.unit.Units.KILOGRAM;

public class OrderBoardFactory {

    public static Order buy(Quantity<Mass> quantity, PricePerKg price, UserId userId) {
        return new Order(userId, quantity, price, Order.Type.Buy);
    }

    public static Order sell(Quantity<Mass> quantity, PricePerKg price, UserId userId) {
        return new Order(userId, quantity, price, Order.Type.Sell);
    }

    public static Quantity<Mass> kg(Number value) {
        return getQuantity(value, KILOGRAM);
    }

    public static PricePerKg £(double amount) {
        return of(GBP, amount);
    }
}
