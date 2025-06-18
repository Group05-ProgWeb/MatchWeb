package it.unitn.progweb.team05.matchweb.services;

import it.unitn.progweb.team05.matchweb.repositories.UserRepository;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class Signup {

    private final UserDetailsManager userDetailsManager;

    public Signup(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public boolean userExists(String username) {
        return userDetailsManager.userExists(username);
    }
}
