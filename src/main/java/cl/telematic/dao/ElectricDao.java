package cl.telematic.dao;

import cl.telematic.model.Electrical;
import cl.telematic.model.RemoteXml;
import com.sun.javafx.beans.annotations.NonNull;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ElectricDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RemoteXml> getNewXmls() {
        return entityManager.createQuery("select x from RemoteXml x where x.parsed is empty OR x.parsed is null", RemoteXml.class).getResultList();
    }

    public void save(@NonNull Electrical electrical) {
        entityManager.persist(electrical);
    }


}
