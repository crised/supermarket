package cl.telematic.business;

import cl.telematic.model.Electrical;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.Reader;
import java.io.StringReader;

@Stateless
public class Parser {

    private static final Logger Logger = org.jboss.logging.Logger.getLogger(Parser.class);


    public Double[] parse(String response) {

        Reader reader = new StringReader(response);
        XMLInputFactory xmlif = XMLInputFactory.newInstance();

        try {
            XMLStreamReader xml = xmlif.createXMLStreamReader(reader);

            int event = xml.getEventType();

            while (xml.hasNext()) {

                switch (event) {

                    case XMLStreamConstants.START_ELEMENT:
                        if (xml.getAttributeValue(0).equals("DA_Dev_1000")) {
                            xml.next();
                            xml.next();
                            String text = xml.getElementText();
                            //Logger.info(text);
                            String[] numbers = text.split(" ");
                            for(String s : numbers){
                                //Logger.info(s);
                            }

                            Double values[] = new Double[3];
                            values[0] = Double.parseDouble(numbers[0]);
                            values[1] = Double.parseDouble(numbers[1]);
                            values[2] = Double.parseDouble(numbers[2]);
                            return values;
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
            return null;

        } catch (Exception e) {
            Logger.error("error");
            Logger.info(e.getMessage());
            return null;
        }

    }
}
