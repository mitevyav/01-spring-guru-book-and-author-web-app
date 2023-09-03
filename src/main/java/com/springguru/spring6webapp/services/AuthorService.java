package com.springguru.spring6webapp.services;

import com.springguru.spring6webapp.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();
}
