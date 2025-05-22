package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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
    public Response createBook(Book book) {
        try {
            BinaryTree<Book> books = bookService.createBook(book);
            return Response.status(Response.Status.CREATED).entity(books).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }

    @DELETE
    @Path("/delete/{bookId}")
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
    public Response searchBooksCategory(@QueryParam("category") String category) {
        try {
            List<Book> books = mapToList.simpleLinkedListToList(bookService.searchBooksCategory(category));
            return Response.status(Response.Status.OK).entity(books).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ha ocurrido un error al intentar " +
                    "procesar la solicitud. Por favor intente más tade.").build();
        }
    }
}
