package repository;

import entity.Product;
import entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@Slf4j
public class PurchaseResultDAO {
    private static final Scanner scanner = new Scanner(System.in);
    HashMap<Integer, ArrayList<Product>> tradeMapByUserId = new HashMap<>();
    HashMap<Integer, ArrayList<User>> tradeMapByProductId = new HashMap<>();



    public void makePurchase(ArrayList<User> userArrayList, ArrayList<Product> productArrayList) {
        User localUser = new UserProductDAO().getUserFromList(userArrayList);
        Product localProduct = new UserProductDAO().getProductFromList(productArrayList);
        if (localUser.getMoney() >= localProduct.getPrice()) {
            localUser.setMoney(localUser.getMoney() - localProduct.getPrice());
            userArrayList.set(localUser.getId(), localUser);
        }
        createUserProductsMap(localUser.getId(), localProduct);
        createProductOwnersMap(localProduct.getId(), localUser);

    }

    public void createUserProductsMap(int id, Product product) {

        ArrayList<Product> localProductArrayList = new ArrayList<>();
        localProductArrayList.add(product);
        tradeMapByUserId.put(id, localProductArrayList);
        log.info("Trade map by User Id was updated!");

    }

    public void createProductOwnersMap(int id, User user) {

        ArrayList<User> localUserArrayList = new ArrayList<>();
        localUserArrayList.add(user);
        tradeMapByProductId.put(id, localUserArrayList);

    }

    public void printListOfProductsByUserId() {

        log.info("Enter product id : ");
        while (!scanner.hasNextInt()) {
            log.info("This field can't be empty, try again");
            scanner.next();
        }
        int id = scanner.nextInt();
        if (id > 0 &&  tradeMapByUserId.containsKey(id)) {
            log.info("All purchases that was done by user with " + id + " id");
            System.out.println(tradeMapByUserId.get(id).toString());
        }
        else log.info("Please enter right ID!");
        scanner.next();
    }

    public void printListOfUsersByProductId() {
        log.info("Enter Id : ");
        while (!scanner.hasNextInt()) {
            log.info("This field can't be empty, try again");
            scanner.next();
        }
        int id = scanner.nextInt();
        if (id > 0 && tradeMapByProductId.containsKey(id)) {
            log.info(tradeMapByProductId.get(id).toString());
        } else log.info("Please enter right ID!");
        scanner.next();
    }
}
