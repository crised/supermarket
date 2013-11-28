package cl.telematic.rest;

import cl.telematic.dao.ElectricDao;
import cl.telematic.rest.domain.ElectricalStat;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces({MediaType.APPLICATION_JSON})
@Path("/")
@Stateless
public class ElectricalRest {

    @EJB
    ElectricDao electricDao;

    @GET
    @Path("/")
    public List<ElectricalStat> getMyDevices(){

        return electricDao.getAllElectricals();


    }
}
