package it.unitn.progweb.team05.matchweb.repositories;

import it.unitn.progweb.team05.matchweb.schemas.PrizeType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrizeTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    public PrizeTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PrizeType> rowMapper = (rs, rowNum) -> {
        PrizeType pt = new PrizeType();
        pt.setId(rs.getLong("id"));
        pt.setName(rs.getString("name"));
        return pt;
    };

    public List<PrizeType> findAll() {
        return jdbcTemplate.query("SELECT * FROM prize_types", rowMapper);
    }
}
