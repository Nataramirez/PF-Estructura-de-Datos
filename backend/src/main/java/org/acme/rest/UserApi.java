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
        boolean created = userService.createUser(user);
        return Response.status(created ? Response.Status.CREATED : Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
