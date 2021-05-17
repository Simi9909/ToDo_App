package database;

import model.Task;
import org.tinylog.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class TaskRepository extends DateBase<Task> {

    public List<Task> Query() {
        EntityManager em = DBConnection.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        Root<Task> from = cq.from(Task.class);

        cq.select(from);

        try {
            Query q = em.createQuery(cq);
            Logger.info("Table loaded successfully");
            return q.getResultList();
        } catch (Exception e) {
            Logger.error("Select failed");
        } finally {
            em.close();
        }
        return new ArrayList<>();
    }

}
