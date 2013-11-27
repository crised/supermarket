package cl.telematic.business;


import cl.telematic.client.Fetcher;
import cl.telematic.model.FieldServerXml;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Stateless
public class Timer {

    @PersistenceContext
    private EntityManager entityManager;

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void doWork() {

        Fetcher fetcher = new Fetcher();
        Parser parser = new Parser();

        String response = fetcher.fetch();
        parser.Parse(response);
        save();

    }

    private void save() {

        FieldServerXml fieldServerXml = new FieldServerXml();
        fieldServerXml.setContent("<xml...>");
        entityManager.persist(fieldServerXml);

    }
}
