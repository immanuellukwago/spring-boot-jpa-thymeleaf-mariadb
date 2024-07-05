package com.immanuel.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.immanuel.start.model.Books;

import jakarta.transaction.Transactional;

@Repository
public interface  BooksRepository extends JpaRepository<Books, Long>{
    
    @Transactional
    @Modifying
    @Query("update Books b set b.myLike = ?2 where b.id  = ?1")
    int like(Long id, Integer myLike);

    @Transactional
    @Modifying
    @Query("update Books b set b.dislike = ?2 where b.id  = ?1")
    int dislike(Long id, Integer dislike);

    @Transactional
    @Modifying
    @Query("update Books b set b.title = ?1, b.author = ?2, b.description = ?3, b.rating = ?4 where b.id = ?5")
    int updateBook(String title, String author, String description, Integer rating, Long id);
}
