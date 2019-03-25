import model.Order;
import service.OrderBoardImpl;
import util.OrderBoardRunnable;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeoutException;

import static model.Users.UserOne;
import static model.Users.UserTwo;
import static util.OrderBoardFactory.*;

public class OrderBoardApplication {

    public static void main ( String[] args) throws InterruptedException, BrokenBarrierException, TimeoutException {
        OrderBoardImpl orderBoardImpl = new OrderBoardImpl();

        final CyclicBarrier gate = new CyclicBarrier(2);
        Order orderToBeAddedToBuy = buy(kg(4.33), £(209), UserOne);
        Order orderToBeAddedToSell = sell(kg(4.1), £(302), UserTwo);
        OrderBoardRunnable thread_one = new OrderBoardRunnable("THREAD_ONE", orderBoardImpl, gate,
                orderToBeAddedToBuy, true);
        OrderBoardRunnable thread_two = new OrderBoardRunnable("THREAD_TWO", orderBoardImpl, gate,
                orderToBeAddedToSell, true);

        thread_one.start();
        thread_two.start();
        gate.await();

        orderBoardImpl.getOrders();
    }
}
