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

    // create


    // update


    // delete

}
