package com.oven;

import com.oven.dao.OvenDao;
import com.oven.domain.Oven;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/OvenService")
public class OvenService {

    private OvenDao ovenDao = new OvenDao();

    private static final String SUCCESS_RESULT="<result>success</result>";
    private static final String FAILURE_RESULT="<result>failure</result>";

    @POST
    @Path("/register/{a}/{b}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String registerDevice(@FormParam("id") long id, @FormParam("devicenumber") int devicenumber) {

        int result = ovenDao.registerDevice(id, devicenumber);
        if(result == 1){
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @POST
    @Path("/changeTemperature/{a}/{b}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String changeTemperature(@FormParam("id") int id, @FormParam("delta") double delta) {

        int result = ovenDao.changeTemperature(id, delta);

        if(result == 1){
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;    }

    @POST
    @Path("/setMode/{a}/{b}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String setMode(@FormParam("id") int id, @FormParam("mode") Oven.Mode newmode) {

        int result = ovenDao.setMode(id, newmode);
        if(result == 1){
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;    }

    public static void main(String[] args) {



       }
}
