package com.oreilly.books;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SqlBookService implements BookService {

    private JdbcTemplate jdbcTemplate;

    public SqlBookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
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
}
