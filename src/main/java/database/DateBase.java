package database;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

import org.tinylog.Logger;

/**
 * Abstract class what contains the the basic database operations
 *
 * @param <T>
 */
public abstract class DateBase<T> {

    private final Class<T> entityClass;

    public DateBase() {
        this.entityClass = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * Generic method for adding nem task to database
     *
     * @param entity the task we want ot add
     */
    public void AddNewTask(T entity) {
        EntityManager em = DBConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            Logger.info("New entity added");
        } catch (Exception e) {
            Logger.error("Failed to upload entity" + e.toString());
        } finally {
            em.close();
        }
    }

    /**
     * Generic method for updating a task in the database
     *
     * @param entity the task we want to update
     */
    public void UpdateTask(T entity) {
        EntityManager em = DBConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            Logger.info("Entity updated");
        } catch (Exception e) {
            Logger.error("Error occurred during update" + e.toString());
        } finally {
            em.close();
        }
    }

    /**
     * Generic method for deleting a task from the databse
     *
     * @param entity the task we want to delete
     */

    public void DeleteTask(T entity) {
        EntityManager em = DBConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
            Logger.info("Entity deleted");
        } catch (Exception e) {
            Logger.error("Error occurred during delete" + e.toString());
        } finally {
            em.close();
        }
    }

}
