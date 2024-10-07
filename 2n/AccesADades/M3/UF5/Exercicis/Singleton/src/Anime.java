public class Anime {

  String nom;
  int anyEstrena;
  double mitjanaDuracio;
  
  public Anime(String nom, int anyEstrena, double mitjanaDuracio) {
    this.nom = nom;
    this.anyEstrena = anyEstrena;
    this.mitjanaDuracio = mitjanaDuracio;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getAnyEstrena() {
    return anyEstrena;
  }

  public void setAnyEstrena(int anyEstrena) {
    this.anyEstrena = anyEstrena;
  }

  public double getMitjanaDuracio() {
    return mitjanaDuracio;
  }

  public void setMitjanaDuracio(double mitjanaDuracio) {
    this.mitjanaDuracio = mitjanaDuracio;
  }

  @Override
  public String toString() {
    return "Anime [nom=" + nom + ", anyEstrena=" + anyEstrena + ", duracio=" + mitjanaDuracio + "]";
  }
}
