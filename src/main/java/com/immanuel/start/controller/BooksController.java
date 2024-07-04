package com.immanuel.start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.immanuel.start.model.Books;
import com.immanuel.start.service.BooksService;

@Controller
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("index", new String());
        return "index";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        //return new ResponseEntity<List<Books>>(booksService.getAllBooks(), HttpStatus.OK);
        model.addAttribute("books", booksService.getAllBooks().toArray());
        return "books";
    }

    @GetMapping("/addBook")
    public String getBookToSave(Model model) {
        model.addAttribute("book", new Books());
        return "saveBook";
    }

    @PostMapping("/save")
    public String saveBooks(@ModelAttribute("book") Books books) {
        booksService.saveBook(books);
        return "redirect:/books";
    }

    @GetMapping("/viewBook/{id}")
    public String getBook(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("book", booksService.getBooksById(id));
        return "aboutBook";
    }

    @GetMapping("/update/{id}")
    public String updateBook(Model model ,@PathVariable(name = "id") Long id) {
        model.addAttribute("book", booksService.getBooksById(id));
        return "updateBook";
    }

    @PostMapping("/saveUpdate/{id}")
    public String saveUpdated(@ModelAttribute("book") Books books, @PathVariable(name = "id") Long id) {
        booksService.updateBook(books.getTitle(), books.getAuthor(), books.getDescription(), books.getRating(), id);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(Model model ,@PathVariable(name = "id") Long id) {
        model.addAttribute("book", booksService.getBooksById(id));
        booksService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/like/{id}")
    public String like(@PathVariable(name = "id") Long id) {
        booksService.like(id);
        return "redirect:/books";
    }

    @GetMapping("/dislike/{id}")
    public String dislike(@PathVariable(name = "id") Long id) {
        booksService.dislike(id);
        return "redirect:/books";
    }
}
