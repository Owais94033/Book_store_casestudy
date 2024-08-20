package com.BookManager.demo.Controller;


import com.BookManager.demo.dto.BookDto;
import com.BookManager.demo.model.Book;
import com.BookManager.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping //
    public List<Book> getAllAuthors() {
        return bookService.getAllbooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> authorOptional = bookService.getBookById(id);
        return authorOptional
                .map(x -> new ResponseEntity<>(x , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PostMapping
//    public Book createBook(@RequestBody Book book) {
//        return bookService.saveBook(book);
//    }
    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto dto){
        Book book = dto.toBook(dto);
        var b = dto.toDto(bookService.saveBook(book));
        return ResponseEntity.status(HttpStatus.OK).body(b);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Book> updateBook(@PathVariable Long id,
//                                           @RequestBody Book book) {
//        Optional<Book> authorOptional = bookService.getBookById(id);
//        return authorOptional
//                .map(a -> new ResponseEntity<>(a , HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto>updateBook(@Valid @RequestParam long id,@RequestBody BookDto dto){

        Book book=dto.toBook(dto);
        var b=dto.toDto(bookService.updateBook(id,book));
        return ResponseEntity.ok().body(b);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Book> authorOptional = bookService.getBookById(id);
        if(authorOptional.isPresent()){
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
