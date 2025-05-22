package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.acme.model.User;
import org.acme.service.UserService;

@Path("/user")
public class UserApi {

    @Inject
    UserService userService;

    @POST
    @Path("/create")
    public Response createUser(User user) {
        try {
            User userCreated = userService.createUser(user);
            return Response.status(Response.Status.CREATED).entity(userCreated).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }
}
