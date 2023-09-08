import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {
    private final int numberOfCustomers;
    private final int numberOfWaiters;
    private final int numberOfChefs;

    private final Table[] tables;
    private final ReentrantLock tableLock = new ReentrantLock();
    private final Condition tableAvailable = tableLock.newCondition();

    public Restaurant(int numberOfCustomers, int numberOfWaiters, int numberOfChefs, int numberOfTables) {
        this.numberOfCustomers = numberOfCustomers;
        this.numberOfWaiters = numberOfWaiters;
        this.numberOfChefs = numberOfChefs;
        tables = new Table[numberOfTables];

        for (int i = 0; i < numberOfTables; i++) {
            tables[i] = new Table(i);
        }
    }

    public void start()
    {
        var orderQueue = new OrderQueue();
        var foodQueue = new FoodQueue();

        for (int i = 0; i < numberOfWaiters; i++) {
            var waiter = new Waiter(orderQueue, foodQueue);
            var waiterThread = new Thread(waiter);
            waiterThread.start();
        }

        for (int i = 0; i < numberOfChefs; i++) {
            var chef = new Chef(orderQueue, foodQueue);
            var chefThread = new Thread(chef);
            chefThread.start();
        }

        var random = new Random();
        for (int i = 0; i < numberOfCustomers; i++) {
            var customer = new Customer(i + 1, this, orderQueue, foodQueue);
            var customerThread = new Thread(customer);
            var randomTime = random.nextInt(5) + 2;
            try {
                Thread.sleep(randomTime * 1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            customerThread.start();
        }
    }

    public void reserveTable(int customerID) {
        try {
            tableLock.lock();

            Table table;
            while ((table = getFreeTable()) == null)
            {
                tableAvailable.await();
            }

            table.reserve(customerID);
            System.out.println("Customer " + customerID + " reserved table " + table.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            tableLock.unlock();
        }
    }

    private Table getFreeTable() {
        for (var table:
                tables) {
            if (!table.isOccupied())
            {
                return table;
            }
        }

        return null;
    }

    public void releaseTable(int customerID) {
        try {
            tableLock.lock();
            for (Table table :
                    tables) {
                if (table.getOccupiedBy() == customerID)
                {
                    table.release();
                    tableAvailable.signal();
                    System.out.println("Customer " + customerID + " released table " + table.getId());
                    break;
                }
            }
        } finally {
            tableLock.unlock();
        }
    }
}
