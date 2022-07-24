package repository;

import entity.Product;
import lombok.extern.slf4j.Slf4j;
import service.PurchaseService;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

@Slf4j
public class ProductDao {
    private static final Scanner scanner = new Scanner(System.in);
    public ArrayList<Product> products = new java.util.ArrayList<>();
    public UserPurchasesDao userPurchasesDao = new UserPurchasesDao();
    public ProductOwnersDao productOwnersDao = new ProductOwnersDao();

    public void addProductToList(Product product) {
        products.add(product);
        log.info("The product was added successfully!");
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

    public void deleteProductById() {
        log.info("Enter Id : ");
        while (!scanner.hasNextInt()) {
            log.info("This field can't be empty, try again");
            scanner.next();
        }
        int id = scanner.nextInt();
        if (id > 0 && id < products.size()) {
            products.remove(id-1);
            productOwnersDao.tradeMapByProductId.remove(id-1);
            Set<Map.Entry<Integer, ArrayList<Product>>> set = userPurchasesDao.tradeMapByUserId.entrySet();
            for (Map.Entry<Integer, ArrayList<Product>> it : set) {
                ArrayList<Product> productsFromMap = it.getValue();
                productsFromMap.removeIf(p -> p.getId() == id-1);
            }
        }
        log.info("The product was deleted successfully!");
    }

    public void printProducts() {
        log.info("THE LIST OF THE PRODUCTS ");
        log.info(products.toString());
    }
}
