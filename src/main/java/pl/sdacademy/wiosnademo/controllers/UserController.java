package pl.sdacademy.wiosnademo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
