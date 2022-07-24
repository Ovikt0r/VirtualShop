
import exceptions.NotEnoughMoneyException;
import controller.ConsoleController;


public class Application {


    public static void main(String[] args) throws NotEnoughMoneyException {
       new ConsoleController().showMainMenu();

    }
}
