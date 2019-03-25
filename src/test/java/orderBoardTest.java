import model.Order;
import model.OrderDetails;
import model.PricePerKg;
import org.junit.Before;
import org.junit.Test;
import service.OrderBoard;
import service.OrderBoardImpl;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

import static model.Users.UserOne;
import static model.Users.UserTwo;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static util.OrderBoardFactory.*;


public class orderBoardTest {

    private OrderBoard orderBoard;

    @Before
    public void setUp() {
        orderBoard = new OrderBoardImpl();

    }

    @Test
    public void canRegisterOrders() {
        orderBoard.register(buy(kg(4.3), £(395), UserOne));
        assertThat(orderBoard.getOrders()).containsExactly(buyOrderDetails(kg(4.3), £(395) ));

    }

    @Test
    public void canCancelOrder() {
        orderBoard.register(buy(kg(4.3), £(395), UserOne));
        assertThat(orderBoard.getOrders()).containsExactly(buyOrderDetails(kg(4.3), £(395) ));

        orderBoard.cancel(buy(kg(4.3), £(395), UserOne));
        assertThat(orderBoard.getOrders()).isEmpty();

    }

    @Test
    public void combineOrdersWithSamePriceButDifferentUsers() {
        orderBoard.register(buy(kg(4.3), £(395), UserOne));
        orderBoard.register(buy(kg(3.3), £(395), UserTwo));
        assertThat(orderBoard.getOrders()).containsExactly(buyOrderDetails(kg(7.6), £(395) ));

    }

    @Test
    public void userWhoHasNotPlacedOrderCanNotCancelOrder() {
        orderBoard.register(buy(kg(4.3), £(395), UserOne));
        orderBoard.cancel(buy(kg(4.3), £(395), UserTwo));
        assertThat(orderBoard.getOrders()).containsExactly(buyOrderDetails(kg(4.3), £(395) ));

    }

    @Test
    public void shouldNotCombineOrderWithDifferentPrice() {
        orderBoard.register(buy(kg(4.3), £(395), UserOne));
        orderBoard.register(buy(kg(4.3), £(402), UserOne));
        assertThat(orderBoard.getOrders()).containsExactly(buyOrderDetails(kg(4.3), £(402) ),
                buyOrderDetails(kg(4.3), £(395) ) );

    }

    @Test
    public void bothBuyandSellOrdersAreDisPlayedCorrectly() {
        orderBoard.register(buy(kg(4.3), £(395), UserOne));
        orderBoard.register(sell(kg(4.3), £(395), UserOne));
        assertThat(orderBoard.getOrders()).containsExactly(buyOrderDetails(kg(4.3), £(395) ),
                sellOrderDetails(kg(4.3), £(395) ) );
    }

    private static OrderDetails buyOrderDetails(Quantity<Mass> quantity, PricePerKg pricePerKg) {
        return new OrderDetails(quantity, pricePerKg, Order.Type.Buy);
    }

    private static OrderDetails sellOrderDetails(Quantity<Mass> quantity, PricePerKg pricePerKg) {
        return new OrderDetails(quantity, pricePerKg, Order.Type.Sell);
    }
}
