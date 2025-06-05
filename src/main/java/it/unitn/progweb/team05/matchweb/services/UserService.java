package it.unitn.progweb.team05.matchweb.services;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDetailsManager userDetailsManager,
                       PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void changePassword(String oldPassword, String newPassword) {
        userDetailsManager.changePassword(oldPassword, passwordEncoder.encode(newPassword));
    }
}
