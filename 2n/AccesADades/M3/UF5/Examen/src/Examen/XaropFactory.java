package Examen;

public class XaropFactory {

	public Xarop crearXarop(String tipus) {
        if (tipus.equalsIgnoreCase("Xarop Menta")) {
            XaropMenta menta = new XaropMenta();
            return menta;
        } 
        else if (tipus.equalsIgnoreCase("Xarop Maduixa")) {
            XaropMaduixa maduixa = new XaropMaduixa();
            return maduixa;
        }
        return null;
    }
}
