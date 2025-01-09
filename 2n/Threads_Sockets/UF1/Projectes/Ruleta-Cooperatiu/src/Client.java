import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import org.json.JSONObject;

public class Client {

    static String hostName;
    static int port;
    static PontEntreClasses pEC;
    static int[] opcionsAposta;

    public static void main(String[] args) {
        BufferedReader inConsola = new BufferedReader(new InputStreamReader(System.in));
        hostName = "localhost";
        port = 25000;

        try {
            Socket clientSocket = new Socket(hostName, port);
            pEC = new PontEntreClasses(clientSocket, true);
            pEC.receiveByte(PontEntreClasses.S_BENVINGUT);
            pEC.sendByte(PontEntreClasses.ACK);

            //Nick del jugador
            while(true) {
                pEC.send(inConsola.readLine());
                if(pEC.receiveNick())
                    break;
            }

            while(true) {
                if(!Menu())
                    break;
            }

            pEC.receiveByte(PontEntreClasses.ACK);
            pEC.close();
        } catch (Exception e) {

        }
    }

    public static boolean Menu() throws NumberFormatException, IOException, Excepcio, ClassNotFoundException {

        BufferedReader inConsola = new BufferedReader(new InputStreamReader(System.in));
        boolean sortirMenu = true;

        while (sortirMenu) {
            byte b = pEC.receiveByte();
            pEC.sendByte(PontEntreClasses.ACK);

            if(b == PontEntreClasses.S_ESTAS_DINS) {
                pEC.receiveByte(PontEntreClasses.S_COMENCA_GAME);
                boolean sortirTria = true;
                while (sortirTria) {
                    opcionsAposta = new int[3];
                    System.out.println("Tria quin tipus d'aposta vols fer: 1. Número - 2. Color - 3. Parell/Imparell (Escriu el número)");

                    switch (inConsola.readLine()) {
                        case "1":
                            opcionsAposta[0] = 0;
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
                                        opcionsAposta[1] = Integer.parseInt(num);
                                        sortirNumero = false;
                                    }
                                    else
                                        System.out.println("No has escrit un dels números corresponents :(");
                                }
                            }
                            break;

                        case "2":
                            opcionsAposta[0] = 1;
                            sortirTria = false;
                            boolean sortirColor = true;
    
                            while (sortirColor) {
                                System.out.println("Tria quin dels dos colors vols: 1. Vermell - 2. Negre (Escriu el número)");
                                switch (inConsola.readLine()) {
                                    case "1":
                                        sortirColor = false;
                                        opcionsAposta[1] = 0;
                                        break;
                                    case "2":
                                        sortirColor = false;
                                        opcionsAposta[1] = 1;
                                        break;
                                    default:
                                        System.out.println("No has escrit cap de les opcions que toca :(");
                                        break;
                                }
                            }
                            break;

                        case "3":
                            opcionsAposta[0] = 2;
                            sortirTria = false;
                            boolean sortirTipusNum = true;
    
                            while (sortirTipusNum) {
                                System.out.println("Tria si vols: 1. Parell - 2. Senar");
                                switch (inConsola.readLine()) {
                                    case "1":
                                        sortirTipusNum = false;
                                        opcionsAposta[1] = 0;
                                        break;
                                    case "2":
                                        sortirTipusNum = false;
                                        opcionsAposta[1] = 1;
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
                        System.out.println("No has posat uns diners vàlids per jugar :(");
                    }
                    else {
                        if(Integer.parseInt(diners) <= dinersJugador && Integer.parseInt(diners) >= 0) {
                            opcionsAposta[2] = Integer.parseInt(diners);
                            dinersJugador -= Integer.parseInt(diners);
                            break;
                        }
                        else
                            System.out.println("No has posat uns diners vàlids per jugar :(");
                    }
                }
                pEC.sendArray(opcionsAposta);
                pEC.receiveByte(PontEntreClasses.ACK);
    
                JSONObject resultatRuleta = pEC.receiveJSON(); // JSON de dades de la ruleta
                System.out.println(resultatRuleta);
                pEC.sendByte(PontEntreClasses.ACK);

                JSONObject dadesRuleta = pEC.receiveJSON(); // JSON de dades de la ruleta (resultats)
                System.out.println(dadesRuleta);
                pEC.sendByte(PontEntreClasses.ACK);
    
                pEC.receiveByte(PontEntreClasses.S_CONTINUAR); 
                pEC.sendByte(PontEntreClasses.ACK);
                if(dinersJugador == 0) {
                    pEC.sendByte(PontEntreClasses.C_PLEGAR);
                    return false;
                }           

                System.out.println("Vols tornar a jugar? Si -> 1 | No -> 2");
                boolean sortir = false;
                while(sortir) {
                    switch(inConsola.readLine()) {
                        case "1":
                            pEC.sendByte(PontEntreClasses.C_SEGUIR);
                            break;
                        case "2":
                        pEC.sendByte(PontEntreClasses.C_PLEGAR);
                            sortirMenu = false;
                            break;
                        default:
                            System.out.println("No has escrit cap de les opcions que toca :(");
                            break;
                    }
                }
            }
        }
        return false;
    }
}
