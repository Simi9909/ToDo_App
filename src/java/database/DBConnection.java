package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {

    private DBConnection(){
    }

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void closeEmf(){
        emf.close();
    }

    public static void openEmf(){
        emf.isOpen();
    }

}
