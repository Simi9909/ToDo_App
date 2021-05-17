package database;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

public abstract class DateBase<T> {

    private final Class<T> entityClass;

    public DateBase(){
        this.entityClass = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public void AddNewTask(T entity){
        EntityManager em = DBConnection.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            System.out.println("New antity added");
        }catch (Exception e){
            System.out.println("Failed to upload entity");
        }finally {
            em.close();
        }
    }

    public void UpdateTask(T entity){
        EntityManager em = DBConnection.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            System.out.println("Entity updated");
        }catch (Exception e){
            System.out.println("Error occurred during update" + e.toString());
        }finally {
            em.close();
        }
    }

    public boolean DeleteTask(T entity){
        EntityManager em = DBConnection.getEntityManager();
        try{
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
            System.out.println("Entity deleted");
            return true;
        }catch (Exception e){
            System.out.println("Error occurred during delete");
            return false;
        }finally {
            em.close();
        }
    }

}
