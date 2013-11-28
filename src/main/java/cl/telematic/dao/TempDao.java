package cl.telematic.dao;

import cl.telematic.model.Electrical;
import cl.telematic.model.Temp;
import com.sun.javafx.beans.annotations.NonNull;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TempDao {


    @PersistenceContext
    private EntityManager entityManager;



    public void save(@NonNull Temp temp) {
        entityManager.persist(temp);
    }

    public void saveTemps(@NonNull List<Temp> temps){

        for(Temp temp : temps){
            entityManager.persist(temp);
        }

    }
}
