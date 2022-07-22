package service;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class ConsoleService {

    public void showMainMenu() {
        log.info("You are in a virtual shop.You can add and delete users and goods from the shop and make purchases");
        //print users table
        //print product table
        log.info("Choose you action from the list:");
        log.info("1: Add an user");
        log.info("2: Add a product");
        log.info("3: Delete an user");
        log.info("4: Delete a product");
        log.info("5: Make a purchase");
        log.info("6: Show user's products");
        log.info("7: Show product owners");
        log.info("8: Quit");
        try (Scanner scanner = new Scanner(System.in)) {
            while (!scanner.hasNextInt()) {
                scanner.next();
            }

            int point = scanner.nextInt();
            if (point > 0 && point < 9) {
                switch (point) {
                    /* case 1 -> addUser();
                    case 2 -> /*addProduct();
                    case 3 -> /*deleteUserById();
                    case 4 -> /*deleteProductById();
                    case 5 -> /*makePurchase;
                    case 6 -> /*printUsersProductById;
                    case 7 -> /*printProductOwnersById;
                    case 8 -> /*exitFromApplication;*/


                    }
            }
        }

    }

}
