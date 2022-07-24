package service;


import entity.Product;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import repository.PurchaseResultDAO;
import repository.UserProductDAO;

import java.util.Scanner;


@Slf4j
public class ConsoleService {
    private static final Scanner scanner = new Scanner(System.in);
    private final UserProductDAO userProductDAO = new UserProductDAO();
    private final PurchaseResultDAO purchaseResultDAO = new PurchaseResultDAO();


    public User newUser() {
        User user = new User();
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

        return user;
    }

    public Product newProduct() {
        Product product = new Product();
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
        product.setPrice(scanner.nextDouble());

        return product;
    }

    public static int getOptionFromUser() {

        while (!scanner.hasNextInt()) {
            log.info("The field can't be string, empty or less '1' or more '11'. Please try again");
            scanner.next();
        }
        int i = scanner.nextInt();

        if (i > 0 && i < 11) {
            return i;
        } else
            log.info("Enter the number in the range from 1 to 10, please!");
        scanner.next();
        getOptionFromUser();

        return 10;
    }

    public void showMainMenu() {
        boolean exit = true;
        System.out.println("You are in a virtual shop.You can add and delete users and goods from the shop and make purchases");
        System.out.println("Choose you action from the list:");
        System.out.println("1: Add an user");
        System.out.println("2: Add a product");
        System.out.println("6: Show users");
        System.out.println("7: Show products");
        System.out.println("5: Make a purchase");
        System.out.println("3: Delete an user");
        System.out.println("4: Delete a product");
        System.out.println("8: Show all product which was bought by user id");
        System.out.println("9: Show all user who bought the product bi its id");
        System.out.println("10: Exit");

        while (exit) {
            int point = getOptionFromUser();
            switch (point) {
                case 1 -> userProductDAO.addUserToList(newUser());
                case 2 -> userProductDAO.addProductToList(newProduct());
                case 3 -> userProductDAO.printUsers();
                case 4 -> userProductDAO.printProducts();
                case 5 -> purchaseResultDAO.makePurchase(userProductDAO.users, userProductDAO.products);
                case 6 -> userProductDAO.deleteUserById();
                case 7 -> userProductDAO.deleteProductById();
                case 8 -> purchaseResultDAO.printListOfProductsByUserId();
                case 9 -> purchaseResultDAO.printListOfUsersByProductId();
                case 10 -> exit = false;
            }
            showMainMenu();
        }

    }
}



