package cl.telematic.business;


import cl.telematic.client.Fetcher;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class Timer {

    @Schedule(second="*/10", minute="*", hour="*", persistent = false)
    public void doWork(){

        Fetcher fetcher = new Fetcher();
        fetcher.fetch();

    }
}
