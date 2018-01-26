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
    @Path("/register/{a}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String registerDevice(int devicenumber) {

        int result = ovenDao.registerDevice(devicenumber);
        if(result == 1){
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @POST
    @Path("/changeTemperature/{a}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String changeTemperature(double delta) {

        int result = ovenDao.changeTemperature(delta);

        if(result == 1){
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;    }

    @POST
    @Path("/setMode/{a}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String setMode(@FormParam("mode") Oven.Mode newmode) {

        int result = ovenDao.setMode(newmode);
        if(result == 1){
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;    }

    public static void main(String[] args) {



       }
}
