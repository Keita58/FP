public class main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SingletonCounter counter = SingletonCounter.getInstance();
        SingletonCounter counter2 = SingletonCounter.getInstance();
        
        Anime anime1 = counter.getNewAnime("Naruto", 2002, 24.5);
        Anime anime2 = counter.getNewAnime("One Piece", 1999, 23.8);
        Anime anime3 = counter.getNewAnime("Attack on Titan", 2013, 25.0);

        System.out.println("Nombre total d'instàncies d'anime creades: " + counter.getInstanceCount());

        System.out.println("Dades de l'anime 1:");
        System.out.println("Nom: " + anime1.getNom());
        System.out.println("Any d'estrena: " + anime1.getAnyEstrena());
        System.out.println("Mitjana de duració dels capítols: " + anime1.getMitjanaDuracio());    
    }
}
