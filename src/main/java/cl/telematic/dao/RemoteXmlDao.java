package cl.telematic.dao;

import cl.telematic.model.RemoteXml;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RemoteXmlDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RemoteXml> getNewXmls() {
        return entityManager.createQuery("select x from RemoteXml x where x.parsed is empty OR x.parsed is null", RemoteXml.class).getResultList();
    }

    public RemoteXml update(RemoteXml remoteXml) {
        return entityManager.merge(remoteXml);
    }

    public void save(RemoteXml remoteXml) {
        entityManager.persist(remoteXml);
    }


}
