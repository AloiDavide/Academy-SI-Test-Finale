package it.testfinale.jwt;

import java.io.IOException;
import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;



@JWTTokenNeeded
@Provider	//Marks this class as a provider for @JWTTokenNeeded
public class JWTTokenNeededFilter implements ContainerRequestFilter {
    
	@Context 	// Dependency injection to give this class the context of the method that triggered the filter, in particular its annotations
	private ResourceInfo resourceInfo;
  
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
  
		// Check if the HTTP Authorization header is present and formatted correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}
  
		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim(); 
  
		try {
			// Validate the token by checking that it's the same as the one that was issued
			byte[] secret = "supersecretpassword1230000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".getBytes();
			Key key = Keys.hmacShaKeyFor(secret);  
    
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); 
			Claims body = claims.getBody();
			

		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}
