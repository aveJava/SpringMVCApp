package controllers;

import dao.PersonDAO;
import models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson() {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person")Person person) {
        personDAO.save(person);
        return "people/successPage";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/edit";
    }

//    @PostMapping  (альтернативный вариант записи метода create)
//    public String create(@RequestParam(value = "name") String name,
//                         @RequestParam(value = "age") int age,
//                         @RequestParam(value = "sex") boolean sex,
//                         Model model) {
//        personDAO.addPerson(name, age, sex);
//        model.addAttribute("name", name);
//        return "people/successPage";
//    }

}
