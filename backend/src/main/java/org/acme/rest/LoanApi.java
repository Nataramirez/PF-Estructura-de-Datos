package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.book.Book;
import org.acme.model.book.BookDTO;
import org.acme.model.loan.Loan;
import org.acme.model.loan.LoanDTO;
import org.acme.service.LoanService;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.mappers.MapToList;
import org.acme.utils.tree.BinaryTree;

import java.util.List;

@Path("/loan")
public class LoanApi {

    @Inject
    LoanService loanService;

    @POST
    @Path("/apply")
    @Produces(MediaType.APPLICATION_JSON)
    public Response applyForLoan(@QueryParam("idBook") String idBook, @QueryParam("userForApply") String userForApply) {
        try {
            SimpleLinkedList<Loan> loans = loanService.applyForLoan(idBook, userForApply);
            List<LoanDTO> loansList = MapToList.simpleLinkedListLoansToList(loans);
            return Response.status(Response.Status.CREATED).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @POST
    @Path("/return")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnLoan(@QueryParam("userString") String userString, @QueryParam("idLoan") String idLoan) {
        try {
            SimpleLinkedList<Loan> loans = loanService.returnLoan(userString, idLoan);
            List<LoanDTO> loansList = MapToList.simpleLinkedListLoansToList(loans);
            return Response.status(Response.Status.OK).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @POST
    @Path("/cancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelLoan(@QueryParam("userString") String userString, @QueryParam("idLoan") String idLoan) {
        try {
            SimpleLinkedList<Loan> loans = loanService.cancelLoan(userString, idLoan);
            List<LoanDTO> loansList = MapToList.simpleLinkedListLoansToList(loans);
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
            List<LoanDTO> loansList = MapToList.simpleLinkedListLoansToList(loans);
            return Response.status(Response.Status.OK).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @GET
    @Path("/get-loans-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserLoans(@QueryParam("userString") String userString) {
        try {
            SimpleLinkedList<Loan> loans = loanService.getUserLoans(userString);
            List<LoanDTO> loansList = MapToList.simpleLinkedListLoansToList(loans);
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
            BinaryTree<Book> books = loanService.qualifyLoan(idLoan, qualification);
            List<BookDTO> loansList = MapToList.binaryTreeBookToList(books);
            return Response.status(Response.Status.OK).entity(loansList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }
}
