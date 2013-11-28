package cl.telematic.business;

import cl.telematic.utils.DArrays;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
@Singleton
@Startup
public class Single {

    // Used only if want to know the latest Update Time of the Data Array
    public static Map<DArrays, Date> DArrayMap;

    @PostConstruct
    private void init() {

        initMap();

    }

    private void initMap() {

        DArrayMap = new ConcurrentHashMap<>();
        for (DArrays dArrays : DArrays.values()) DArrayMap.put(dArrays, null);

    }


}   */
