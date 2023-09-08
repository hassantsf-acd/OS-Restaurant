public class Chef implements Runnable {
    private final OrderQueue orderQueue;
    private final FoodQueue foodQueue;

    public Chef(OrderQueue orderQueue, FoodQueue foodQueue) {
        this.orderQueue = orderQueue;
        this.foodQueue = foodQueue;
    }

    private void cookOrder() {
        String order = orderQueue.takeOrder();
        System.out.println("Chef is cooking the order: " + order);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Chef finished cooking the order: " + order);
    }

    private void notifyWaiter() {
        orderQueue.setLastOrderProcessed();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            cookOrder();
            notifyWaiter();
        }
    }
}