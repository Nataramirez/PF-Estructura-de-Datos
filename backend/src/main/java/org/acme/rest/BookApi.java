package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Book;
import org.acme.service.BookService;

import java.util.List;

@Path("/book")
public class BookApi {

    @Inject
    BookService bookService;

    @POST
    @Path("/create")
    public Response createBook(Book book){
        boolean created = bookService.createBook(book);
        return Response.status(created ? Response.Status.CREATED : Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Path("/delete/{bookId}")
    public Response deleteBook(@PathParam("bookId") String bookId){
        bookService.deleteBook(bookId);
        return Response.ok().build();
    }

    @GET
    @Path("/search/category")
    public Response searchBooksCategory(@QueryParam("category") String category){
        List<Book> books = bookService.searchBooksCategory(category);
        return Response.ok(books).build();
    }

}
