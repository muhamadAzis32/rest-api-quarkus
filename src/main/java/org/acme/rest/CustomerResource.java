package org.acme.rest;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.CustomerData;
import org.acme.dto.UserData;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Path("customers")
public class CustomerResource {

    private Map<String, CustomerData> customers = new HashMap<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers(){
        return  Response.ok(customers.values()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerData customer){
        customers.put(customer.getPhone(), customer);
        return Response.created(URI.create("/customers/"+customer.getPhone())).build();
    }


}
