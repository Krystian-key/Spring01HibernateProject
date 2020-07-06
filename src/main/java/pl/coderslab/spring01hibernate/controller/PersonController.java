package pl.coderslab.spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernate.dao.PersonDao;
import pl.coderslab.spring01hibernate.entity.Person;

@Controller
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }


    @RequestMapping("/Person/add")
    @ResponseBody
    public String hello() {
        Person person = new Person();
        person.setLogin("smokinglogin");
        person.setEmail("Welka@asd.interia.pl");
        person.setPassword("seecretPassword");
        personDao.save(person);
        return "Id to:"
                + person.getId();
    }


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


    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @RequestMapping("/person/delete/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable long id) {
        Person author = personDao.findById(id);
        personDao.delete(author);
        return "deleted";
    }


    @RequestMapping("/form")
    public String form() {
        return "person/form";
    }


    @PostMapping("/form")
    @ResponseBody
    public Person formPost(@RequestParam String login,
                           @RequestParam String email,
                           @RequestParam String password) {

        Person person = new Person()
                .setLogin(login)
                .setEmail(email)
                .setPassword(password);

        personDao.save(person);

        return person;
    }

    @GetMapping("/formBind")
    public String formBind(Model m) {
        m.addAttribute(new Person().setLogin("example"));

        return "person/formBind";
    }

    @PostMapping("/formBind")
    @ResponseBody
    public Person formBindPost(@ModelAttribute Person person) {
        personDao.save(person);

        return person;
    }
}
