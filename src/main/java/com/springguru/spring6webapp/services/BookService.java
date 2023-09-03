package com.springguru.spring6webapp.services;

import com.springguru.spring6webapp.domain.Book;

public interface BookService {

    Iterable<Book> findAll();
}
