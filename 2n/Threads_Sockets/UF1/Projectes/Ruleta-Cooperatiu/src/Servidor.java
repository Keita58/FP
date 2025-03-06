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
    static List<Handler> jugadorsEsperantADirSi = new ArrayList<>();
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
    static Semaphore numJugadors;

    public static void main(String[] args) throws Excepcio {
        int port = 25000;
        partidaComencada = false;
        ruletaCanviada = false;
        i = 0;
        filaDeU = new Semaphore(1);
        numJugadors = new Semaphore(100);
        resultat = new JSONObject();
        apostesTotals = new JSONObject();
        executor = Executors.newCachedThreadPool();
        S = new Servidor();
        executor.execute(S);

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while(true) {
                Socket clientSocket = serverSocket.accept();
                Handler aux = new Handler(clientSocket, S);
                executor.execute(aux);
            }
        }
        catch(Exception e) {
            throw new Excepcio(e.toString());
        }
    }

    @Override
    public void run() {
        Random r = new Random();
        boolean esperar = true;
        while(true) {
            try {
                if(numJugadors.availablePermits() <= 98) {
                    executor.execute(new Temp(10));
                    Thread.sleep(10000);
                    
                    for(Handler h : jugadors) {
                        h.Desperta();
                    }
                    
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
                    apostesTotals = new JSONObject();
    
                    System.out.println("Ronda acabada!");
    
                    partidaComencada = false;
                    PartidaComenca();
                    Thread.sleep(2000);
                    i = 0;
                    esperar = true;
                }
                else {
                    if(esperar) {
                        esperar = false;
                        System.out.println("Esperant dos jugadors...");
                    }
                }
            } catch (InterruptedException e) {
                try {
                    throw new Excepcio("El servidor s'ha tancat inesperadament!");
                } catch (Excepcio e1) {}
            }
        }
    }

    //Llista paral·lela per anar apuntant qui ha arribat o no
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

    public synchronized void AlliberaNick(String nick) {
        nicksJugadors.remove(nick);
    }

    public synchronized void EliminarHandler(Handler h) {
        jugadors.remove(h);

        if(i == jugadors.size())
            synchronized(this) { notify(); }
    }

    public void AfegirHandler(Handler h) {
        if(jugadorsEsperantADirSi.contains(h))
            jugadorsEsperantADirSi.remove(h);

        jugadors.add(h);
    }

    public void AfegirHandlerEspera(Handler h) {
        if(jugadorsEsperantADirSi.contains(h))
            jugadorsEsperantADirSi.remove(h);

        jugadorsEsperant.add(h);
    }

    public void AfegirHandlerEsperaADirSi(Handler h) {
        jugadors.remove(h);
        jugadorsEsperantADirSi.add(h);
    }

    public void DespertaServer() {
        synchronized(this) { notify(); }
    }
}