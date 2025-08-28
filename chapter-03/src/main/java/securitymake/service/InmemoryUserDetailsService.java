package securitymake.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class InmemoryUserDetailsService implements UserDetailsService {

    private final List<UserDetails> users;

    public InmemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst()
            .orElseThrow(() -> {
                log.error("Username not found {}", username);
                return new UsernameNotFoundException("username not found");
            });
    }
}
