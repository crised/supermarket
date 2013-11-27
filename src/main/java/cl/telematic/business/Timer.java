package cl.telematic.business;


import cl.telematic.client.Fetcher;
import cl.telematic.dao.ElectricDao;
import cl.telematic.dao.RemoteXmlDao;
import cl.telematic.dao.TempDao;
import cl.telematic.model.Electrical;
import cl.telematic.model.RemoteXml;
import cl.telematic.model.Temp;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class Timer {

    @EJB
    private ElectricDao electricDao;

    @EJB
    private TempDao tempDao;

    @EJB
    private RemoteXmlDao remoteXmlDao;

    @EJB
    private Parser parse;

    @EJB
    private Fetcher fetcher;



    @Schedule(second = "*/4", minute = "*", hour = "*", persistent = false)
    public void doWork() {

        String response = fetcher.fetch();
        save(response);

    }

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void doCalmWork(){
        readFromDb();
    }

    private void save(String xml) {

        RemoteXml remoteXml = new RemoteXml();
        remoteXml.setContent(xml);
        remoteXml.setCreatedOn(new Date());
        remoteXmlDao.save(remoteXml);

    }

    private void readFromDb(){

        List<RemoteXml> remoteXmlList = remoteXmlDao.getNewXmls();

        for(RemoteXml remoteXml : remoteXmlList){
            Electrical electrical = new Electrical();
            Temp temp = new Temp();
            temp.setOriginXml(remoteXml);
            electrical.setOriginXml(remoteXml);
            remoteXml.setParsed(new Date());
            remoteXmlDao.update(remoteXml);
            String content = remoteXml.getContent();
            Double[] values = parse.parse(content);
            electrical.setEnergyReading(values[0]);
            electrical.setPowerReading(values[1]);
            temp.setTemperatureReading(values[2]);
            electricDao.save(electrical);
            tempDao.save(temp);

        }

    }


}
