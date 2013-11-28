package cl.telematic.dao;

import cl.telematic.model.Electrical;
import cl.telematic.rest.domain.ElectricalStat;
import com.sun.javafx.beans.annotations.NonNull;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ElectricDao {

    @PersistenceContext
    private EntityManager entityManager;


    public void save(@NonNull Electrical electrical) {
        entityManager.persist(electrical);
    }

    public void saveElectricals(@NonNull List<Electrical> electricals) {

        for (Electrical electrical : electricals) {
            entityManager.persist(electrical);
        }

    }

    public List<ElectricalStat> getAllElectricals() {

        TypedQuery<ElectricalStat> query = entityManager.createQuery("select new cl.telematic.rest.domain.ElectricalStat(e.energyReading, e.powerReading) from Electrical e", ElectricalStat.class);
        List<ElectricalStat> electricalStatses = query.getResultList();
        return electricalStatses;



       //return entityManager.createQuery("select e.energyReading, e.powerReading from Electrical e", ElectricalStat.class).getResultList();
    }


}
