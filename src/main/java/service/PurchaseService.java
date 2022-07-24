package service;

import entity.Product;
import entity.User;
import exceptions.NotEnoughMoneyException;
import lombok.extern.slf4j.Slf4j;
import repository.ProductDao;
import repository.ProductOwnersDao;
import repository.UserDao;
import controller.ConsoleController;
import repository.UserPurchasesDao;

import java.util.ArrayList;


@Slf4j
public class PurchaseService {
    public UserPurchasesDao userPurchasesDao = new UserPurchasesDao();
    public ProductOwnersDao productOwnersDao = new ProductOwnersDao();

    public void makePurchase(ArrayList<User> userArrayList, ArrayList<Product> productArrayList) throws NotEnoughMoneyException {
        User localUser = new UserDao().getUserFromList(userArrayList);
        Product localProduct = new ProductDao().getProductFromList(productArrayList);
        if (localUser.getMoney() >= localProduct.getPrice()) {
            localUser.setMoney(localUser.getMoney() - localProduct.getPrice());
            userArrayList.set(localUser.getId()-1, localUser);
            userPurchasesDao.addUserProductsMap(localUser.getId()-1, localProduct);
            productOwnersDao.addProductOwnersMap(localProduct.getId()-1, localUser);
            log.info("The purchase was successful !");
        }
        else throw new NotEnoughMoneyException("This user doesn't have enought money to by it. Choose another one");
        new ConsoleController().showMainMenu();
    }




}
