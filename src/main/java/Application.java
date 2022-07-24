
import exceptions.NotEnoughMoneyException;
import controller.ConsoleService;


public class Application {


    public static void main(String[] args) throws NotEnoughMoneyException {
       new ConsoleService().showMainMenu();

    }
}
