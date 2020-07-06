package pl.coderslab.spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.dao.BookDao;
import pl.coderslab.spring01hibernate.dao.PublisherDao;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Book;
import pl.coderslab.spring01hibernate.entity.Publisher;

import java.util.List;


@Controller
public class BookFormController {
    private BookDao bookDao;
    private PublisherDao publisherDao;
    private AuthorDao authorDao;

    public void BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/addForm")
    public String addForm(Model model) {
        model.addAttribute(new Book());
        return "/book/addForm";
    }
    @PostMapping("/addForm")
    public String addFormPost(@ModelAttribute Book book){
        bookDao.save(book);

        return "redirect:all";
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers(){
        return publisherDao.readAll();
    }

    @ModelAttribute("authors")
    public List<Author> authors(){
        return authorDao.readAll();
    }

    @ModelAttribute("books")
    public List<Book> books(){
        return bookDao.readAll();
    }

}
