import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var numberOfCustomers = readInt(scanner, "number of customers");
        var numberOfWaiters = readInt(scanner, "number of waiters");
        var numberOfChefs = readInt(scanner, "number of chefs");
        var numberOfTables = readInt(scanner, "number of tables");

        var restaurant = new Restaurant(numberOfCustomers, numberOfWaiters, numberOfChefs, numberOfTables);
        restaurant.start();
    }

    private static int readInt(Scanner scanner, String field)
    {
        System.out.print("Enter " + field + " = ");
        return scanner.nextInt();
    }
}