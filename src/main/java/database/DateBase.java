package database;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

import org.tinylog.Logger;

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
            Logger.info("New entity added");
        }catch (Exception e){
            Logger.error("Failed to upload entity" + e.toString());
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
            Logger.info("Entity updated");
        }catch (Exception e){
            Logger.error("Error occurred during update" + e.toString());
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
            Logger.info("Entity deleted");
            return true;
        }catch (Exception e){
            Logger.error("Error occurred during delete" + e.toString());
            return false;
        }finally {
            em.close();
        }
    }

}
