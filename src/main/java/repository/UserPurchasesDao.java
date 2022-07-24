package repository;

import entity.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@Slf4j
public class UserPurchasesDao {
    private static final Scanner scanner = new Scanner(System.in);
    public HashMap<Integer, ArrayList<Product>> tradeMapByUserId = new HashMap<>();

    public void createUserProductsMap(int id, Product product) {

        ArrayList<Product> localProductArrayList = new ArrayList<>();
        localProductArrayList.add(product);
        tradeMapByUserId.put(id, localProductArrayList);
        log.info("Trade map by user id was updated!");

    }

    public void printListOfProductsByUserId() {

        log.info("Enter product id : ");
        while (!scanner.hasNextInt()) {
            log.info("This field can't be empty, try again");
            scanner.next();
        }
        int id = scanner.nextInt();
        if (id > 0 &&  tradeMapByUserId.containsKey(id-1)) {
            log.info("All purchases that was done by user with " + id + " id");
            System.out.println(tradeMapByUserId.get(id).toString());
        }
        else log.info("Please enter right ID!");
        scanner.next();
    }
}
