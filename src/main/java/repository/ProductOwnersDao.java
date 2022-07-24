package repository;

import entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@Slf4j
public class ProductOwnersDao {
    private static final Scanner scanner = new Scanner(System.in);
    public HashMap<Integer, ArrayList<User>> tradeMapByProductId = new HashMap<>();

    public void addProductOwnersMap(int id, User user) {

        ArrayList<User> localUserArrayList = new ArrayList<>();
        localUserArrayList.add(user);
        tradeMapByProductId.put(id, localUserArrayList);
        log.info("Trade map by product id was updated!");

    }

    public void printListOfUsersByProductId() {
        log.info("Enter Id : ");
        while (!scanner.hasNextInt()) {
            log.info("This field can't be empty, try again");
            scanner.next();
        }
        int id = scanner.nextInt();
        if (id > 0 && tradeMapByProductId.containsKey(id)) {
            log.info("All users who bought the product with " + id + " id");
            log.info(tradeMapByProductId.get(id-1).toString());
        } else log.info("Please enter right ID!");
        scanner.next();
    }
}
