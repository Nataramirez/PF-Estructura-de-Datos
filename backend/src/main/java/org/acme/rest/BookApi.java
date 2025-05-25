package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Book;
import org.acme.service.BookService;
import org.acme.utils.mappers.MapToList;
import org.acme.utils.tree.BinaryTree;

import java.util.List;

@Path("/book")
public class BookApi {

    @Inject
    BookService bookService;

    @Inject
    MapToList mapToList;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {
        System.out.println("📩 createBook() fue llamado");
        try {
            bookService.createBook(book);
            var list = mapToList.simpleLinkedListToList(bookService.getBooks().toList());
            return Response.status(Response.Status.CREATED).entity(list).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el libro: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{bookId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("bookId") String bookId) {
        try {
            BinaryTree<Book> books = bookService.deleteBook(bookId);
            return Response.status(Response.Status.OK).entity(books).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @GET
    @Path("/search/category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchBooksCategory(@QueryParam("category") String category) {
        try {
            List<Book> books = mapToList.simpleLinkedListToList(bookService.searchBooksCategory(category));
            return Response.status(Response.Status.OK).entity(books).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @GET
    @Path("/search/name-or-author")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchBooksNameOrAuthor(@QueryParam("param") String param) {
        try {
            List<Book> books = mapToList.simpleLinkedListToList(bookService.searchBooksNameOrAuthor(param));
            return Response.status(Response.Status.OK).entity(books).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }
}
