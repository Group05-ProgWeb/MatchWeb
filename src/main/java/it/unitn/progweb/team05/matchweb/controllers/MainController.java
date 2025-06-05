package it.unitn.progweb.team05.matchweb.controllers;

import it.unitn.progweb.team05.matchweb.User;
import it.unitn.progweb.team05.matchweb.repositories.UserRepository;
import it.unitn.progweb.team05.matchweb.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final UserService userService;

    public MainController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("dateOfBirth") Date dateOfBirth, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(surname);
        user.setDate0fBirth(dateOfBirth);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("ROLE_USER");
        userRepository.add(user);
        return "signup-success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginFail")
    public String loginFail() {return "login-fail";}

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        userService.changePassword(oldPassword, newPassword);
        return "login-success";
    }

    @GetMapping("/football")
    public String football() {
        return "teams/football";
    }

    @GetMapping("/volleyball")
    public String volleyball() {
        return "teams/volleyball";
    }

    @GetMapping("/basketball")
    public String basketball() {
        return "teams/basketball";
    }

    @GetMapping("/sponsors")
    public String sponsors() {
        return "sponsors";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentiation, Model model) {
        model.addAttribute("firstName", authentiation.getName());
        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile() {return "profile";}

    @GetMapping("/game-calendar")
    public String gameCalendar() {return "game-calendar";}

    @GetMapping("/play")
    public String play() {return "play";}

    @GetMapping("/reviews")
    public String comments() {
        return "reviews";
    }

}
