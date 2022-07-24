package repository;

import entity.Product;
import entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Getter
@Setter
public class UserProductDAO {
    private static final Scanner scanner = new Scanner(System.in);
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<Product> products = new ArrayList<>();
    public PurchaseResultDAO purchaseResultDAO = new PurchaseResultDAO();


    public void addUserToList(User user) {
        users.add(user);
        log.info("The user was added successfully!");
    }

    public void addProductToList(Product product) {
        products.add(product);
        log.info("The product was added successfully!");
    }

    public User getUserFromList(ArrayList<User> userArrayList) {

        log.info("Enter id of the user who wants to buy: ");
        while (!scanner.hasNextInt()) {
            log.info("ID not be empty, try again!");
            log.info("Enter id of the use who wants to buy: ");
            scanner.hasNextInt();
        }
        int id = scanner.nextInt();
        if(id >=0 && id < userArrayList.size()+2) {
            return userArrayList.get(id - 1);
        }
        else log.info("Enter a right id !");
        return null;
    }

    public Product getProductFromList(ArrayList<Product> productArrayList) {
        log.info("Enter id of product what should be sold: ");
        while (!scanner.hasNextInt()) {
            log.info("ID not be empty! Try again.");
            log.info("Enter id of use who wants to buy: ");
            scanner.hasNextInt();
        }
        int id = scanner.nextInt();
        if(id >=0 && id < productArrayList.size()+2) {
            return productArrayList.get(id - 1);
        }
        else log.info("Enter a right id !");
        return null;
    }

    public void printUsers() {
        log.info("THE LIST OF THE USERS");
        log.info(users.toString());
    }

    public void printProducts() {
        log.info("THE LIST OF THE PRODUCTS ");
        log.info(products.toString());
    }

    public void deleteUserById() {
        log.info("Enter Id : ");
        while (!scanner.hasNextInt()) {
            log.info("This field can't be empty, try again");
            scanner.next();
        }
        int id = scanner.nextInt();
        if (id > 0 && id < users.size()+2) {
            users.remove(id-1);
            purchaseResultDAO.tradeMapByUserId.remove(id-1);
            Set<Map.Entry<Integer, ArrayList<User>>> set = purchaseResultDAO.tradeMapByProductId.entrySet();
            for (Map.Entry<Integer, ArrayList<User>> it : set) {
                ArrayList<User> usersFromMap = it.getValue();
                usersFromMap.removeIf(u -> u.getId() == id-1);
            }
        }
        log.info("The user was deleted successfully!");
    }

    public void deleteProductById() {
        log.info("Enter Id : ");
        while (!scanner.hasNextInt()) {
            log.info("This field can't be empty, try again");
            scanner.next();
        }
        int id = scanner.nextInt();
        if (id > 0 && id < products.size()) {
            products.remove(id-1);
            purchaseResultDAO.tradeMapByProductId.remove(id-1);
            Set<Map.Entry<Integer, ArrayList<Product>>> set = purchaseResultDAO.tradeMapByUserId.entrySet();
            for (Map.Entry<Integer, ArrayList<Product>> it : set) {
                ArrayList<Product> productsFromMap = it.getValue();
                productsFromMap.removeIf(p -> p.getId() == id-1);
            }
        }
        log.info("The product was deleted successfully!");
    }

}

