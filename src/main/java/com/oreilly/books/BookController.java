package com.oreilly.books;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {

    private final JdbcTemplate jdbcTemplate;

    public BookController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Book> list() {
        String sql = "SELECT * FROM BOOK";
        List<Book> books = jdbcTemplate.query(sql,new BookRowMapper());
        return books;
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable int id) {
        String sql = "SELECT * FROM BOOK WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookRowMapper());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Book book) {
        String sql = "insert into book(id,title,author,publisher,release_date,isbn,topic) values (?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql,
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getReleaseDate(),
                book.getIsbn(),
                book.getTopic());

        if( insert == 1 ) {
            System.out.println("A new book was inserted into the datbase: " + book.getTitle());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Book book, @PathVariable int id) {
        String sql = "update book set title = ?, author = ?, publisher = ?, release_date = ?, isbn = ?, topic =? where id = ?";
        int update = jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getReleaseDate(),
                book.getIsbn(),
                book.getTopic(),
                id);
        if( update == 1 ) {
            System.out.println("Book: " + book.getTitle() + " was updated!");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        String sql = "delete from book where id = ?";
        int delete = jdbcTemplate.update(sql,id);
        if( delete == 0 ) {
            System.out.println("Book: " + id + " was deleted.");
        }
    }

}
