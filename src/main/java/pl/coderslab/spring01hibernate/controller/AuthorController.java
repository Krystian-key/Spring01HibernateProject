package pl.coderslab.spring01hibernate.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.BookDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;
import pl.coderslab.spring01hibernate.entity.Author;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthorController {
    private final AuthorDao authorDao;
    private BookDao bookDao;
    private PublisherDao publisherDao;

    public AuthorController(AuthorDao authorDao, BookDao bookDao , PublisherDao publisherDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
    }

    @GetMapping("/author/all")
    @ResponseBody
    public String showAll(){
        List<Author> authors = authorDao.getAll();
        String collect = authors.stream()
                .map(Author::toString)
                .collect(Collectors.joining(", \r\n <br>"));
        return collect;
    }

    @GetMapping("/getBooks/{authorId}")
    @ResponseBody
    @Transactional
    public String getBooks(@PathVariable Long authorId){
        Author author = authorDao.findById(authorId);

        Hibernate.initialize(author.getBooks());

        return author.getBooks().toString();
    }

    //    - zapis encji
    @RequestMapping("/author/add")
    @ResponseBody
    public String hello() {
        Author author = new Author();
        author.setFirstName("Jejms");
        author.setLastName("Welk");
        authorDao.saveAuthor(author);
        return "Id to:"
                + author.getId();
    }

    //    - edycja encji
    @RequestMapping("/author/update/{id}/{firstName}/{lastName}")
    @ResponseBody
    public String updateAuthor(@PathVariable long id, @PathVariable String firstName, @PathVariable String lastName) {
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.update(author);
        return author.toString();
    }

    //- pobieranie
    @RequestMapping("/author/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        return author.toString();
    }

    //- usuwanie
    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "deleted";
    }


}