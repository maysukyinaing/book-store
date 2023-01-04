package com.ace.bookstore.controller;

import com.ace.bookstore.dao.BookDao;
import com.ace.bookstore.model.Book;
import com.ace.bookstore.model.BookDto;
import com.ace.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookApiController {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookService bookService;

    @GetMapping("/init")
    public String init() {
        Book b1 = new Book("Blue Moon","T.M Thomas",
                25.4,"A good to read novel", "https://picsum.photos/400", List.of("nice","good to read"), LocalDate.now());

        Book b2 = new Book("A Tale of Two Cities","Dickens",
                50.4,"A good to read novel",
                "https://picsum.photos/400", List.of("nice","good to read"), LocalDate.now());

        Book b3 = new Book("A Return of Native","Hardy",
                30.4,"A good to read novel",
                "https://picsum.photos/400", List.of("nice","good to read"), LocalDate.now());

        Book b4 = new Book("Under the green wood tree","Hardy",
                25.4,"A good to read novel",
                "https://picsum.photos/400", List.of("nice","good to read"), LocalDate.now());

        Book b5 = new Book("A pair of blue eyes","Hardy",
                25.4,"A good to read novel",
                "https://picsum.photos/400", List.of("nice","good to read"), LocalDate.now());

        Book b6 = new Book("A pale view of Hill","Dickson",
                25.4,"A good to read novel",
                "https://picsum.photos/400", List.of("nice","good to read"), LocalDate.now());

        Book b7 = new Book("The Unconsoled","T.M Thomas",
                25.4,"A good to read novel",
                "https://picsum.photos/400", List.of("nice","good to read"), LocalDate.now());

        Book b8 = new Book("The Good","T.M Thomas",
                25.4,"A good to read novel",
                "https://picsum.photos/400", List.of("nice","good to read"), LocalDate.now());

        List.of(b1,b2,b3,b4,b5,b6,b7,b8)
                .stream()
                .forEach(bookDao::save);


        return "success";
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @PostMapping(value = "/book/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        System.out.println(bookDto);
        Book book = bookService.saveBook(bookDto);
        if (book.getId() > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/book/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> update(@PathVariable("id")int bookId,@RequestBody BookDto bookDto) {
        if (bookService.isExist(bookId)){
            BookDto updateBook = this.bookService.updateImplicit(bookId,bookDto);
            return ResponseEntity.status(HttpStatus.OK).body(updateBook);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable("id")int bookId) {
        if (bookService.isExist(bookId)){
            this.bookService.deleteBook(bookId);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
