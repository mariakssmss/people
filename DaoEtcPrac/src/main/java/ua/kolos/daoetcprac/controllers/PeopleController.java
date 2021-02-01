package ua.kolos.daoetcprac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.kolos.daoetcprac.dao.PersonDAO;
import ua.kolos.daoetcprac.models.Person;

import javax.validation.Valid;
@Controller
//в юрл при запуске сервера вводим сначала /people
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired//внедрение подхолящего бина
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()//если ничего не прописать то мы попадем сюда лишь даже прописав реквест маппинг
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){//с помощью пасвариабл мы извлекаем айди из юрлл и можем засунуть его в наш метод
       model.addAttribute("person", personDAO.show(id));
       return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    //на этапе проверяет с помощью аннотации валид сототтвествуют ли значения в вью прописаным валидациям в моделе
    //байндинг резалт должен идти сразу после модели которая валидируется, в него будут внедрены все ошибки валидации
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
