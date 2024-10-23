import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController {

    static int michiTokens;
    static Map<String, Integer> michis = new HashMap<>();
    static ExecutorService executor;
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        executor = Executors.newCachedThreadPool();
        executor.execute(new InputController(scanner));
        printGUI();
    }

    private static void printGUI() {
        System.out.print("\033[H\033[2J");  
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
        
        }
        else
            System.out.println("No tens suficients MichiTokens!");
    }

    public static void accio4() {
        if(michiTokens >= 100) {
        
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


