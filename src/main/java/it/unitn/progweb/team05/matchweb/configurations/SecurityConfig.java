package it.unitn.progweb.team05.matchweb.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
@ComponentScan("it.unitn.progweb.team05.matchweb")
public class SecurityConfig {

    // User details manager
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // Password encoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security chain
    @Bean
    public SecurityFilterChain configure(HttpSecurity http)
            throws Exception {

        // Authentication
        http.formLogin(c ->
                c.loginPage("/login")
                 .defaultSuccessUrl("/")
                 .failureForwardUrl("/loginFail")
        );

        // Authorization
        http.authorizeHttpRequests(c ->
                c.requestMatchers("/dashboard").hasAnyRole("USER", "MODERATOR")
                 .requestMatchers("/adminDashboard").hasRole("ADMIN")
                 .requestMatchers("/profile").hasAnyRole("USER", "MODERATOR")
                 .requestMatchers("/gameCalendar").hasAnyRole("USER", "MODERATOR")
                 .requestMatchers("/changePassword").hasAnyRole("USER", "MODERATOR")
                 .requestMatchers("/play").hasAnyRole("USER", "MODERATOR")
                 .requestMatchers("/reviews").hasAnyRole("USER", "MODERATOR")
                 .requestMatchers("/admin/users").hasAnyRole("ADMIN", "MODERATOR")
                 .requestMatchers("/admin/leaderboard").hasAnyRole("ADMIN", "MODERATOR")
                 .requestMatchers("/admin/prizes").hasAnyRole("ADMIN", "MODERATOR")
                 .requestMatchers("/admin/upgrade").hasAnyRole("ADMIN", "MODERATOR")
                 .anyRequest().permitAll()
        );

        // Logout
        http.logout(c ->
                c.logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );

        // TO DISABLE CSRF PROTECTION
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
