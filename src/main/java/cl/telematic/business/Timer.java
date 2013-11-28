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
import java.util.*;

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
            //More objects can be created if 1 XML brings more than one of the same object.
            List<Electrical> electricals = electricalsFactory(remoteXml);
            List<Temp> temps = tempFactory(remoteXml);
            remoteXml.setParsed(new Date());
            remoteXmlDao.update(remoteXml);
            String content = remoteXml.getContent();
            parse.parse(electricals,temps,content);
            electricDao.saveElectricals(electricals);
            tempDao.saveTemps(temps);

        }

    }

    private List<Electrical> electricalsFactory(RemoteXml remoteXml){

        List<Electrical> electricals = new ArrayList<>();
        electricals.add(new Electrical(remoteXml));
        electricals.add(new Electrical(remoteXml));
        electricals.add(new Electrical(remoteXml));
        electricals.add(new Electrical(remoteXml));

        return electricals;

    }

    private List<Temp> tempFactory(RemoteXml remoteXml){

        List<Temp> temps = new ArrayList<>();
        temps.add(new Temp(remoteXml));
        temps.add(new Temp(remoteXml));

        return temps;



    }






}
