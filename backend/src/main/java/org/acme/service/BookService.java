package org.acme.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import jakarta.inject.Inject;
import org.acme.model.Book;
import org.acme.model.enums.book.BookCategory;
import org.acme.model.enums.book.BookState;
import org.acme.utils.Data;
import org.acme.utils.list.Queue;
import org.acme.utils.list.SimpleLinkedList;
import org.acme.utils.tree.BinaryTree;
import org.acme.utils.mappers.MapToList;

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
    //private final BinaryTree<Book> books = new BinaryTree<>();
    private static boolean alreadyLoaded = false;

    public BookService() {
        if (!alreadyLoaded) {
            System.out.println("🚀 Iniciando BookService...");
            loadBooksFromFile();
            alreadyLoaded = true;
        }
    }

    public BinaryTree<Book> getBooks() {
        return Data.books;
    }

    public BinaryTree<Book> createBook(Book book) throws Exception {
        if (!BookCategory.isValidCategory(book.getCategory())) {
            throw new Exception("La categoría enviada no es válida");
        }

        setInitialDataBook(book);
        Data.books.insert(book);

        System.out.println("📚 Libros en memoria antes de guardar: " + mapToList.simpleLinkedListToList(Data.books.toList()).size());
        saveBooksToFile();
        return Data.books;
    }

    public void setInitialDataBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        book.setPendingLoans(new Queue<>());
        book.setScore(0);
        book.setState(BookState.AVAILABLE.getValue());
    }

    //TODO: Validar que el libro no se encuentre prestado
    public BinaryTree<Book> deleteBook(String bookId) {
        try {
            // Buscar el libro por ID
            Book book = Data.books.search(Book.builder().id(bookId).build());

            // Validar si existe
            if (book == null) {
                throw new RuntimeException("Libro no encontrado con ID: " + bookId);
            }

            // Validar que no esté prestado
            if (BookState.LOANED.getValue().equals(book.getState())) {
                throw new RuntimeException("El libro no puede ser eliminado porque está prestado.");
            }

            // Eliminar el libro
            Data.books.deleteValue(book);
            saveBooksToFile();

        } catch (IOException e) {
            System.err.println("❌ Error al guardar libros: " + e.getMessage());
        }

        return Data.books;
    }

    public SimpleLinkedList<Book> searchBooksCategory(String category) {
        Iterator<Book> iterator = Data.books.iterator();
        SimpleLinkedList<Book> result = new SimpleLinkedList<>();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.insertAtStart(book);
            }
        }
        return result;
    }

    public SimpleLinkedList<Book> searchBooksNameOrAuthor(String param) {
        Iterator<Book> iterator = Data.books.iterator();
        SimpleLinkedList<Book> result = new SimpleLinkedList<>();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getName().toLowerCase().contains(param.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(param.toLowerCase())) {
                result.insertAtStart(book);
            }
        }
        return result;
    }

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

    private void saveBooksToFile() throws IOException {
        SimpleLinkedList<Book> bookList = Data.books.toList();
        List<Book> list = mapToList.simpleLinkedListToList(bookList);
        System.out.println("💾 Guardando " + list.size() + " libros");

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
    }
}

