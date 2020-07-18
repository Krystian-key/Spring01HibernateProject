package pl.coderslab.spring01hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Book;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@Controller
@RequestMapping("/validation")
public class ValidationController {
    @Autowired
    private Validator validator;

    @GetMapping("/validateBook")
    public String validateBook(){
        Book book = new Book();
        Set<ConstraintViolation<Book>> errors = validator.validate(book);
        if (errors.isEmpty()){
            return "Zapis ksiązki: " + book.toString();
        } else {
            String message = "Mie można zapisać. Błędy walidacji: <br>\r\n";
            for (ConstraintViolation<Book> err: errors){
                message += err.getPropertyPath()
                        + " : " + err.getInvalidValue()
                        + " : " + err.getMessage()
                        + "<br\r\n";
            }
            return message;
        }
//        return
    }
}
