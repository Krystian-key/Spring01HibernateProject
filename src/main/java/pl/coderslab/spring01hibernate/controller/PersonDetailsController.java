package pl.coderslab.spring01hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernate.dao.PersonDetailsDao;
import pl.coderslab.spring01hibernate.entity.PersonDetails;

@Controller
public class PersonDetailsController {

    private final PersonDetailsDao personDetailsDao;

    public PersonDetailsController(PersonDetailsDao personDetailsDao) {
        this.personDetailsDao = personDetailsDao;
    }

    @RequestMapping("/PersonDetails/add")
    @ResponseBody
    public String hello() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("andrzej");
        personDetails.setLastName("nowak");
        personDetails.setCity("krakow");
        personDetails.setStreet("krakowska");
        personDetails.setStreetNumber(12);
        personDetailsDao.savePersonDetails(personDetails);
        return "Id to:"
                + personDetails.getId();
    }

    //    - edycja encji
    @RequestMapping("/personDetails/update/{id}/{firstName}/{lastName}/{city}/{street}/{streetNumber}")
    @ResponseBody
    public String updatePersonDetails(@PathVariable long id, @PathVariable String firstName, @PathVariable String lastName,
           @PathVariable String city, @PathVariable String street, @PathVariable Integer streetNumber) {
        PersonDetails personDetails = personDetailsDao.findById(id);
        personDetails.setFirstName(firstName);
        personDetails.setLastName(lastName);
        personDetails.setCity(city);
        personDetails.setStreet(street);
        personDetails.setStreetNumber(streetNumber);
        personDetailsDao.update(personDetails);
        return personDetails.toString();
    }

    //- pobieranie
    @RequestMapping("/personDetails/get/{id}")
    @ResponseBody
    public String getPersonDetails(@PathVariable long id) {
        PersonDetails personDetails = personDetailsDao.findById(id);
        return personDetails.toString();
    }

    //- usuwanie
    @RequestMapping("/personDetails/delete/{id}")
    @ResponseBody
    public String deletePersonDetails(@PathVariable long id) {
        PersonDetails personDetails = personDetailsDao.findById(id);
        personDetailsDao.delete(personDetails);
        return "deleted";
    }


}
