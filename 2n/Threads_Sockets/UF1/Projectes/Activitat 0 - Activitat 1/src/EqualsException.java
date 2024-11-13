public class EqualsException {

    public void Comparar(String s, String s2) throws Interrupcio {
		if(!s.equals(s2)) {
			throw new Interrupcio("Les strings son diferents");
		} else {
			System.out.println("Niceee");
		}
	}
}
