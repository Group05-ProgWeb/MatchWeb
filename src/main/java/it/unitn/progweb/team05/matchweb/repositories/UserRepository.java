package it.unitn.progweb.team05.matchweb.repositories;

import it.unitn.progweb.team05.matchweb.models.SecurityUser;
import it.unitn.progweb.team05.matchweb.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    private RowMapper<User> userRowMapper = (r, i) -> {
        User rowObject = new User();
        rowObject.setId(r.getInt("ID"));
        rowObject.setUsername(r.getString("USERNAME"));
        rowObject.setFirstName(r.getString("FIRST_NAME"));
        rowObject.setLastName(r.getString("LAST_NAME"));
        rowObject.setDateOfBirth(r.getDate("DATE_OF_BIRTH"));
        rowObject.setEmail(r.getString("EMAIL"));
        rowObject.setSport(r.getString("SPORT"));
        rowObject.setFavoriteTeam(r.getString("FAVORITE_TEAM"));
        rowObject.setTotalScore(r.getInt("TOTAL_SCORE"));
        return rowObject;
    };

    public UserRepository(JdbcTemplate jdbc,
                          UserDetailsManager userDetailsManager,
                          PasswordEncoder passwordEncoder) {
        this.jdbc = jdbc;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExists(String username) {
        return userDetailsManager.userExists(username);
    }

    public List<User> findAllUsers() {
        String sql = "SELECT * FROM USER_DETAILS ORDER BY FIRST_NAME ASC";
        return jdbc.query(sql, userRowMapper);
    }

    public List<User> findAllUsersOrderByScoreDesc() {
        String sql = "SELECT * FROM USER_DETAILS ORDER BY TOTAL_SCORE DESC";
        return jdbc.query(sql, userRowMapper);
    }

    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsManager.createUser(new SecurityUser(user));
        String sql = "INSERT INTO USER_DETAILS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
        jdbc.update(sql,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getEmail(),
                user.getSport(),
                user.getFavoriteTeam()
        );
    }

    public User get(String username){
        String sql = "SELECT * FROM USER_DETAILS WHERE USERNAME = ?";
        try {
            return jdbc.queryForObject(sql, userRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateScoreByUsername(String username, long total_score) {
        String sql = "UPDATE USER_DETAILS SET TOTAL_SCORE = ? WHERE USERNAME = ?";
        jdbc.update(sql, total_score, username);
    }

    public boolean isAdmin(String username) {
        String sql = "SELECT COUNT(*) FROM AUTHORITIES WHERE USERNAME = ? AND AUTHORITY = 'ROLE_ADMIN'";
        Integer count = jdbc.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    public List<User> findAllNonAdminModerators() {
        String sql = """
        SELECT u.* FROM user_details u
        JOIN authorities a ON u.username = a.username
        WHERE a.AUTHORITY NOT IN ('ROLE_ADMIN', 'ROLE_MODERATOR')
        """;

        return jdbc.query(sql, userRowMapper);
    }

    public void updateRoleToModerator(String username) {
        String sql = "UPDATE authorities SET AUTHORITY = 'ROLE_MODERATOR' WHERE username = ?";
        jdbc.update(sql, username);
    }
}


