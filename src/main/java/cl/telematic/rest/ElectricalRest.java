package cl.telematic.rest;

import cl.telematic.model.Electrical;
import cl.telematic.model.RemoteXml;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Produces({MediaType.APPLICATION_JSON})
@Path("/")
@Stateless
public class ElectricalRest {

    @GET
    @Path("/")
     public List<Electrical> getMyDevices(){

        RemoteXml remoteXml = new RemoteXml();
        Electrical electrical1 = new Electrical(remoteXml);
        Electrical electrical2 = new Electrical(remoteXml);
        List<Electrical> goofy = new ArrayList<>();
        goofy.add(electrical1);
        goofy.add(electrical2);
        return goofy;


    }
}
