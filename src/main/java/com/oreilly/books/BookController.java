package com.oreilly.books;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {

    @GetMapping
    public List<Book> list() {
        return null;
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable int id) {
        return null;
    }
}
