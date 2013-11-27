package cl.telematic.dao;

import cl.telematic.model.RemoteXml;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RemoteXmlDao {

    @PersistenceContext
    private EntityManager entityManager;


    public RemoteXml update(RemoteXml remoteXml) {
        return entityManager.merge(remoteXml);
    }

    public void save(RemoteXml remoteXml) {
        entityManager.persist(remoteXml);
    }


}
