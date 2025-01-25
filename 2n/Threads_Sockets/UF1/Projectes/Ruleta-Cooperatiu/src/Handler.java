import org.json.JSONObject;

public class Handler implements Runnable{

    static PontEntreClasses pEC;
    static Servidor S;
    static boolean inici;
    static int dinersJugador;
    static JSONObject dinersJugadorsJSON;

    public Handler(PontEntreClasses p, Servidor s) {
        pEC = p;
        inici = false;
        S = s;
        dinersJugador = 500;
        dinersJugadorsJSON = new JSONObject();
    }

    @Override
    public void run() {
        try{
            pEC.sendByte(PontEntreClasses.S_BENVINGUT);
            pEC.receiveByte(PontEntreClasses.ACK);
            inici = S.partidaComencada;
            JSONObject nomJugador = new JSONObject();
            while(true) {
                nomJugador = pEC.receiveJSON();
                if(S.AfegirNick(nomJugador)) {
                    pEC.sendByte(PontEntreClasses.ACK);
                    pEC.receiveByte(PontEntreClasses.ACK);
                    break;
                }
                else {
                    pEC.sendByte(PontEntreClasses.S_NICK_EN_US);
                }
            }


            if(inici) {
                pEC.sendByte(PontEntreClasses.S_EN_CURS);
                pEC.receiveByte(PontEntreClasses.ACK);
                synchronized(this) { wait(); }
            }
            
            pEC.sendByte(PontEntreClasses.S_ESTAS_DINS);
            pEC.receiveByte(PontEntreClasses.ACK);
            pEC.sendInt(dinersJugador);
            pEC.receiveByte(PontEntreClasses.ACK);
            
            while(true) {
                Servidor.filaDeU.acquire();
                pEC.sendByte(PontEntreClasses.S_APOSTA);

                JSONObject apostaJugador = pEC.receiveJSON();
                pEC.sendByte(PontEntreClasses.ACK);
                Servidor.filaDeU.release();
                dinersJugador -= apostaJugador.getInt("Diners");
                int dinersApostats = apostaJugador.getInt("Diners");
                if(!S.ruletaCanviada)
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

                if(pEC.receiveByte() == PontEntreClasses.C_PLEGAR) {
                    S.AlliberaNick(nomJugador.getString("Nickname"));
                    S.EliminarHandler(this);
                    break;
                }

                pEC.sendByte(PontEntreClasses.S_ESTAS_DINS);
                pEC.receiveByte(PontEntreClasses.ACK);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            Servidor.filaDeU.release();
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
