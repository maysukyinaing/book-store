package com.ace.bookstore.service;

import com.ace.bookstore.dao.BookDao;
import com.ace.bookstore.model.Book;
import com.ace.bookstore.model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public Book saveBook(BookDto bookDto){
        return bookDao.save(toEntity(bookDto));
    }

    @Transactional
    public BookDto updateImplicit(int id,BookDto bookDto) {
        Book oldBook = this.bookDao.findById(id).get();
        oldBook.setId(bookDto.getId());
        oldBook.setTitle(bookDto.getTitle());
        oldBook.setAuthor(bookDto.getAuthor());
        oldBook.setPrice(bookDto.getPrice());
        oldBook.setDescription(bookDto.getDescription());
        oldBook.setImageUrl(bookDto.getImageUrl());
        oldBook.setComments(bookDto.getComments());
        oldBook.setPublishedDate(bookDto.getPublishedDate());
        return toDto(oldBook);
    }

    public boolean isExist(int id) {
        return this.bookDao.existsById(id);
    }

    public void deleteBook(int id) {
        this.bookDao.deleteById(id);
    }

    public BookDto toDto(Book book){
        return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getPrice(),
            book.getDescription(),
            book.getImageUrl(),
            book.getComments(),
            book.getPublishedDate()
        );
    }
    public Book toEntity(BookDto bookDto) {
        return new Book(
            bookDto.getTitle(),
            bookDto.getAuthor(),
            bookDto.getPrice(),
            bookDto.getDescription(),
            bookDto.getImageUrl(),
            bookDto.getComments(),
            bookDto.getPublishedDate()
        );
    }

}
