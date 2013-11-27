package cl.telematic.client;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@Stateless
public class Fetcher {

    static String url = "http://64.60.250.222/data_arrays.xml";

    public String fetch() {

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        response.close();
        return value;
    }



}
