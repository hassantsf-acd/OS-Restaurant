## Restaurant Simulation Project

- Course: Operating System
- Semester: Spring 2023
- Instructor: Dr. Hamed Khan Mirza

This program implements an algorithm that allows customers to request a table and place food orders. 
The restaurant only assigns a table to a customer when one is available. 
If no tables are available, the customer must wait. Additionally, there is a limited number of waitstaff who take orders and serve food, as well as a limited number of chefs who prepare the food.

### Components
- The Restaurant class will contain the main program logic and create necessary objects such as tables, waitstaff, and chefs.
-The Table class represents a table and has a Boolean variable indicating whether it is currently occupied.
- The Waiter class represents a waiter/waitress who takes customer orders and serves food.
- The Chef class represents a chef who prepares food orders.

- Customers arrive at the restaurant at random times. Customers who occupy a table only request it once. Each customer takes 3 seconds to place an order. For each order, it takes 4 seconds for the chef to prepare the food. The waitstaff spends 2 seconds on each order. Eating a meal takes 4 seconds.

The program will update the status of tables, waitstaff, chefs, and the customer queue every 5 seconds.
### Constraints
- Use Java for implementation, but do not use thread-safe data structures from java.util.concurrent. You must handle data protection yourself.
- Key aspects of the project include managing resources efficiently, avoiding deadlock scenarios, and handling race conditions to ensure a smooth and realistic restaurant simulation.