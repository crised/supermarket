package cl.telematic.utils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Shared {

    public Map<DArrays, Date> arraysDateMap;

    public Shared() {

        arraysDateMap = new ConcurrentHashMap<>();
        for (DArrays dArrays : DArrays.values()) arraysDateMap.put(dArrays, null);

    }

}
