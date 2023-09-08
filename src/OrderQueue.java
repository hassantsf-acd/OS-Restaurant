import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OrderQueue {
    private final Queue<String> orders;
    private final ReentrantLock lock;
    private final Condition orderAvailable;
    private boolean lastOrderProcessed;

    public OrderQueue() {
        orders = new LinkedList<>();
        lock = new ReentrantLock();
        orderAvailable = lock.newCondition();
        lastOrderProcessed = true;
    }

    public void addOrder(String order) {
        lock.lock();
        try {
            orders.add(order);
            orderAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    public String takeOrder() {
        lock.lock();
        try {
            while (orders.isEmpty() || !lastOrderProcessed) {
                orderAvailable.await();
            }
            lastOrderProcessed = false;
            return orders.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }

    public String getLastOrder() {
        lock.lock();
        try {
            return orders.peek();
        } finally {
            lock.unlock();
        }
    }

    public void setLastOrderProcessed() {
        lock.lock();
        try {
            lastOrderProcessed = true;
        } finally {
            lock.unlock();
        }
    }
}