import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONObject;

public class Client {

    static String hostName;
    static int port;
    static PontEntreClasses pEC;
    static JSONObject opcionsAposta;
    static int dinersJugador;

    public static void main(String[] args) {
        BufferedReader inConsola = new BufferedReader(new InputStreamReader(System.in));
        hostName = "localhost";
        //hostName = "10.1.83.134";
        //port = 55575;
        port = 25000;
        
        try {
            Socket clientSocket = new Socket(hostName, port);
            pEC = new PontEntreClasses(clientSocket, true);
            pEC.receiveByte(PontEntreClasses.S_BENVINGUT);
            pEC.sendByte(PontEntreClasses.ACK);

            //Nick del jugador
            while(true) {
                JSONObject json = new JSONObject();
                System.out.println("Escriu el teu nick: ");
                json.put("Nickname", inConsola.readLine());
                pEC.sendJSON(json);
                if(pEC.receiveNick()){
                    break;
                }
                System.out.println("El teu nick ja està en ús, prova un altre.");
            }

            while(true) {
                if(!Menu())
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean Menu() throws Exception {

        BufferedReader inConsola = new BufferedReader(new InputStreamReader(System.in));
        boolean sortirMenu = true;
        boolean primera = true;

        while (sortirMenu) {
            while(true) {
                if(pEC.receiveByte() == PontEntreClasses.S_ESTAS_DINS)
                    break;
                pEC.sendByte(PontEntreClasses.ACK);
            }
            pEC.sendByte(PontEntreClasses.ACK);

            if(primera){
                dinersJugador = pEC.receiveInt(); //dinersJugador del jugador
                pEC.sendByte(PontEntreClasses.ACK);
                primera = false;
            }
           
            pEC.receiveByte(PontEntreClasses.S_APOSTA);
            boolean sortirTria = true;
            while (sortirTria) {
                opcionsAposta = new JSONObject();
                System.out.println("Tria quin tipus d'aposta vols fer: 1. Número - 2. Color - 3. Parell/Imparell (Escriu el número)");

                switch (inConsola.readLine()) {
                    case "1":
                        opcionsAposta.put("Tipus", 0);
                        sortirTria = false;
                        boolean sortirNumero = true;

                        while(sortirNumero) {
                            System.out.println("Tria quin número vols (Escriu un número de 0 a 35)");
                            String num = inConsola.readLine();
                            if(num.equals("")){
                                System.out.println("No has escrit un dels números corresponents :(");
                            }
                            else {
                                if(Integer.parseInt(num) >= 0 && Integer.parseInt(num) <= 35) {
                                    opcionsAposta.put("Aposta", Integer.parseInt(num));
                                    sortirNumero = false;
                                }
                                else
                                    System.out.println("No has escrit un dels números corresponents :(");
                            }
                        }
                        break;

                    case "2":
                        opcionsAposta.put("Tipus", 1);
                        sortirTria = false;
                        boolean sortirColor = true;

                        while (sortirColor) {
                            System.out.println("Tria quin dels dos colors vols: 1. Vermell - 2. Negre (Escriu el número)");
                            switch (inConsola.readLine()) {
                                case "1":
                                    sortirColor = false;
                                    opcionsAposta.put("Aposta", 0);
                                    break;
                                case "2":
                                    sortirColor = false;
                                    opcionsAposta.put("Aposta", 1);
                                    break;
                                default:
                                    System.out.println("No has escrit cap de les opcions que toca :(");
                                    break;
                            }
                        }
                        break;

                    case "3":
                        opcionsAposta.put("Tipus", 2);
                        sortirTria = false;
                        boolean sortirTipusNum = true;

                        while (sortirTipusNum) {
                            System.out.println("Tria si vols: 1. Parell - 2. Senar");
                            switch (inConsola.readLine()) {
                                case "1":
                                    sortirTipusNum = false;
                                    opcionsAposta.put("Aposta", 0);
                                    break;
                                case "2":
                                    sortirTipusNum = false;
                                    opcionsAposta.put("Aposta", 1);
                                    break;
                                default:
                                    System.out.println("No has escrit cap de les opcions que toca :(");
                                    break;
                            }
                        }
                        break;

                    default:
                        System.out.println("No has escrit cap de les opcions que toca :(");
                        break;
                }
            }

            while(true) {
                System.out.println("Quants diners vols apostar? Tens " + dinersJugador);
                String diners = inConsola.readLine();
                if(diners.equals("")) {
                    System.out.println("No has posat uns dinersJugadors vàlids per jugar :(");
                }
                else {
                    if(Integer.parseInt(diners) <= dinersJugador && Integer.parseInt(diners) >= 0) {
                        opcionsAposta.put("Diners", Integer.parseInt(diners));
                        break;
                    }
                    else
                        System.out.println("No has posat uns dinersJugadors vàlids per jugar :(");
                }
            }
            pEC.sendJSON(opcionsAposta);
            pEC.receiveByte(PontEntreClasses.ACK);

            JSONObject resultatRuleta = pEC.receiveJSON(); // JSON de dades de la ruleta
            System.out.println("El resultat de la ruleta ha sigut el següent: " + resultatRuleta.toString(3));
            pEC.sendByte(PontEntreClasses.ACK);

            JSONObject dadesRuleta = pEC.receiveJSON(); // JSON de dades de la ruleta (resultats)
            System.out.println("Els jugadors tenen actualment els següents diners: " + dadesRuleta.toString(3));
            pEC.sendByte(PontEntreClasses.ACK);

            dinersJugador = pEC.receiveInt(); //Diners actuals del jugador
            pEC.sendByte(PontEntreClasses.ACK);

            pEC.receiveByte(PontEntreClasses.S_CONTINUAR); 
            if(dinersJugador == 0) {
                pEC.sendByte(PontEntreClasses.C_PLEGAR);
                return false;
            }           

            System.out.println("Vols tornar a jugar? Si -> 1 | No -> 2");
            boolean sortir = true;
            while(sortir) {
                switch(inConsola.readLine()) {
                    case "1":
                        pEC.sendByte(PontEntreClasses.C_SEGUIR);
                        sortir = false;
                        break;
                    case "2":
                        pEC.sendByte(PontEntreClasses.C_PLEGAR);
                        sortir = false;
                        sortirMenu = false;
                        break;
                    default:
                        System.out.println("No has escrit cap de les opcions que toca :(");
                        break;
                }
            }
        }
        return false;
    }
}
