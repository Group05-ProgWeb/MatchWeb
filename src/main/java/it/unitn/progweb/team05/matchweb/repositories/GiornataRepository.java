package it.unitn.progweb.team05.matchweb.repositories;

import it.unitn.progweb.team05.matchweb.schemas.Giornata;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GiornataRepository {

    private final JdbcTemplate jdbc;

    private RowMapper<Giornata> giornataRowMapper = (r, i) -> {
        Giornata rowObject = new Giornata();
        rowObject.setMatchday(r.getInt("MATCHDAY"));
        rowObject.setUsername(r.getString("USERNAME"));
        return rowObject;
    };

    public GiornataRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void add(Giornata giornata) {
        String sql = "INSERT INTO GIORNATE VALUES (?, ?)";
        jdbc.update(sql, giornata.getUsername(), giornata.getMatchday());
    }

    public boolean exists(String username, int matchday) {
        String sql = "SELECT * FROM GIORNATE WHERE USERNAME = ? AND matchday = ?";
        return !jdbc.query(sql, giornataRowMapper, username, matchday).isEmpty();

    }
}
