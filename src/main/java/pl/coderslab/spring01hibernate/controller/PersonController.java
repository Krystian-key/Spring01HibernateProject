package pl.coderslab.spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.dao.PersonDao;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Person;

@Controller
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    //    - zapis encji
    @RequestMapping("/Person/add")
    @ResponseBody
    public String hello() {
        Person person = new Person();
        person.setLogin("smokinglogin");
        person.setEmail("Welka@asd.interia.pl");
        person.setPassword("seecretPassword");
        personDao.savePerson(person);
        return "Id to:"
                + person.getId();
    }

    //    - edycja encji
    @RequestMapping("/person/update/{id}/{login}/{password}/{email}")
    @ResponseBody
    public String updatePerson(@PathVariable long id, @PathVariable String login, @PathVariable String password, @PathVariable String email) {
        Person person = personDao.findById(id);
        person.setLogin(login);
        person.setPassword(password);
        person.setEmail(email);
        personDao.update(person);
        return person.toString();
    }

    //- pobieranie
    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    //- usuwanie
    @RequestMapping("/person/delete/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable long id) {
        Person author = personDao.findById(id);
        personDao.delete(author);
        return "deleted";
    }








}
