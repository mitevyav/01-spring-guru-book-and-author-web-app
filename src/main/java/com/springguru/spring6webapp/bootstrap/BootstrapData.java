package com.springguru.spring6webapp.bootstrap;

import com.springguru.spring6webapp.domain.Author;
import com.springguru.spring6webapp.domain.Book;
import com.springguru.spring6webapp.domain.Publisher;
import com.springguru.spring6webapp.repositories.AuthorRepository;
import com.springguru.spring6webapp.repositories.BookRepository;
import com.springguru.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123123");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("321321");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        Publisher publisher = new Publisher();
        publisher.setAddress("1234 Main St");
        publisher.setCity("Miami");
        publisher.setState("FL");
        publisher.setZip("33101");
        publisher.setPublisherName("SFG Publishing");
        Publisher saved = publisherRepository.save(publisher);

        dddSaved.setPublisher(saved);
        noEJBSaved.setPublisher(saved);

        ericSaved.getBooks().add(noEJBSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(noEJBSaved);
        bookRepository.save(dddSaved);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());



        System.out.println("Number of Publishers: " + publisherRepository.count());
    }
}
