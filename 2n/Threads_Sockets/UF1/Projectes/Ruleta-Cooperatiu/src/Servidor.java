import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

public class Servidor {

    static ExecutorService executor;
    static List<Handler> jugadors = new ArrayList<>();

    public static void main(String[] args) {
        int port = 25000;
        boolean entra = true;
        executor = Executors.newCachedThreadPool();
        GameManager GM = new GameManager();
        executor.execute(GM);

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while(true) {
                if(entra) {
                    executor.execute(new Temp(10));
                    entra = false;
                }

                Socket clientSocket = serverSocket.accept();
                PontEntreClasses pEC = new PontEntreClasses(clientSocket, true);
                Handler aux = new Handler(pEC, GM);
                jugadors.add(aux);
                executor.execute(aux);
                
                for(Handler jugador : jugadors) {
                    jugador.Comenca();
                }
                
                if(true)
                    break;
            }
            
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }
}

/*
    Tot el codi per triar la ruleta

    Random r = new Random();

    Colors: 0 -> Negre / 1 -> Vermell / 2 -> Verd
    int[] ruleta = {2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1};
    int numRuleta = r.nextInt(0, 37);
    resultat.put("Numero", numRuleta);
    resultat.put("Color", ruleta[numRuleta]);
    resultat.put("Parell_Imparell", numRuleta%2);
    if((array[0] == 0 && array[1] != numRuleta) || (array[0] == 1 && array[1] != ruleta[numRuleta]) || (array[0] == 2 && array[1] != numRuleta%2))
        resultat.put("Guanys", 0);
    else {
        if(array[0] == 0 && array[1] == numRuleta) //Número
            resultat.put("Guanys", array[2]*36);
        else if(array[0] == 1 && array[1] == ruleta[numRuleta]) //Color
            resultat.put("Guanys", array[2]*2);
        else if(array[0] == 2 && array[1] == numRuleta%2) //Parell_Imparell
            resultat.put("Guanys", array[2]*2);
    }
 */
