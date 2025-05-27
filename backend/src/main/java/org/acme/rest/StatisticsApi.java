package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.book.Book;
import org.acme.model.user.User;
import org.acme.service.StatisticsService;
import org.acme.utils.Data;

import java.util.*;

@Path("/statistics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatisticsApi {

    @Inject
    StatisticsService statisticsService;

    // 1. Cantidad de préstamos por lector
    @GET
    @Path("/loans-per-user")
    public Response getLoansPerUser() {
        return Response.ok(statisticsService.getLoanCountPerUser()).build();
    }

    // 2. Libros más valorados
    @GET
    @Path("/top-rated-books")
    public Response getTopRatedBooks(@QueryParam("limit") @DefaultValue("5") int limit) {
        return Response.ok(statisticsService.getTopRatedBooks(limit)).build();
    }

    // 3. Lectores con más conexiones
    @GET
    @Path("/most-connected-users")
    public Response getMostConnectedUsers() {
        Map<User, List<User>> affinityGraph = Data.affinityGraph;
        return Response.ok(statisticsService.getMostConnectedUsers(affinityGraph)).build();
    }

    // 4. Camino más corto entre dos lectores
    @GET
    @Path("/shortest-path")
    public Response getShortestPath(@QueryParam("startUser") String startUser,
                                    @QueryParam("endUser") String endUser) {
        Map<User, List<User>> affinityGraph = Data.affinityGraph;

        User start = Data.users.search(User.builder().user(startUser).build());
        User end = Data.users.search(User.builder().user(endUser).build());

        if (start == null || end == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Uno o ambos usuarios no existen").build();
        }

        List<User> path = statisticsService.getShortestPath(start, end, affinityGraph);
        return Response.ok(path).build();
    }

    // 5. Clústeres de afinidad
    @GET
    @Path("/clusters")
    public Response getAffinityClusters() {
        Map<User, List<User>> affinityGraph = Data.affinityGraph;
        List<Set<User>> clusters = statisticsService.getAffinityClusters(affinityGraph);
        return Response.ok(clusters).build();
    }
}