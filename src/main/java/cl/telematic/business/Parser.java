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
            XMLStreamReader xmlr = xmlif.createXMLStreamReader(reader);

            int event = xmlr.getEventType();

            while (xmlr.hasNext()) {

                switch (event) {

                    /*case XMLStreamConstants.START_DOCUMENT:
                        System.out.println("Start Document " + xmlr.getVersion());
                        break; Version is Null */

                    case XMLStreamConstants.START_ELEMENT:
                        //System.out.println("Start Element " + xmlr.getName() + " " + xmlr.getAttributeCount());
                        if (xmlr.getName().toString() == "DATA") {
                            System.out.println(xmlr.getAttributeName(0).toString() + " " + xmlr.getAttributeValue(0));
                            System.out.println(xmlr.getAttributeName(1).toString() + " " + xmlr.getAttributeValue(1));
                            System.out.println(xmlr.getElementText());
                        }


                        break;
                    case XMLStreamConstants.ATTRIBUTE:
                        System.out.println("Attribute " + xmlr.getAttributeName(0));
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        xmlr.close();
                        System.out.println("End");
                        break;
                }

                event = xmlr.next();


            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


}
