import java.io.IOException;
import java.net.Socket;

import org.json.JSONObject;

public class Handler implements Runnable{

    private PontEntreClasses pEC;
    private Servidor S;
    //private boolean inici;
    private int dinersJugador;
    private JSONObject dinersJugadorsJSON;
    private boolean foraCuaJoc;

    public Handler(Socket client, Servidor s) throws IOException {
        pEC = new PontEntreClasses(client, true);
        //inici = false;
        S = s;
        dinersJugador = 500;
        dinersJugadorsJSON = new JSONObject();
        foraCuaJoc = true;
    }

    @Override
    public void run() {
        JSONObject nomJugador = new JSONObject();

        try{
            pEC.sendByte(PontEntreClasses.S_BENVINGUT);
            pEC.receiveByte(PontEntreClasses.ACK);
            while(true) {
                nomJugador = pEC.receiveJSON();
                if(S.AfegirNick(nomJugador)) {
                    pEC.sendByte(PontEntreClasses.ACK);
                    pEC.receiveByte(PontEntreClasses.ACK);
                    break;
                }
                else {
                    pEC.sendByte(PontEntreClasses.S_NICK_EN_US);
                    pEC.receiveByte(PontEntreClasses.ACK);
                }
            }

            if(Servidor.partidaComencada) {
                S.AfegirHandlerEspera(this);
                pEC.sendByte(PontEntreClasses.S_EN_CURS);
                pEC.receiveByte(PontEntreClasses.ACK);
                synchronized(this) { wait(); }
            }
            else
                S.AfegirHandler(this);

            pEC.sendByte(PontEntreClasses.S_ESTAS_DINS);
            pEC.receiveByte(PontEntreClasses.ACK);
            pEC.sendInt(dinersJugador);
            pEC.receiveByte(PontEntreClasses.ACK);
            
            while(true) {
                Servidor.filaDeU.acquire();
                System.out.println();
                System.out.println("És el torn del jugador " + nomJugador + "!");
                pEC.sendByte(PontEntreClasses.S_APOSTA);

                JSONObject apostaJugador = pEC.receiveJSON();
                pEC.sendByte(PontEntreClasses.ACK);
                Servidor.filaDeU.release();
                dinersJugador -= apostaJugador.getInt("Diners");
                int dinersApostats = apostaJugador.getInt("Diners");
                if(!Servidor.ruletaCanviada)
                    synchronized(this) { wait(); }
                JSONObject resultatRuleta = Servidor.resultat;

                JSONObject resultat = new JSONObject();
                if((apostaJugador.getInt("Tipus") == 0 && apostaJugador.getInt("Aposta") != resultatRuleta.getInt("Numero")) || (apostaJugador.getInt("Tipus") == 1 && apostaJugador.getInt("Aposta") != resultatRuleta.getInt("Color")) || (apostaJugador.getInt("Tipus") == 2 && apostaJugador.getInt("Aposta") != resultatRuleta.getInt("Parell_Imparell")))
                    resultat.put("Guanys", 0);
                else {
                    if(apostaJugador.getInt("Tipus") == 0 && apostaJugador.getInt("Aposta") == resultatRuleta.getInt("Numero")) //Número
                        resultat.put("Guanys", apostaJugador.getInt("Diners")*36);
                    else if(apostaJugador.getInt("Tipus") == 1 && apostaJugador.getInt("Aposta") == resultatRuleta.getInt("Color")) //Color
                        resultat.put("Guanys", apostaJugador.getInt("Diners")*2);
                    else if(apostaJugador.getInt("Tipus") == 2 && apostaJugador.getInt("Aposta") == resultatRuleta.getInt("Parell_Imparell")) //Parell_Imparell
                        resultat.put("Guanys", apostaJugador.getInt("Diners")*2);
                }
                dinersJugador += resultat.getInt("Guanys");
                pEC.sendJSON(resultatRuleta);
                pEC.receiveByte(PontEntreClasses.ACK);

                S.DinersJugadors(dinersApostats, nomJugador.getString("Nickname")+"_Apostat", resultat.getInt("Guanys"), nomJugador.getString("Nickname")+"_Guanys");
                synchronized(this) { wait(); }
                pEC.sendJSON(dinersJugadorsJSON);
                pEC.receiveByte(PontEntreClasses.ACK);

                pEC.sendInt(dinersJugador);
                pEC.receiveByte(PontEntreClasses.ACK);

                pEC.sendByte(PontEntreClasses.S_CONTINUAR);
                Servidor.numJugadors.release();
                foraCuaJoc = false;
                S.AfegirHandlerEsperaADirSi(this);

                if(pEC.receiveByte() == PontEntreClasses.C_PLEGAR) {
                    S.AlliberaNick(nomJugador.getString("Nickname"));
                    S.EliminarHandler(this);
                    pEC.close();
                    break;
                }

                if(Servidor.partidaComencada)
                    S.AfegirHandlerEspera(this);
                else
                    S.AfegirHandler(this);
                
                Servidor.numJugadors.acquire();
                foraCuaJoc = true;
                pEC.sendByte(PontEntreClasses.S_ESTAS_DINS);
                pEC.receiveByte(PontEntreClasses.ACK);
            }
        }
        catch(Exception e) {
            try {
                throw new Excepcio("Un client s'ha acabat inesperadament!");
            } catch (Excepcio e1) {
                e1.printStackTrace();
            }
            S.AlliberaNick(nomJugador.getString("Nickname"));
            S.EliminarHandler(this);
            Servidor.filaDeU.release();
            if(foraCuaJoc)
                Servidor.numJugadors.release();
        }
    }

    public void DinersJugadors(JSONObject json) {
        dinersJugadorsJSON = json;
        synchronized(this) { notify(); }
    }

    public void Desperta() {
        synchronized(this) { notify(); }
    }
}
