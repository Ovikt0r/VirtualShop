package repository;


import entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class UserDao {
    private static final Scanner scanner = new Scanner(System.in);
    public ArrayList<User> users = new ArrayList<>();
    public UserPurchasesDao userPurchasesDao = new UserPurchasesDao();
    public ProductOwnersDao productOwnersDao = new ProductOwnersDao();


    public void addUserToList(User user) {
        users.add(user);
        log.info("The user was added successfully!");
    }

    public User getUserFromList(ArrayList<User> userArrayList) {

        log.info("Enter id of the user who wants to buy: ");
        while (!scanner.hasNextInt()) {
            log.info("ID not be empty, try again!");
            log.info("Enter id of the use who wants to buy: ");
            scanner.hasNextInt();
        }
        int id = scanner.nextInt();
        if (id >= 0 && id < userArrayList.size() + 2) {
            return userArrayList.get(id - 1);
        } else log.info("Enter a right id !");
        return null;
    }

    public void deleteUserById() {
        log.info("Enter Id : ");
        while (!scanner.hasNextInt()) {
            log.info("This field can't be empty, try again");
            scanner.next();
        }
        int id = scanner.nextInt();
        if (id > 0 && id < users.size() + 2) {
            users.remove(id - 1);
            userPurchasesDao.tradeMapByUserId.remove(id - 1);
            Set<Map.Entry<Integer, ArrayList<User>>> set = productOwnersDao.tradeMapByProductId.entrySet();
            for (Map.Entry<Integer, ArrayList<User>> it : set) {
                ArrayList<User> usersFromMap = it.getValue();
                usersFromMap.removeIf(u -> u.getId() == id - 1);
            }
        }
        log.info("The user was deleted successfully!");
    }

    public void printUsers() {
        log.info("THE LIST OF THE USERS");
        log.info(users.toString());
    }


}

