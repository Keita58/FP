public class SingletonCounter {

  private static SingletonCounter singletonAnime = null;

  private int countInstancies;

  private SingletonCounter() {
    countInstancies = 0;
  }

  public static synchronized SingletonCounter getInstance() {
    
    if(singletonAnime == null) {
      singletonAnime = new SingletonCounter();
    }
    else
      System.out.println("La instància ja ha estat creada.");
    
    return singletonAnime;
  }

  public void incrementInstanceCount() {
    countInstancies++;
  }

  public int getInstanceCount() {
    return countInstancies;
  }

  public Anime getNewAnime(String nom, int anyEstrena, double mitjanaDuracio) {
    incrementInstanceCount();
    return new Anime(nom, anyEstrena, mitjanaDuracio);
  }

}
