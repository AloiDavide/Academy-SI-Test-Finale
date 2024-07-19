package it.testfinale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.testfinale.dto.CourseDto;
import it.testfinale.dto.CourseUpdateDto;
import it.testfinale.jwt.JWTTokenNeeded;
import it.testfinale.jwt.Secured;
import it.testfinale.model.Course;
import it.testfinale.service.CategoryService;
import it.testfinale.service.CourseService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private CategoryService categoryService;
	
    @GET
    @Path("/get/{id}") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response getCourseById(@PathParam("id") int id) {
        try {
            CourseDto courseDto = courseService.getCourseDTOById(id);
            return Response.status(Response.Status.OK).entity(courseDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @GET
    @Path("/get/byCategory/{categoryId}") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response getCoursesByCategory(@PathParam("categoryId") int categoryId) {
    	try {
            List<CourseDto> courses = courseService.findByCategory(categoryId);
            return Response.status(Response.Status.OK).entity(courses).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response getCourses() {
        try {
            List<CourseDto> courses = courseService.getCourses();
            return Response.status(Response.Status.OK).entity(courses).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @JWTTokenNeeded
    @Secured(role = "Admin")
    @POST
    @Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response createCourse(@Valid CourseDto courseDto) {
        try {
            Course createdCourse = courseService.createCourse(courseDto);
            return Response.status(Response.Status.OK).entity(createdCourse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    
    @JWTTokenNeeded
    @Secured(role = "Admin")
    @PUT
    @Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateCourse(@Valid CourseUpdateDto courseUpdateDto) {
        try {
            courseService.updateCourse(courseUpdateDto);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    
    @JWTTokenNeeded
    @Secured(role = "Admin")
    @DELETE
    @Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCourse(@PathParam("id") int id) {
        try {
            courseService.deleteCourse(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    
    @JWTTokenNeeded
    @Secured(role = "Admin")
    @DELETE
    @Path("delete/byCategory/{id}")
    public Response deleteByCategory(@PathParam("id") int catId) {
    	try {
    		courseService.deleteByCategory(catId);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    
}
