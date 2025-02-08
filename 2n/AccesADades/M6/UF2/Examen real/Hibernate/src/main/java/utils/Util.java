package utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {
    static EntityManagerFactory factory;

    public static synchronized EntityManagerFactory getCurrentEntityManager() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("Examen");
        }
        return factory;
    }

    public static void close() {
        factory.close();
    }

}
