import org.json.JSONObject;

public class Handler implements Runnable{

    static PontEntreClasses pEC;
    static GameManager GM;
    static boolean inici;

    public Handler(PontEntreClasses pEC, GameManager GM) {
        this.pEC = pEC;
        this.inici = false;
        this.GM = GM;
    }

    @Override
    public void run() {
        try{
            pEC.sendByte(PontEntreClasses.S_BENVINGUT);
            pEC.receiveByte(PontEntreClasses.ACK);
            while(true) {
                JSONObject nomJugador = new JSONObject();
                nomJugador = pEC.receiveJSON();
                if(GM.AfegirNick(nomJugador)) {
                    pEC.sendByte(PontEntreClasses.ACK);
                    pEC.receiveByte(PontEntreClasses.ACK);
                    break;
                }
                else {
                    pEC.sendByte(PontEntreClasses.S_NICK_EN_US);
                }
            }
            
            
            while(true) {
                
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void Comenca() {
        inici = true;
    }
}
