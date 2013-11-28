package cl.telematic.business;

import cl.telematic.model.Electrical;
import cl.telematic.model.Temp;
import cl.telematic.utils.DArrays;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

@Stateless
public class Parser {

    private static final Logger Logger = org.jboss.logging.Logger.getLogger(Parser.class);


    public void parse(List<Electrical> electricals, List<Temp> temps, String stringXml) {

        Reader reader = new StringReader(stringXml);
        XMLInputFactory xmlif = XMLInputFactory.newInstance();

        try {
            XMLStreamReader xml = xmlif.createXMLStreamReader(reader);

            int event = xml.getEventType();

            // TODO: Make quick  consistency check, e.g. number of Data Arrays in XML, if fail throw application error.
            // TODO: Throw Application Error if something is wrong setting the instances.

            while (xml.hasNext()) {

                switch (event) {

                    case XMLStreamConstants.START_ELEMENT:

                        String dataArrayName = xml.getAttributeValue(0);
                        Integer ordinal = null;
                        for (DArrays dArray : DArrays.values()) {
                            if (dataArrayName.equals(dArray.getName())) ordinal = dArray.ordinal();
                        }
                        if (ordinal == null || ordinal > 5) break;

                        xml.next();
                        xml.next();

                        if(ordinal <= 3) //electrical object
                        {

                            Electrical electrical = electricals.get(ordinal); //Electrical Object
                            electrical.setRealRead(getRealDate(xml.getAttributeValue(1)));
                            List<Double> doubles = extractDoubles(xml.getElementText());
                            electrical.setPowerReading(doubles.get(0));
                            electrical.setEnergyReading(doubles.get(1));

                        }

                        if(ordinal > 3) //temp object
                        {
                            ordinal = ordinal - 4; //ordinal 4 must be 0, ordinal 5 must be 1
                            Temp temp = temps.get(ordinal);
                            temp.setRealRead(getRealDate(xml.getAttributeValue(1)));
                            List<Double> doubles = extractDoubles(xml.getElementText());
                            temp.setTemperatureReading(doubles.get(0));
                        }

                        break;
                    case XMLStreamConstants.ATTRIBUTE:
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        xml.close();
                        break;
                }

                event = xml.next();

            }

        } catch (Exception e) {
            Logger.error("error");
            Logger.info(e.getMessage());
        }

    }

    private List<Double> extractDoubles(String text) {

        List<Double> values = new ArrayList<>();
        String[] numbers = text.split(" ");
        for (String number : numbers) values.add(Double.parseDouble(number));
        return values;
    }

    private Date getRealDate(String dataAge) {

        // days:hours:minutes:seconds.ms
        // 19:16:00:43.239s
        String dataAgeStrip = dataAge.substring(0, dataAge.indexOf("."));
        String numbers[] = dataAgeStrip.split(":");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -Integer.valueOf(numbers[0]));
        cal.add(Calendar.HOUR, -Integer.valueOf(numbers[1]));
        cal.add(Calendar.MINUTE, -Integer.valueOf(numbers[2]));
        cal.add(Calendar.SECOND, -Integer.valueOf(numbers[3]));
        return cal.getTime();

    }


}
