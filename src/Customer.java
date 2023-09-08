public class Customer implements Runnable {
    private final int id;
    private final Restaurant restaurant;
    private final OrderQueue orderQueue;
    private final FoodQueue foodQueue;

    public Customer(int id, Restaurant restaurant, OrderQueue orderQueue, FoodQueue foodQueue) {
        this.id = id;
        this.restaurant = restaurant;
        this.orderQueue = orderQueue;
        this.foodQueue = foodQueue;
    }

    private void reserveTable() {
        restaurant.reserveTable(id);
    }

    private void releaseTable() {
        restaurant.releaseTable(id);
    }

    private void placeOrder() {
        String order = "Order by Customer " + id;
        orderQueue.addOrder(order);
        System.out.println("Customer " + id + " placed an order: " + order);
    }

    private void receiveFood() {
        String food = foodQueue.takeFood();
        System.out.println("Customer " + id + " received food: " + food);
    }

    private void eatFood()
    {
        try {
            Thread.sleep(4_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Customer " + id + " arrived!");
        reserveTable();
        placeOrder();
        receiveFood();
        eatFood();
        releaseTable();

        System.out.println("Customer " + id + " finished eating.");
    }
}