package it.unitn.progweb.team05.matchweb.controllers;

import it.unitn.progweb.team05.matchweb.repositories.NewsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsRestController {

    private final NewsRepository newsRepository;

    public NewsRestController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/random")
    public Map<String, String> getRandomNews() {
        String news = newsRepository.getRandomNewsText().orElse("Nessuna news disponibile.");
        return Map.of("text", news);
    }
}

