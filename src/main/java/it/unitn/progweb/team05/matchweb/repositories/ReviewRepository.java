package it.unitn.progweb.team05.matchweb.repositories;

import it.unitn.progweb.team05.matchweb.models.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {
    private final JdbcTemplate jdbc;

    public ReviewRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Review> getAll() {
        String sql = "SELECT * FROM REVIEWS";
        RowMapper<Review> reviewRowMapper = (r, i) -> {
            return new Review(
                    Integer.parseInt(r.getString("AUTHOR_ID")),
                    r.getString("TEXT"),
                    Integer.parseInt(r.getString("SCORE"))
            );
        };
        return jdbc.query(sql, reviewRowMapper);
    }

    public void add(Review review) {
        String sql = "INSERT INTO REVIEWS VALUES (DEFAULT, ?, ?, ?)";
        jdbc.update(sql,
                review.getAuthorId(),
                review.getText(),
                review.getScore()
        );
    }
}