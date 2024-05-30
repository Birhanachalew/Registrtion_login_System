package Birhan.Registrtion_login.controller;

import Birhan.Registrtion_login.dto.UserDto;
import Birhan.Registrtion_login.entity.User;
import Birhan.Registrtion_login.service.UserService;
import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import java.util.List;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
@GetMapping("/users")
public String user(Model model){
    List<UserDto> users = userService.findAllUser();
    model.addAttribute("users", users);
    return "users";
}
    @GetMapping("/register")

    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public  String reguistation(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){

        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() )
  1=null && !existingUser.getEmail().isEmpty() {
            result.rejectValue("email", null, "there us already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addArreibute("user", userDto);
            return "/register";

        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

}
