package com.immanuel.start.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immanuel.start.model.Books;
import com.immanuel.start.repository.BooksRepository;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public void saveBook(Books books) {
        books.setCreationDate(LocalDateTime.now());
        books.setMyLike(0);
        books.setDislike(0);
        booksRepository.save(books);
    }

    public Books getBooksById(Long id) {
        Optional<Books> optional = booksRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else 
            throw new RuntimeException("The book you are searching for isn't present!");
    }

    public void deleteById(Long id) {
        booksRepository.deleteById(id);
    }

    public void like(Long id) {
        Optional<Books> optional = booksRepository.findById(id);
        if (optional.isPresent()) {
            Books books = optional.get();
            Integer like = 0;
            if (books.getMyLike() == null) {
                like = 1;
            } else {
                like = books.getMyLike() + 1;
            }
            booksRepository.like(id, like);
        } else 
            throw new RuntimeException("Book does not exist");
    }

    public void dislike(Long id) {
        Optional<Books> optional = booksRepository.findById(id);
        if (optional.isPresent()) {
            Books books = optional.get();
            Integer dislike = 0;
            if (books.getDislike() == null) {
                dislike = 1;
            } else {
                dislike = books.getDislike() + 1;
            }            booksRepository.dislike(id, dislike);
        } else 
            throw new RuntimeException("Book does not exist");
    }

    public void updateBook(String title, String author, String description, Integer rating, Long id) {
        booksRepository.updateBook(title, author, description, rating, id);
    }
}
