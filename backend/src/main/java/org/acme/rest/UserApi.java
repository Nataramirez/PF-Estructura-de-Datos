package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.user.User;
import org.acme.model.user.UserAuth;
import org.acme.model.user.UserDTO;
import org.acme.service.UserService;
import org.acme.utils.mappers.MapToList;
import org.acme.utils.tree.BinaryTree;

import java.util.List;

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

    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){
        try {
            BinaryTree<User> users = userService.getUsers();
            List<UserDTO> usersDTO = MapToList.binaryTreeUserToList(users);
            return Response.status(Response.Status.OK).entity(usersDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @DELETE
    @Path("/delete/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") String userId) {
        try {
            BinaryTree<User> users = userService.deleteUser(userId);
            return Response.status(Response.Status.OK).entity(MapToList.binaryTreeUserToList(users)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        try {
            BinaryTree<User> users = userService.updateUser(user);
            return Response.status(Response.Status.OK).entity(MapToList.binaryTreeUserToList(users)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
