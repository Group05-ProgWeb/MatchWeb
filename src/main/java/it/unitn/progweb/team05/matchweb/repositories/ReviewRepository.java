package it.unitn.progweb.team05.matchweb.repositories;

import it.unitn.progweb.team05.matchweb.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//
//    public void store(Review comment){
//        String sql ="INSERT INTO comments VALUES (DEFAULT, ?, ?, ?)";
//        jdbcTemplate.update(sql, comment.getAuthorId(), comment.getText(), comment.getScore());
//    }
//
//    public List<UserDetails> findAll() {
//        String sql = "SELECT * from comments";
//        return jdbcTemplate.query(sql, new UserRowMapper());
//    }
}
