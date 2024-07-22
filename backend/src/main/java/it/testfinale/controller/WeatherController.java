package it.testfinale.controller;

import it.testfinale.dto.WeatherDto;
import it.testfinale.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.testfinale.jwt.JWTTokenNeeded;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
}
