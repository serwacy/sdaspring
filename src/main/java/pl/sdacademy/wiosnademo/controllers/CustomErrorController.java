package pl.sdacademy.wiosnademo.controllers;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController extends AbstractErrorController {

  public CustomErrorController(final ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @RequestMapping("/error")
  public String handleError(HttpMethod httpMethod) {
    System.out.println(httpMethod);
    return "error";
  }

  @Override
  public String getErrorPath() { // NIE JEST UZYWANA
    return "DUPANIEJESTEMUZYWAMY";
  }
}
