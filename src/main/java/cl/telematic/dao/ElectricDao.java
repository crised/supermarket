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



    public void save(@NonNull Electrical electrical) {
        entityManager.persist(electrical);
    }

    public void saveElectricals(@NonNull List<Electrical> electricals){

        for(Electrical electrical : electricals){
            entityManager.persist(electrical);
        }

    }


}
