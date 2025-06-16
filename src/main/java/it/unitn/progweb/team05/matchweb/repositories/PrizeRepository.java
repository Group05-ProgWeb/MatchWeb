package it.unitn.progweb.team05.matchweb.repositories;

import it.unitn.progweb.team05.matchweb.models.Prize;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Repository
public class PrizeRepository {

    private final JdbcTemplate jdbcTemplate;

    public PrizeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Prize> rowMapper = (rs, rowNum) -> {
        Prize prize = new Prize();
        prize.setId(rs.getLong("id"));
        prize.setUserId(rs.getInt("user_id"));
        prize.setPrizeTypeId(rs.getLong("prize_type_id"));
        prize.setAwardedAt(rs.getTimestamp("awarded_at"));
        return prize;
    };

    public Prize save(Prize prize) {
        final String sql = "INSERT INTO prizes (user_id, prize_type_id, awarded_at) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, prize.getUserId());
            ps.setLong(2, prize.getPrizeTypeId());
            ps.setTimestamp(3, prize.getAwardedAt() != null ? prize.getAwardedAt() : new Timestamp(System.currentTimeMillis()));
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null && keys.containsKey("ID")) {
            prize.setId(((Number) keys.get("ID")).longValue());
        } else {
            prize.setId(keyHolder.getKey().longValue());
        }
        return prize;
    }

    public List<Prize> findByUserId(int userId) {
        String sql = """
            SELECT p.id, p.user_id, p.prize_type_id, p.awarded_at,
                   pt.name AS prize_type_name
              FROM prizes p
              JOIN prize_types pt ON p.prize_type_id = pt.id
             WHERE p.user_id = ?
             ORDER BY p.awarded_at DESC
            """;

        return jdbcTemplate.query(sql, rowMapper, userId);
    }

    public List<Prize> findAll() {
        return jdbcTemplate.query("SELECT * FROM prizes", rowMapper);
    }
}
