package it.unitn.progweb.team05.matchweb.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NewsRepository {
    private final JdbcTemplate jdbcTemplate;

    public NewsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<String> getRandomNewsText() {
        String sql = "SELECT text FROM news ORDER BY RANDOM() LIMIT 1";
        try {
            String news = jdbcTemplate.queryForObject(sql, String.class);
            return Optional.ofNullable(news);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
