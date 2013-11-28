package cl.telematic.business;

import cl.telematic.utils.DArrays;
import cl.telematic.utils.Shared;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.Date;
import java.util.Map;

@Singleton
@javax.ejb.Startup
public class Startup {

    public Map<DArrays, Date> ArrayMap;

    @PostConstruct
    void init() {

        Shared shared = new Shared();
        ArrayMap = shared.arraysDateMap;

    }


}
