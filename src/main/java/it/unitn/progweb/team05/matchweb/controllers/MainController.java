package it.unitn.progweb.team05.matchweb.controllers;

import it.unitn.progweb.team05.matchweb.exceptions.MultipleBetslipsException;
import it.unitn.progweb.team05.matchweb.feign.PartiteWebClient;
import it.unitn.progweb.team05.matchweb.models.*;
import it.unitn.progweb.team05.matchweb.repositories.GiornataRepository;
import it.unitn.progweb.team05.matchweb.repositories.ReviewRepository;
import it.unitn.progweb.team05.matchweb.repositories.UserRepository;
import it.unitn.progweb.team05.matchweb.services.CalcolaPunteggio;
import it.unitn.progweb.team05.matchweb.services.MatchService;
import it.unitn.progweb.team05.matchweb.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final GiornataRepository giornataRepository;
    private final UserService userService;
    private final MatchService matchService;
    private final CalcolaPunteggio calcolaPunteggio;
    private final PartiteWebClient partiteWebClient;

    public MainController(UserRepository userRepository,
                          UserService userService,
                          ReviewRepository reviewRepository,
                          MatchService matchService,
                          CalcolaPunteggio calcolaPunteggio,
                          GiornataRepository giornataRepository, PartiteWebClient partiteWebClient) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.reviewRepository = reviewRepository;
        this.matchService = matchService;
        this.calcolaPunteggio = calcolaPunteggio;
        this.giornataRepository = giornataRepository;
        this.partiteWebClient = partiteWebClient;
    }

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("firstName", authentication.getName());
            if(userRepository.isAdmin(authentication.getName())){
                return "admin-dashboard";
            } else {
                return "dashboard";
            }
        }

        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("dateOfBirth") Date dateOfBirth, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("sport") String sport, @RequestParam("favoriteTeam") String favoriteTeam) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(surname);
        user.setDateOfBirth(dateOfBirth);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("ROLE_USER");
        user.setSport(sport);
        user.setFavoriteTeam(favoriteTeam);
        userRepository.add(user);
        return "redirect:/login";
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
        return "redirect:/";
    }

    @GetMapping("/football")
    public String football(Model model) {
        model.addAttribute("teams", partiteWebClient.getTeams());
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
    public String dashboard(Authentication authentication, Model model) {
        model.addAttribute("firstName", authentication.getName());
        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile() {return "profile";}

    @GetMapping("/game-calendar")
    public String gameCalendar() {return "game-calendar";}

    @GetMapping("/play")
    public String play(Model model) {
        List<MatchDTO> matches = matchService.getMatchesFromCurrentMatchDay();
        model.addAttribute("matchday", matchService.getCurrentMatchDay());
        model.addAttribute("matches", matches);
        return "play";
    }

    @PostMapping("/play")
    @ResponseBody
    public ResponseEntity<?> play(@RequestBody BetSlip betSlip) {
        try {
            return ResponseEntity.ok(calcolaPunteggio.evaluateBetSlip(betSlip));
        } catch (MultipleBetslipsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Limit of one betslip per day exceeded");
        }

    }

    @GetMapping("/reviews")
    public String comments(Model model) {
        model.addAttribute("reviews", reviewRepository.getAll());
        return "reviews";
    }

    @PostMapping("/reviews")
    public String postReview(@RequestParam("comment") String comment,
                             @RequestParam("rating") int rating, Authentication authentication) {
        reviewRepository.add(
                new Review(
                    userRepository.get(authentication.getName()).getId(),
                    comment,
                    rating
                )
        );

        return "redirect:/reviews";
    }

    @GetMapping("/admin/users")
    public String userList(Model model) {
        List<User> users = userRepository.findAllUsers();
        model.addAttribute("users", users);
        return "admin-users";
    }

    @GetMapping("/admin/leaderboard")
    public String leaderboard(Model model) {
        List<User> usersSortedByScore = userRepository.findAllUsersOrderByScoreDesc();
        model.addAttribute("users", usersSortedByScore);
        return "admin-leaderboard";
    }


}
