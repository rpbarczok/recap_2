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
                System.out.println("order --> starts order process");
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
                Product product = shopService.orderRepo.getProductRepo().getProductById(id);
                System.out.println(product.name() + " (id " + product.productId() + "): " + product.price() +" €");
                System.out.println();

                System.out.println("What do you want to do next?");
            } else if (command.matches("order")) {
                System.out.println("Please enter the product id of the product you want to order:");
                int productId =  Integer.parseInt(scanner.nextLine());
                Product product = shopService.orderRepo.getProductRepo().getProductById(productId);
//                if (product == null) {
//                    System.out.println("Product with id " + productId + " not found");
//                    break;
//                }
                System.out.println("How many of the product " + product.name() + " do you want to order:");
                int quantity = Integer.parseInt(scanner.nextLine());
                System.out.println("That will cost: " + product.price().multiply(BigDecimal.valueOf(quantity)) + " €. Do you still want to order? (Y/N)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    Order order = shopService.orderRepo.addOrder(productId, quantity);
                    System.out.println("Order has been added successfully, your order id is " + order.getOrderId());
                    System.out.println("You need the order id to check on your order information.");
                }
            }
            else {
                System.out.println("Unknown command, please try again or type 'man'");
            }
        }
    }
}