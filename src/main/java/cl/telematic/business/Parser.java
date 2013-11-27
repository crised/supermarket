package cl.telematic.business;

import javax.ejb.Stateless;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.Reader;
import java.io.StringReader;

@Stateless
public class Parser {

    public void Parse(String response) {

        Reader reader = new StringReader(response);
        XMLInputFactory xmlif = XMLInputFactory.newInstance();

        try {
            XMLStreamReader xml = xmlif.createXMLStreamReader(reader);

            int event = xml.getEventType();

            while (xml.hasNext()) {

                switch (event) {

                    /*case XMLStreamConstants.START_DOCUMENT:
                        System.out.println("Start Document " + xml.getVersion());
                        break; Version is Null */

                    case XMLStreamConstants.START_ELEMENT:
                        //System.out.println("Start Element " + xml.getName() + " " + xml.getAttributeCount());

                        if (xml.getAttributeValue(0).equals("DA_Dev_1000")) {
                            System.out.println("Inside if");
                            System.out.println(xml.getAttributeName(0).toString() + " " + xml.getAttributeValue(0));
                            xml.next();
                            xml.next();
                            System.out.println(xml.getAttributeName(0).toString() + " " + xml.getAttributeValue(0));
                            System.out.println(xml.getElementText());
                        }
                        break;
                    case XMLStreamConstants.ATTRIBUTE:
                        System.out.println("Attribute " + xml.getAttributeName(0));
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        xml.close();
                        System.out.println("End");
                        break;
                }

                event = xml.next();


            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


}
