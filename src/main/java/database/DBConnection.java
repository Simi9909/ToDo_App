package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Creates a database connection class
 */
public class DBConnection {

    private DBConnection() {
    }

    /**
     * Method for creating Entity Manager Factory for connection with database
     */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    /**
     * Method for creating an entity manager anywhere in the program.
     *
     * @return new entity manager.
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * {@code closeEmf()} closes the connection with the database
     */
    public static void closeEmf() {
        emf.close();
    }

    /**
     * {@code openEmf()} opens the connection with the database
     */
    public static void openEmf() {
        emf.isOpen();
    }

}
