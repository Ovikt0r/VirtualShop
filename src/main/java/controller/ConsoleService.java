package controller;


import entity.Product;
import entity.User;
import exceptions.NotEnoughMoneyException;
import lombok.extern.slf4j.Slf4j;
import repository.ProductDao;
import repository.ProductOwnersDao;
import repository.UserDao;
import repository.UserPurchasesDao;
import service.PurchaseService;

import java.util.Scanner;


@Slf4j
public class ConsoleService {
    private static final Scanner scanner = new Scanner(System.in);
    private final UserDao userDao = new UserDao();
    private final ProductDao productDao = new ProductDao();
    private final PurchaseService purchaseResultDAO = new PurchaseService();
    private final UserPurchasesDao userPurchasesDao = new UserPurchasesDao();
    private final ProductOwnersDao productOwnersDao = new ProductOwnersDao();


    public User newUser() {
        User user = new User();
        String firstNameResponse;
        String lastNameResponse;
        log.info("Enter user's first name:");
        while (!scanner.hasNextLine()) {
            log.info("User's first name can't be empty,try again!");
            log.info("Enter user's first name:");
            scanner.next();
        }
            firstNameResponse = scanner.next();
            user.setFirstName(firstNameResponse);

        log.info("Enter user's lastname:");
        while (!scanner.hasNextLine()) {
            log.info("User's last name can't be empty,try again!");
            log.info("Enter user's last name:");
            scanner.next();
        }
        lastNameResponse = scanner.next();
        user.setFirstName(lastNameResponse);

        log.info("Enter user's money amount:");
        while (!scanner.hasNextDouble()) {
            log.info("Lastname can't be empty,try again!");
            log.info("Enter user's lastname:");
            scanner.nextDouble();
        }
        user.setMoney(scanner.nextDouble());

        return user;
    }

    public Product newProduct() {
        Product product = new Product();
        String productNameResponse;
        log.info("Enter product's name:");
        while (!scanner.hasNextLine()) {
            log.info("User's first name can't be empty,try again!");
            log.info("Enter user's name:");
            scanner.next();
        }
        productNameResponse = scanner.next();
        product.setName(productNameResponse);

        log.info("Enter price:");
        while (!scanner.hasNextDouble()) {
            log.info("Price can't be empty,try again!");
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

    public void showMainMenu() throws NotEnoughMoneyException {
        boolean exit = true;
        System.out.println("You are in a virtual shop.You can add and delete users and goods from the shop and make purchases");
        System.out.println("Choose you action from the list:");
        System.out.println("1: Add an user");
        System.out.println("2: Add a product");
        System.out.println("3: Show users");
        System.out.println("4: Show products");
        System.out.println("5: Make a purchase");
        System.out.println("6: Delete an user");
        System.out.println("7: Delete a product");
        System.out.println("8: Show all product which was bought by user id");
        System.out.println("9: Show all user who bought the product bi its id");
        System.out.println("10: Exit");

        while (exit) {
            int point = getOptionFromUser();
            switch (point) {
                case 1 -> userDao.addUserToList(newUser());
                case 2 -> productDao.addProductToList(newProduct());
                case 3 -> userDao.printUsers();
                case 4 -> productDao.printProducts();
                case 5 -> purchaseResultDAO.makePurchase(userDao.users, productDao.products);
                case 6 -> userDao.deleteUserById();
                case 7 -> productDao.deleteProductById();
                case 8 -> userPurchasesDao.printListOfProductsByUserId();
                case 9 -> productOwnersDao.printListOfUsersByProductId();
                case 10 -> exit = false;
            }
            showMainMenu();
        }

    }
}



