package ru.gb.library_online.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.library_online.model.Book;
import ru.gb.library_online.service.BookService;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping(value = {"/", "index", "index.html"})
    public String getHome() {
        return "index";
    }

    @GetMapping(value = {"/books", "/books.html"})
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        return "books";
    }

    @GetMapping(value = {"/book-save", "/book-save.html"})
    public String getBookSaveForm(Book book) {
        return "book-save";
    }

    @PostMapping(value = {"/book-save", "/book-save.html"})
    public String saveBook(Book book) {
        bookService.saveBook(book);

        return "redirect:/books";
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable(name="id") int id, Model model) {
        System.out.println(bookService.getBookById(id));
        model.addAttribute("book", bookService.getBookById(id));

        return "book";
    }
}
