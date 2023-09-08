class Waiter implements Runnable {
    private final OrderQueue orderQueue;
    private final FoodQueue foodQueue;

    public Waiter(OrderQueue orderQueue, FoodQueue foodQueue) {
        this.orderQueue = orderQueue;
        this.foodQueue = foodQueue;
    }

    private void takeOrder() {
        String order = orderQueue.takeOrder();
        System.out.println("Waiter took an order: " + order);
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void deliverFood() {
        String food = "Food for order: " + orderQueue.getLastOrder();
        foodQueue.addFood(food);
        System.out.println("Waiter delivered food: " + food);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            takeOrder();
            deliverFood();
        }
    }
}