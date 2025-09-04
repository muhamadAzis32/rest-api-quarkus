package org.acme.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.UserData;

import java.net.URI;
import java.util.*;

@Path("users")
public class UserResource {

    private Map<String, UserData> users = new HashMap<>();

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        return Response.ok(users.values()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserData user){
        users.put(user.getEmail(), user);
        return Response.created(URI.create("/users/" +user.getEmail())).build();
    }

    @Path("{email}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(String email){
        UserData user = users.get(email);
        if (Objects.isNull(user)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

}
