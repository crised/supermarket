package cl.telematic.business;


import cl.telematic.client.Fetcher;
import cl.telematic.dao.ElectricDao;
import cl.telematic.dao.RemoteXmlDao;
import cl.telematic.model.Electrical;
import cl.telematic.model.RemoteXml;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class Timer {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private ElectricDao electricDao;

    @EJB
    private RemoteXmlDao remoteXmlDao;

    @EJB
    private Parser parse;

    @Schedule(second = "*/4", minute = "*", hour = "*", persistent = false)
    public void doWork() {

        Fetcher fetcher = new Fetcher();
        String response = fetcher.fetch();
        save(response);
        readFromDb();

    }

    private void save(String xml) {

        RemoteXml remoteXml = new RemoteXml();
        remoteXml.setContent(xml);
        remoteXml.setCreatedOn(new Date());
        entityManager.persist(remoteXml);

    }

    private void readFromDb(){

        List<RemoteXml> remoteXmlList = electricDao.getNewXmls();

        for(RemoteXml remoteXml : remoteXmlList){
            Electrical electrical = new Electrical();
            electrical.setOriginXml(remoteXml);
            String content = remoteXml.getContent();
            parse.parse(content, electrical);
            electricDao.save(electrical);
            remoteXmlDao.update(remoteXml);

        }

    }


}
