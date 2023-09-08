import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FoodQueue {
    private final Queue<String> foods;
    private final ReentrantLock lock;
    private final Condition foodAvailable;

    public FoodQueue()
    {
        foods = new LinkedList<>();
        lock = new ReentrantLock();
        foodAvailable = lock.newCondition();
    }

    public void addFood(String food) {
        lock.lock();
        try {
            foods.add(food);
            foodAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    public String takeFood() {
        lock.lock();
        try {
            while (foods.isEmpty()) {
                foodAvailable.await();
            }
            return foods.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}