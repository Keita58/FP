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
    static boolean ruletaCanviada;

    public static void main(String[] args) {
        int port = 25000;
        partidaComencada = false;
        ruletaCanviada = false;
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
                if(partidaComencada)
                    jugadorsEsperant.add(aux);
                else
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
                Thread.sleep(10000);
                partidaComencada = true;
                
                int numRuleta = r.nextInt(0, 37);
                resultat.put("Numero", numRuleta);
                resultat.put("Color", ruleta[numRuleta]);
                resultat.put("Parell_Imparell", numRuleta%2);
                ruletaCanviada = true;

                for(Handler h : jugadors) {
                    h.Desperta();
                }
                
                if(jugadors.size() > 0)
                    synchronized(this) { wait(); } // Esperem per rebre totes les dades dels Handlers
                
                ruletaCanviada = false;

                for(Handler h : jugadors) {
                    h.DinersJugadors(apostesTotals);
                }

                System.out.println("Ronda acabada!");

                partidaComencada = false;
                Thread.sleep(10000);
                PartidaComenca();
                i = 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Llista parella per anar apuntant qui ha arribat o no
    public synchronized void DinersJugadors(int aposta, String text, int guanys, String textGuanys) {
        apostesTotals.put(text, aposta);
        apostesTotals.put(textGuanys, guanys);
        i++;
        //Només quan estiguin totes les dades
        if(i == jugadors.size())
            synchronized(this) { notify(); }
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
            handler.Desperta();
        }
        jugadorsEsperant.clear();
    }

    public void AlliberaNick(String nick) {
        nicksJugadors.remove(nick);
    }

    public void EliminarHandler(Handler h) {
        jugadors.remove(h);

        if(jugadors.size() <= 0)
            synchronized(this) { notify(); }
    }
}