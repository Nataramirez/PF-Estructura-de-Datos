package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.user.User;
import org.acme.model.user.UserAuth;
import org.acme.service.UserService;
import org.acme.utils.mappers.MapToList;

@Path("/user")
public class UserApi {

    @Inject
    UserService userService;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        try {
            User userCreated = userService.createUser(user);
            return Response.status(Response.Status.CREATED).entity(MapToList.userToUserDTO(userCreated)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @POST
    @Path("/auth")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authUser(UserAuth userAuth) {
        try {
            User user = userService.userAuth(userAuth.getUser(), userAuth.getPassword());
            if (user != null) return Response.status(Response.Status.OK).entity(MapToList.userToUserDTO(user)).build();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }
}
