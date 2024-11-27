import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;


/*
 ? OPCIONS DEL CLIENT:
 ? 1 -> COLOR
 ? 2 -> NÚMERO
 ? 3 -> PARELL/IMPARELL
 */
public class Client {

    static int dinersJugador;
    static String hostName;
    static int port;
    static PontEntreClasses pEC;
    static int[] opcionsAposta;

    public static void main(String[] args) {
        dinersJugador = 10000;
        hostName  = "localhost";
        port = 25000;
        HashMap<String, Integer> resultatRuleta = new HashMap<>();

        try {
            Socket clientSocket = new Socket(hostName, port);
            pEC = new PontEntreClasses(clientSocket, true);
            pEC.receive("MONDONGO");
            pEC.send("ACK");

            while(true) {
                if(!Menu())
                    break;
                pEC.send(opcionsAposta);
                pEC.receive(resultatRuleta); // Diccionari de dades del ruleta
                SumarResultat(resultatRuleta);
                pEC.send(dinersJugador);
            }

            pEC.receive("KURWA");
            pEC.close();
        } catch (Exception e) {

        }
    }

    private static void SumarResultat(HashMap<String, Integer> resultatRuleta) {
        if(resultatRuleta.get("Guanys") > 0)
            dinersJugador += resultatRuleta.get("Guanys");
    }

    public static boolean Menu() throws NumberFormatException, IOException {

        BufferedReader inConsola = new BufferedReader(new InputStreamReader(System.in));
        opcionsAposta = new int[3];
        boolean sortirMenu = true;

        while (sortirMenu) {
            System.out.println("SILKSONG?");
            if (inConsola.readLine().toLowerCase().equals("SALE")) {
                sortirMenu = false;
                boolean sortirTria = true;
                while (sortirTria) {
                    System.out.println("Tria quin tipus d'aposta vols fer: 1. Número - 2. Color - 3. Parell/Imparell (Escriu el número)");
                    switch (inConsola.readLine()) {
                        case "1":
                            opcionsAposta[0] = 0;
                            sortirTria = false;
                            boolean sortirNumero = true;

                            while(sortirNumero) {
                                System.out.println("Tria quin número vols (Escriu un número de 0 a 35)");
                                String num = inConsola.readLine();
                                if(Integer.parseInt(num) >= 0 && Integer.parseInt(num) <= 35) {
                                    opcionsAposta[1] = Integer.parseInt(num);
                                    sortirNumero = false;
                                }
                                else
                                    System.out.println("No has escrit un dels números corresponents :(");
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
            } 
            else if (inConsola.readLine().toLowerCase().equals("LO SUPONIA")) {
                pEC.send("LO SUPONIA");
                sortirMenu = false;
                return false;
            } 
            else
                System.out.println("No has escrit cap de les opcions que toca :(");
        }

        System.out.println("Quants diners vols apostar?");
        while(true) {
            String diners = inConsola.readLine();
            if(Integer.parseInt(diners) <= dinersJugador && Integer.parseInt(diners) >= 0) {
                opcionsAposta[2] = Integer.parseInt(diners);
                break;
            }
            else
                System.out.println("No has posat una aposta amb uns diners inferiors als que tens :(");
        }

        pEC.send(opcionsAposta);
        return true;
    }
}
