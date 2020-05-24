package pl.sdacademy.wiosnademo.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserForm;
import pl.sdacademy.wiosnademo.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  // ModelAndView -> opcjonalna zwrotna z kontrolerów
  public String showUsers(final ModelMap modelMap) {
    modelMap.addAttribute("users", userService.getAllUsers());
    return "users";
  }

  @GetMapping("/create")
  public String showUserForm(final ModelMap modelMap) {
    modelMap.addAttribute("userForm", new UserForm());
    return "users-create";
  }

  @PostMapping("/create")
  public String handleNewUser(@Valid @ModelAttribute(name = "userForm") final UserForm userForm,
                              final Errors errors) {
    if (errors.hasErrors()) {
      return "users-create";
    }

    userService.createUser(userForm);
    return "redirect:/users";
  }

  @PostMapping("/delete/{username}")
  public String deleteUser(@PathVariable final String username) {
    // dodać check żeby nie można usunąć siebie samego
    userService.delete(username);
    return "redirect:/users";
  }

  @GetMapping("/edit/{username}")
  public String showEditForm(@PathVariable final String username, final ModelMap modelMap) {
    final User user = userService.getByUsername(username);
    modelMap.addAttribute("userForm", new UserForm(user.getUsername(),
        user.getEmail(), "", ""));
    return "users-edit";
  }

  @PostMapping("/edit/{username}")
  public String handleUserEdit(@PathVariable final String username, @Valid @ModelAttribute final UserForm userForm,
                               final Errors errors) {
    userService.updateUser(username, userForm);
    if (errors.hasErrors()) {
      return "users-edit";
    }
    return "redirect:/users";
  }

  @ExceptionHandler(RuntimeException.class)
  public String handleException(final RuntimeException exp, final Model model) { // WTF
    model.addAttribute("msg", exp.getMessage());
    return "error";
  }

}
