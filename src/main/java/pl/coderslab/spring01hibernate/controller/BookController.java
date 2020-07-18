package pl.coderslab.spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.BookDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Publisher;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private final BookDao bookDao;
    private PublisherDao publisherDao;
    private AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }


    @GetMapping("/book/addNew")
    @ResponseBody
    public String addNew(){
        Publisher publisher = new Publisher();
        publisher.setName("KRK");
        publisherDao.savePublisher(publisher);

        Author author = new Author();
        author.setFirstName("Henryk");
        author.setLastName("Sienkiewicz");
        authorDao.saveAuthor(author);

        Book book = new Book();
        book.setTitle("W pustyni i w puszczy");
        book.setPublisher(publisher);
        book.getAuthors().add(author);

        bookDao.create(book);

        return "dodano";
    }

    @GetMapping("/book/anyPublisher")
    @ResponseBody
    public String showWithPublisher(){
        List<Book> books = bookDao.getPublisherIsNotNull();
        String collect = books.stream()
                .map(Book::toString)
                .collect(Collectors.joining(", \r\n <br>"));
        return collect;
    }

    @GetMapping("/book/all")
    @ResponseBody
    public String showAll(){
        List<Book> books = bookDao.readAll();
        String collect = books.stream()
                .map(Book::toString)
                .collect(Collectors.joining(", \r\n <br>"));
        return collect;
    }

    @GetMapping("/byRating/{rating}")
    @ResponseBody
    public String byRating(@PathVariable int rating){
        List<Book> books = bookDao.getRatingList(rating);
        String collect = books.stream()
                .map(Book::toString)
                .collect(Collectors.joining(", \r\n <br>"));
        return collect;
    }

    @GetMapping("/byPublisher/{id}")
    @ResponseBody
    public String byPublisher(@PathVariable long id){
        List<Book> books = bookDao.getPublisherById(id);
        String collect = books.stream()
                .map(Book::toString)
                .collect(Collectors.joining(", \r\n <br>"));
        return collect;
    }

    @GetMapping("/byAuthor/{name}")
    @ResponseBody
    public String byPublisher(@PathVariable String name){
        List<Book> books = bookDao.getByAuthor(name);
        String collect = books.stream()
                .map(Book::toString)
                .collect(Collectors.joining(", \r\n <br>"));
        return collect;
    }

    //    - zapis encji
    @RequestMapping("/book/add")
    @ResponseBody
    public String hello() {
        Book book = new Book();
        book.setTitle("Thinking in Java");
        book.setRating(10);
        book.setDescription("Be a good programmer");
        bookDao.create(book);
        return "Id dodanej książki to:"
                + book.getId();
    }

    //    - edycja encji
    @RequestMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    //- pobieranie
    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    //- usuwanie
    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
    }
}
