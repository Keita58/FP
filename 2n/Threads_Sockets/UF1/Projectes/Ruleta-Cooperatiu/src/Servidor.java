import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.json.JSONObject;

public class Servidor implements Runnable {

    static ExecutorService executor;
    static List<Handler> jugadors = new ArrayList<>();
    static List<Handler> jugadorsEsperant = new ArrayList<>();
    static List<String> nicksJugadors = new ArrayList<>();
    static boolean partidaComencada;
    static Semaphore filaDeU;
    static int[] ruleta = {2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1};
    static JSONObject resultat;
    static JSONObject apostesTotals;
    static Servidor S;
    static int i;
    static Object iniciPartida = new Object(); // Per poder fer-ho amb semàfors

    public static void main(String[] args) {
        int port = 25000;
        partidaComencada = false;
        i = 0;
        filaDeU = new Semaphore(1);
        resultat = new JSONObject();
        apostesTotals = new JSONObject();
        executor = Executors.newCachedThreadPool();
        S = new Servidor();
        executor.execute(S);

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while(true) {
                Socket clientSocket = serverSocket.accept();
                PontEntreClasses pEC = new PontEntreClasses(clientSocket, true);
                Handler aux = new Handler(pEC, S);
                jugadors.add(aux);
                executor.execute(aux);
            }
            
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void run() {
        Random r = new Random();
        
        while(true) {
            try {
                executor.execute(new Temp(10));
                synchronized(this) { wait(); } // Espera de 10s pel Lobby
                partidaComencada = true;
                
                int numRuleta = r.nextInt(0, 37);
                resultat.put("Numero", numRuleta);
                resultat.put("Color", ruleta[numRuleta]);
                resultat.put("Parell_Imparell", numRuleta%2);
                
                if(jugadors.size() > 0)
                    synchronized(this) { wait(); } // Esperem per rebre totes les dades dels Handlers

                for(Handler h : jugadors) {
                    h.DinersJugadors(apostesTotals);
                }

                Thread.sleep(10000);
                partidaComencada = false;
                PartidaComenca();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Llista paralel·la per anar apuntant qui ha arribat o no
    public synchronized void DinersJugadors(int aposta, String text, int guanys, String textGuanys) {
        apostesTotals.put(text, aposta);
        apostesTotals.put(textGuanys, guanys);
        i++;
        //Només quan estiguin totes les dades
        if(i == jugadors.size())
            this.notify();
    }

    public synchronized boolean AfegirNick(JSONObject nomJugador) {
        if(nicksJugadors.contains(nomJugador.getString("Nickname"))) {
            return false;
        }
        else {
            nicksJugadors.add(nomJugador.getString("Nickname"));
            return true;
        }
    }

    public void PartidaComenca() {

        for (Handler handler : jugadorsEsperant) {
            jugadors.add(handler);
        }
        jugadorsEsperant.clear();
    }
}

/*
    Tot el codi per triar la ruleta

    

    Colors: 0 -> Negre / 1 -> Vermell / 2 -> Verd
    
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

 
