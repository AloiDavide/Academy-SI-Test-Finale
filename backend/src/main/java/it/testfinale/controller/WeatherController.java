package it.testfinale.controller;

import it.testfinale.dto.BackendWeatherResponse;
import it.testfinale.dto.WeatherDto;
import it.testfinale.service.WeatherService;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.testfinale.jwt.JWTTokenNeeded;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@RestController
@Path("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;


    @POST
    @Path("/save/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWeather(@RequestBody WeatherDto weatherDto, @PathParam("email") String email) {
        try {
            weatherService.saveWeather(weatherDto, email);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


    @GET
    @Path("/all/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getWeathersByUser(@PathParam("email") String email) {
        try {
            List<BackendWeatherResponse> response =  weatherService.getAllByUser(email);
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
