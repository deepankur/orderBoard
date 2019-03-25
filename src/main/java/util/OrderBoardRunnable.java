package util;

import model.Order;
import service.OrderBoardImpl;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class OrderBoardRunnable extends Thread {

    private final OrderBoardImpl orderBoardImpl;
    private final CyclicBarrier threadBarrier;
    private final Order order;
    private final boolean toRegister;

    public OrderBoardRunnable(final String threadName, final OrderBoardImpl orderBoardImpl, final CyclicBarrier threadBarrier,
                             final Order order, final boolean toRegister) {
        super(threadName);
        this.orderBoardImpl = orderBoardImpl;
        this.threadBarrier = threadBarrier;
        this.order = order;
        this.toRegister = toRegister;
    }

    @Override
    public void run() {
        try {

            if (toRegister) {
                orderBoardImpl.register(this.order);
            } else {
                orderBoardImpl.cancel(this.order);
            }
            threadBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
