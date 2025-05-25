package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Book;
import org.acme.model.Loan;
import org.acme.service.LoanService;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.mappers.MapToList;
import org.acme.utils.tree.BinaryTree;

import java.util.List;

public class LoanApi {

    @Inject
    LoanService loanService;

    @POST
    @Path("/apply")
    @Produces(MediaType.APPLICATION_JSON)
    public Response applyForLoan(String idBook, String userForApply) {
        try {
            SimpleLinkedList<Loan> loans = loanService.applyForLoan(idBook, userForApply);
            List<Loan> loansList = MapToList.simpleLinkedListToList(loans);
            return Response.status(Response.Status.CREATED).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @POST
    @Path("/return")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnLoan(String userString, String idLoan) {
        try {
            SimpleLinkedList<Loan> loans = loanService.returnLoan(userString, idLoan);
            List<Loan> loansList = MapToList.simpleLinkedListToList(loans);
            return Response.status(Response.Status.OK).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @POST
    @Path("/cancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelLoan(String userString, String idLoan) {
        try {
            SimpleLinkedList<Loan> loans = loanService.cancelLoan(userString, idLoan);
            List<Loan> loansList = MapToList.simpleLinkedListToList(loans);
            return Response.status(Response.Status.OK).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoans() {
        try {
            SimpleLinkedList<Loan> loans = loanService.getLoans();
            List<Loan> loansList = MapToList.simpleLinkedListToList(loans);
            return Response.status(Response.Status.OK).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @GET
    @Path("/get-loans-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserLoans(String userString) {
        try {
            SimpleLinkedList<Loan> loans = loanService.getUserLoans(userString);
            List<Loan> loansList = MapToList.simpleLinkedListToList(loans);
            return Response.status(Response.Status.OK).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @POST
    @Path("/qualify-loan/{idLoan}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response qualifyLoan(@PathParam("idLoan") String idLoan, @QueryParam("qualification") int qualification) {
        try {
            BinaryTree<Book> loans = loanService.qualifyLoan(idLoan, qualification);
            List<Book> loansList = MapToList.binaryTreeToList(loans);
            return Response.status(Response.Status.OK).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }
}
