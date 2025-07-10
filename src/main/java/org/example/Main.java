package org.example;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ShopService shopService = new ShopService();

        System.out.println("Type 'man' to see how it works:");
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            command = scanner.nextLine();
            if (command.equals("man")) {
                System.out.println("exit --> exit");
                System.out.println("order new --> starts order process");
                System.out.println("order show all --> shows the current state of all orders");
                System.out.println("order show id --> shows the timeline of order with specific order id");
                System.out.println("order update id --> updates order with specific order id");
                System.out.println("product new --> creates new product");
                System.out.println("product show all --> shows list with all products");
                System.out.println("product show <id> --> shows product with specific id");


                System.out.println("What do you want to do next?");
            } else if (command.equals("product new")) {

                System.out.println("Please enter the product name:");
                String name = scanner.nextLine();

                System.out.println("Please enter the product price:");
                BigDecimal price = new BigDecimal(scanner.nextLine());

                shopService.addNewProduct(name, price);
                System.out.println("New product was added successfully");
                System.out.println();

                System.out.println("What do you want to do next?");
            } else if (command.equals("product show all")) {
                Map<Integer, Product> products = shopService.getProducts();
                for (Map.Entry<Integer, Product> entry : products.entrySet()) {
                    System.out.println(entry.getValue().name() + " (id " + entry.getValue().productId() + "): " + entry.getValue().price() +" €");
                }

                System.out.println("What do you want to do next?");
            } else if (command.matches("product show [0-9]*")) {
                String split = command.split(" ")[2];
                int id = Integer.parseInt(split);
                Product product = shopService.getOrderRepo().getProductRepo().getProductById(id);
                System.out.println(product.name() + " (id " + product.productId() + "): " + product.price() +" €");
                System.out.println();

                System.out.println("What do you want to do next?");
            } else if (command.equals("order new")) {
                System.out.println("Please enter the product id of the product you want to order:");
                int productId =  Integer.parseInt(scanner.nextLine());
                Product product = shopService.getOrderRepo().getProductRepo().getProductById(productId);
                System.out.println("How many of the product " + product.name() + " do you want to order:");
                int quantity = Integer.parseInt(scanner.nextLine());
                System.out.println("That will cost: " + product.price().multiply(BigDecimal.valueOf(quantity)) + " €. Do you still want to order? (Y/N)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    Order order = shopService.getOrderRepo().addOrder(productId, quantity);
                    System.out.println("Order has been added successfully, your order id is " + order.getOrderId());
                    System.out.println("You need the order id to check on your order information.");
                }
                System.out.println("What do you want to do next?");
            } else if (command.equals("order show all")) {
                Map<Integer, Order> orders = shopService.getOrderRepo().getOrders();
                for (Map.Entry<Integer, Order> entry : orders.entrySet()) {
                    int orderId = entry.getValue().getOrderId();
                    int processId = entry.getValue().getProcessId();
                    OrderHistory currentState = entry.getValue().getOrderTimeLine().get(processId);

                    System.out.println("Current state of order " + orderId + " ordered at " + entry.getValue().getCreatedAt());
                    if (currentState.getUpdatedAt() != null) {
                        System.out.println("Latest update: " + currentState.getUpdatedAt() + " (" + currentState.getComment() + ")");
                    }
                    System.out.println("    Product name: " + currentState.getProduct().name());
                    System.out.println("    Quantity    : " + currentState.getQuantity());
                    System.out.println("    Cost        : " + currentState.getProduct().price().multiply(BigDecimal.valueOf(currentState.getQuantity())) + " €" +
                            " (=" + currentState.getQuantity() + "x" + currentState.getProduct().price() + "€)");
                }
                System.out.println("What do you want to do next?");
            } else if (command.matches("order show [0-9]*")){
                String split = command.split(" ")[2];
                int id = Integer.parseInt(split);
                Order order = shopService.getOrderRepo().getOrders().get(id);
                Map<Integer, OrderHistory> orderTimeLine = order.getOrderTimeLine();
                System.out.println("Order id " + order.getOrderId() + " ordered at " + order.getCreatedAt() + ": ");
                for(Map.Entry<Integer, OrderHistory> entry : orderTimeLine.entrySet()){

                    if (entry.getValue().getUpdatedAt() != null) {
                        System.out.println("Update " + (entry.getValue().getProcessId()-1) + " (" + entry.getValue().getComment() + " - " + entry.getValue().getUpdatedAt() + ")");
                    } else {
                        System.out.println("Original Order: ");
                    }
                    System.out.println("    Product name: " + entry.getValue().getProduct().name());
                    System.out.println("    Quantity    : " + entry.getValue().getQuantity());
                    System.out.println("    Cost        : " + entry.getValue().getProduct().price().multiply(BigDecimal.valueOf(entry.getValue().getQuantity())) + " €" +
                            " (=" + entry.getValue().getQuantity() + "x" + entry.getValue().getProduct().price() + "€)");
                }
                System.out.println("What do you want to do next?");
            } else if (command.matches("order update [0-9]*")) {
                String split = command.split(" ")[2];
                int id = Integer.parseInt(split);
                Order order = shopService.getOrderRepo().getOrders().get(id);

                System.out.println("Enter state if you want to update the state or enter quantity if you want to change the quantity.");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("state")) {
                    System.out.println("Enter the new state of the order");
                    String newState = scanner.nextLine();
                    order.updateComment(newState);
                    System.out.println("Order has been updated successfully");
                } else if (answer.equalsIgnoreCase("quantity")) {
                    System.out.println("Enter the new quantity of the order");
                    int quantity =  Integer.parseInt(scanner.nextLine());
                    BigDecimal newPrice = order.getOrderTimeLine().get(order.getProcessId()).getProduct().price().multiply(BigDecimal.valueOf(quantity));
                    System.out.println("The new price will be " +  newPrice + "€. Still update? (Y/N)");
                    String newAnswer = scanner.nextLine();
                    if (newAnswer.equalsIgnoreCase("Y")) {
                        order.updateQuantity(quantity);
                        System.out.println("Order has been updated successfully");
                    }
                } else {
                    System.out.println("Unknown command");
                }
                System.out.println("What do you want to do next?");
            }
            else {
                System.out.println("Unknown command, please try again or type 'man'");
            }
        }
    }
}