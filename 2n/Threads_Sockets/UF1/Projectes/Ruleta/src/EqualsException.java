public class EqualsException {

    public void Comparar(String s, String s2) throws Excepcio {
		if(!s.equals(s2)) {
			throw new Excepcio("Les strings son diferents");
		} else {
			System.out.println("Niceee");
		}
	}
}
