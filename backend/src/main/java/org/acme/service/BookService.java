package org.acme.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.book.Book;
import org.acme.model.enums.book.BookCategory;
import org.acme.model.enums.book.BookState;
import org.acme.utils.Data;
import org.acme.utils.list.Queue;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.mappers.MapToList;
import org.acme.utils.tree.BinaryTree;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookService {

    @Inject
    MapToList mapToList;

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("books.json");
    private static boolean alreadyLoaded = false;

    public BookService() {
        if (!alreadyLoaded) {
            System.out.println("📚 Iniciando BookService...");
            loadBooksFromFile();
            alreadyLoaded = true;
        }
    }

    public BinaryTree<Book> createBook(Book book) throws Exception {
        if (!BookCategory.isValidCategory(book.getCategory())) {
            throw new Exception("La categoria enviada no es válida");
        }
        setInitialDataBook(book);
        Data.books.insert(book);
        try {
            saveBooksToFile();
        } catch (IOException e) {
            System.err.println("❌ Error al guardar libros: " + e.getMessage());
        }
        return getBooks();
    }

    public void setInitialDataBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        book.setPendingLoans(new Queue<>());
        book.setScore(0);
        book.setState(BookState.AVAILABLE.getValue());
        book.setCategory(book.getCategory().toUpperCase());
    }

    //TODO: Validar que el libro no se encuentre prestado
    public BinaryTree<Book> deleteBook(String bookId) {
        Data.books.deleteValue(Book.builder().id(bookId).build());
        try {
            saveBooksToFile();
        } catch (IOException e) {
            System.err.println("❌ Error al guardar libros después de eliminar: " + e.getMessage());
        }
        return getBooks();
    }

    public SimpleLinkedList<Book> searchBooksCategory(String category) {
        Iterator<Book> iterator = Data.books.iterator();
        SimpleLinkedList<Book> books = new SimpleLinkedList<>();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getCategory().equals(category.toUpperCase())) {
                books.insertAtStart(book);
            }
        }

        return books;
    }

    public SimpleLinkedList<Book> searchBooksNameOrAuthor(String param) {
        Iterator<Book> iterator = Data.books.iterator();
        SimpleLinkedList<Book> books = new SimpleLinkedList<>();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getAuthor().toLowerCase().contains(param.toLowerCase()) ||
                    book.getName().toLowerCase().contains(param.toLowerCase())) {
                books.insertAtStart(book);
            }
        }
        return books;
    }

    public BinaryTree<Book> updateBook(Book bookUpdate) {
        Book book = Data.books.search(bookUpdate);
        book.setCategory(bookUpdate.getCategory());
        book.setName(bookUpdate.getName());
        book.setAuthor(bookUpdate.getAuthor());
        book.setYear(bookUpdate.getYear());
        return Data.books;
    }

    public BinaryTree<Book> getBooks() { return Data.books; }

    private void loadBooksFromFile() {
        System.out.println("📂 Intentando cargar books.json desde: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("⚠️ books.json no existe.");
            return;
        }

        try {
            List<Book> booksFromFile = mapper.readValue(file, new TypeReference<>() {});
            System.out.println("📥 Se encontraron " + booksFromFile.size() + " libros en el archivo.");

            Data.books.clearTree();
            for (Book b : booksFromFile) {
                Data.books.insert(b);
            }

        } catch (IOException e) {
            System.err.println("❌ Error al leer books.json: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int lastSavedCount = -1;

    private void saveBooksToFile() throws IOException {
        SimpleLinkedList<Book> bookList = Data.books.toList();
        List<Book> list = mapToList.simpleLinkedListToList(bookList);

        if (list.size() != lastSavedCount) {
            System.out.println("💾 Guardando " + list.size() + " libros");
            lastSavedCount = list.size();
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
    }

}
