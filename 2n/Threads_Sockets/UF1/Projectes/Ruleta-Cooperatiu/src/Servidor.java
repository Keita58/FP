import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

public class Servidor {

    public static void main(String[] args) {
        int port = 25000;
        Random r = new Random();

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PontEntreClasses pEC = new PontEntreClasses(clientSocket, true);
            pEC.send("MONDONGO");
            pEC.receive("ACK");
            while(true) {
                HashMap<String, Integer> resultat = new HashMap<>();
                pEC.send("COMIENZA EL GAME");
                pEC.receive("ACK");
                int[] array = pEC.receiveArray();
                pEC.send("ACK");
                //Colors: 0 -> Negre / 1 -> Vermell / 2 -> Verd
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
                pEC.sendMap(resultat);
                pEC.receive("ACK");
                int dinersJugador = pEC.receiveInt();
                System.out.println("El jugador té " + dinersJugador);
                pEC.send("ACK");
                pEC.send("SILKSONG?");
                String reJugar = pEC.receive();
                if(reJugar.equals("LO SUPONIA"))
                    break;
            }
            pEC.send("PAKETE");
            pEC.close();
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }
}
