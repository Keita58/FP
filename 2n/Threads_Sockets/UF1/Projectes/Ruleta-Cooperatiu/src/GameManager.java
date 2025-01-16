import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class GameManager implements Runnable{

    static List<String> nicksJugadors = new ArrayList<>();

    public GameManager() {

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public boolean AfegirNick(JSONObject nomJugador) {
        if(nicksJugadors.contains(nomJugador.getString("Nickname"))) {
            return false;
        }
        else {
            nicksJugadors.add(nomJugador.getString("Nickname"));
            return true;
        }
    }
}

/*
    Random r = new Random();

    Colors: 0 -> Negre / 1 -> Vermell / 2 -> Verd
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
 */
