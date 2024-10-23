import java.util.Scanner;

public class InputController implements Runnable {

    Scanner ohowo;

    public InputController(Scanner scanner) {
        this.ohowo = scanner;
    }

    @Override
    public void run() {
        try{
            while(true) {
                String valor = ohowo.nextLine();
                switch(valor) {
                    case "1":
                        GameController.accio1();
                        break;
                    case "2":
                        GameController.accio2();
                        break;
                    case "3":
                        GameController.accio3();
                        break;
                    case "4":
                        GameController.accio4();
                        break;
                    case "0":
                        GameController.quitGame();
                        break;
                    default:
                        GameController.incrementarMichitokens(1);
                        break;
                }
            }
        }
        catch (Exception e) {
            //? Per quan tanquem el scanner per a que no aparegui el missatge d'error
        }
    }
}
