package web.controller;


import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    // внедрение зависимости через конструктор!
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //получим всех людей из DAO через Service и передадим на отбражение в представление
    @GetMapping()
    public String index(Model model) {

        model.addAttribute("users", userService.index());
        return "index";
    }


    //получим одного из людей по id из DAO через Service и передадим на отбражение в представление
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("user", userService.show(id));
        return "show";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "new";
    }


    // bindingResult - объект в котором будут лежать все ошибки заполнения формы
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors())                  // если есть ошибка заполнения - сразу возврат.
            return "new";

        userService.save(user);
        return "redirect:/users";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {

        model.addAttribute("user", userService.show(id));
        return "edit";
    }


    @PatchMapping("/{id}")
    public String update(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "edit";

        userService.update(user);
        return "redirect:/users";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        userService.delete(id);
        return "redirect:/users";
    }
}

