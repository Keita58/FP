import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class GameController {

    static int michiTokens;
    static Map<String, Integer> michis = new HashMap<>();
    static ExecutorService executor;
    static Scanner scanner;
    static Semaphore semMichismart;
    static CountDownLatch cdlMichilover;

    public static void main(String[] args) {
        semMichismart = new Semaphore(2);
        cdlMichilover = new CountDownLatch(5);
        scanner = new Scanner(System.in);
        executor = Executors.newCachedThreadPool();
        executor.execute(new InputController(scanner));
        printGUI();
    }

    private static void printGUI() {
        System.out.print("\033[H\033[2J");  //? Neteja la pantalla per a que només es quedin els prints següents
        System.out.println("Quantitat actual de MichiTokens: " + michiTokens);
        System.out.println("Ara mateix tens aquesta quantitat de michis: ");
        for(Entry<String, Integer> michi : michis.entrySet()) {
            System.out.println(michi.getKey() + " - " + michi.getValue());
        }
        System.out.println();
        System.out.println("Pots realitzar les següents accions: ");
        System.out.println("0.- Tancar l'aplicació");
        System.out.println("1.- Crear Michibi (5 MichiTokens) -> 1 MichiToken/s");
        System.out.println("2.- Crear Michonk (20 MichiTokens) -> 5 MichiTokens/s");
        System.out.println("3.- Crear Michilover (10 MichiTokens) -> Cada 5 Michilovers et donen 250 MichiTokens");
        System.out.println("4.- Crear Michismart (100 MichiTokens) -> 10s d'espera per 1000 MichiTokens");
        System.out.println("Per guanyar un MichiToken pots clicar qualsevol altre tecla del teclat");
        System.out.println();
    }

    public static void accio1() {
        if(michiTokens >= 5) {
            michiTokens -= 5;
            executor.execute(new Michibi());
            if(michis.containsKey("Michibi"))
                michis.put("Michibi", (michis.get("Michibi") + 1));
            else
                michis.put("Michibi", 1);
        }
        else
            System.out.println("No tens suficients MichiTokens!");
    }

    public static void accio2() {
        if(michiTokens >= 20) {
            michiTokens -= 20;
            executor.execute(new Michonk());
            if(michis.containsKey("Michonk"))
                michis.put("Michonk", (michis.get("Michonk") + 1));
            else
                michis.put("Michonk", 1);
        }
        else
            System.out.println("No tens suficients MichiTokens!");
    }

    public static void accio3() {
        if(michiTokens >= 10) {
            michiTokens -= 10;
            try {
                if(cdlMichilover.getCount() > 0) {
                    if(michis.containsKey("Michilover"))
                        michis.put("Michilover", (michis.get("Michilover") + 1));
                    else
                        michis.put("Michilover", 1);
                    executor.execute(new Michilover(cdlMichilover, michis));
                    printGUI();
                }
            }
            catch (Exception e) {

            }
        }
        else
            System.out.println("No tens suficients MichiTokens!");
    }

    public static void accio4() {
        if(michiTokens >= 100) {
            michiTokens -= 100;
            try {
                if(semMichismart.tryAcquire()) {
                    executor.execute(new Michismart(semMichismart, michis));
                    if(michis.containsKey("Michismart"))
                        michis.put("Michismart", (michis.get("Michismart") + 1));
                    else
                        michis.put("Michismart", 1);
                    printGUI();
                }
                
            }
            catch (Exception e) {

            }
        }
        else
            System.out.println("No tens suficients MichiTokens!");
    }
    
    public static void quitGame() {
        System.out.print("\033[H\033[2J");
        System.out.println("Gràcies per jugar! ^^");
        scanner.close();
        executor.shutdownNow();
    }

    public static synchronized void incrementarMichitokens(int tokens) {
        michiTokens += tokens;
        printGUI();
    }
}


