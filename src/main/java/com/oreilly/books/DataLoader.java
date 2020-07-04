package com.oreilly.books;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private BookService bookService;

    public DataLoader(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Book> books = new ArrayList<>();

        Book one = new Book(1,
                "97 Things Every Java Programmer Should Know",
                "Kevlin Henney, Trisha Gee",
                "OReilly Media, Inc.",
                "May 2020",
                "9781491952696",
                "Java");
        books.add(one);

        Book two = new Book(1,
                "Spring Boot: Up and Running",
                "Mark Heckler",
                "OReilly Media, Inc.",
                "February 2021",
                "9781492076919",
                "Spring");
        books.add(two);

        Book three = new Book(1,
                "Hacking with Spring Boot 2.3: Reactive Edition",
                "Greg L. Turnquist",
                "Amazon.com Services LLC",
                "May 2020",
                "B086722L4L",
                "Spring");
        books.add(three);

        for(Book book : books) {
            bookService.create(book);
        }
    }
}
