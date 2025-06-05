package it.unitn.progweb.team05.matchweb.repositories;

import it.unitn.progweb.team05.matchweb.SecurityUser;
import it.unitn.progweb.team05.matchweb.User;
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
        rowObject.setEmail(r.getString("EMAIL"));
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
        String sql = "SELECT * FROM USER_DETAILS";
        return jdbc.query(sql, userRowMapper);
    }

    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsManager.createUser(new SecurityUser(user));
        String sql = "INSERT INTO USER_DETAILS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
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

    public User getByUsername(String username){
        String sql = "SELECT * FROM USER_DETAILS WHERE USERNAME = ?";
        try {
            return jdbc.queryForObject(sql, userRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}


