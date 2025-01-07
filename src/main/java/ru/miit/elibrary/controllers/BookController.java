package ru.miit.elibrary.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.miit.elibrary.dtos.BookDTO;
import ru.miit.elibrary.models.Book;
import ru.miit.elibrary.models.BookAuthor;
import ru.miit.elibrary.repository.IBookRepository;
import ru.miit.elibrary.services.MainBookService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RestController
@RequestMapping("/api/book")
@Tag(name = "Юзер-контроллер книг", description = "Позволяет найти нужную книгу по автору, идентификатору, и т.д.")
@CrossOrigin("http://localhost:3000/")
public class BookController {
    @Autowired
    private final IBookRepository bookRepository;
    private final MainBookService mainBookService;

    public BookController(IBookRepository bookRepository, MainBookService mainBookService) {
        this.bookRepository = bookRepository;
        this.mainBookService = mainBookService;
    }
    private ResponseEntity<?> bookRespToBookDTO(@org.jetbrains.annotations.NotNull
                                                List<Book> books){
        if (!books.isEmpty()){
            List<BookDTO> resp1 = new ArrayList<>();
            for (Book b : books){
                resp1.add(new BookDTO(b));
            }
            return ResponseEntity.status(200).body(resp1);
        }
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/byName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книги успешно найдены"),
            @ApiResponse(responseCode = "201", description = "Запрос выполнен, книг не найдено")
    })
    public ResponseEntity<?> getBooksByName(@RequestParam String namePart){
        var resp = bookRepository.findAllByBookNameContaining(namePart);
        return bookRespToBookDTO(resp);
    }

    @GetMapping("/byAuthorIdentifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книги успешно найдены"),
            @ApiResponse(responseCode = "201", description = "Запрос выполнен, книг не найдено")
    })
    public ResponseEntity<?> getBooksByAuthor(@RequestParam String authorIdentifier){
        var resp = bookRepository.findBooksByAuthorIdentifier(authorIdentifier);
        return bookRespToBookDTO(resp);
    }
    @GetMapping("/byBookIdentifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга успешно найдена"),
            @ApiResponse(responseCode = "201", description = "Запрос выполнен, книг не найдено")
    })
    public ResponseEntity<?> getBooksByIdentifier(@RequestParam String fullIdentifier){
        var resp = bookRepository.findByIdentifier(fullIdentifier);
        return resp!=null
                ? ResponseEntity.status(200).body(new BookDTO(resp))
                : ResponseEntity.status(201).build();
    }

    @GetMapping("/byBookGenre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книга успешно найдена"),
            @ApiResponse(responseCode = "201", description = "Запрос выполнен, книг не найдено")
    })
    public ResponseEntity<?> getBooksByGenre(@RequestParam String genreName){
        var resp = bookRepository.findBooksByGenreName(genreName);
        return bookRespToBookDTO(resp);
    }

    @GetMapping("/byBookReleaseYear/{releaseYear}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Книги успешно найдены"),
            @ApiResponse(responseCode = "201", description = "Запрос выполнен, книг не найдено")
    })
    public ResponseEntity<?> getBooksByReleaseYear(@PathVariable int releaseYear){
        var resp = bookRepository.findBooksByReleaseYearNative(releaseYear);
        return bookRespToBookDTO(resp);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен список книг"),
            @ApiResponse(responseCode = "201", description = "Не найдено ни одной книги")
    })
    @Operation(summary = "Позволяет получить список книг из БД")
    @GetMapping("/getBooks")
    public ResponseEntity<?> getAllBooks(){
        var resp = mainBookService.getAllBooks();
        return bookRespToBookDTO(resp);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получен список книг"),
            @ApiResponse(responseCode = "201", description = "Не найдено ни одной книги")
    })
    @Operation(summary = "Позволяет книг (по полю название, год выпуска, идентификатор книги, идентификатор автора, и жанр)")
    @GetMapping("/search")
    public ResponseEntity<?> getBooksBySearchRequest(@RequestParam @NotNull String searchRequest){
        var resp = mainBookService.searchBooksFromSearchField(searchRequest);
        return bookRespToBookDTO(resp);
    }

}
