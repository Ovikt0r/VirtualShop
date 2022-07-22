package service;

import entity.Product;
import entity.User;
import exceptions.NotEnoughMoneyException;
import exceptions.UserNameException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@Slf4j
public class ShopService {

    User user = new User();
    Product product = new Product();
    User buyer = new User();
    Product soldProduct = new Product();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    HashMap<User, Product> tradeMap = new HashMap<>();

    public User newUser() {
        try (Scanner scanner = new Scanner(System.in)) {

            log.info("Enter user's name:");
            while (!scanner.hasNext()) {
                log.info("Name not be empty,try again!");
                log.info("Enter user's name:");
                scanner.next();
            }
            user.setFirstname(scanner.next());

            log.info("Enter user's lastname:");
            while (!scanner.hasNext()) {
                log.info("Lastname not be empty,try again!");
                log.info("Enter user's lastname:");
                scanner.next();
            }
            user.setLastname(scanner.next());

            log.info("Enter user's money amount:");
            while (!scanner.hasNextDouble()) {
                log.info("Lastname not be empty,try again!");
                log.info("Enter user's lastname:");
                scanner.nextDouble();
            }
            user.setMoney(scanner.nextDouble());
        }
        return user;
    }

    public Product newProduct() {
        try (Scanner scanner = new Scanner(System.in)) {

            log.info("Enter product's name:");
            while (!scanner.hasNext()) {
                log.info("Product's name not be empty,try again!");
                log.info("Enter product's name:");
                scanner.next();
            }
            product.setName(scanner.next());

            log.info("Enter price:");
            while (!scanner.hasNextDouble()) {
                log.info("Price not be empty,try again!");
                log.info("Enter price:");
                scanner.next();
            }
            user.setLastname(scanner.next());

        }
        return product;
    }

    public ArrayList<User> addUsersList() {
        users.add(newUser());
        return users;
    }

    public ArrayList<Product> addProductList() {
        products.add(newProduct());
        return products;
    }

    public ArrayList<User> deleteUserById(int id) {
        users.remove(id);
        return users;
    }


    public ArrayList<Product> deleteProductById(int id) {
        products.remove(id);
        return products;
    }

    public void printUsers() {
        for (User u : users) {
            log.info(u.toString());
        }
    }

    public void printProducts() {
        for (Product p : products) {
            log.info(p.toString());
        }
    }

    public void choseUserAndProduct() {

        log.info("Enter id of use who wants to buy: ");
        try (Scanner scanner = new Scanner(System.in)) {
            while (!scanner.hasNextInt()) {
                log.info("ID not be empty! Try again.");
                log.info("Enter id of use who wants to buy: ");
                scanner.hasNextInt();
            }
            buyer = users.get(scanner.nextInt());

            log.info("Enter id of product what should be sold: ");
            while (!scanner.hasNextInt()) {
                log.info("ID not be empty! Try again.");
                log.info("Enter id of use who wants to buy: ");
                scanner.hasNextInt();
            }
            soldProduct = products.get(scanner.nextInt());
        }
    }

    public HashMap<User, Product> makePurchase() throws NotEnoughMoneyException {
        try {
            if (buyer.getMoney() < soldProduct.getPrice()) {
                throw new NotEnoughMoneyException("The user has not enought money to buy this product");
            }

        } finally {
            log.info("Choose another buyer or product");
            choseUserAndProduct();
        }
            buyer.setMoney(buyer.getMoney()-soldProduct.getPrice());
            tradeMap.put(buyer, soldProduct);
            log.info("The purchase was successful!");
            users.set(buyer.getId(), buyer);
            products.set(soldProduct.getId(), soldProduct);
            return tradeMap;
    }

    public void printListOfProductsByUserId() {

    }
    public void printListOfUsersByProductId() {

    }

}
