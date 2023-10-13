package steve.dev.steve_security_sbRegLogEmailVerif.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import steve.dev.steve_security_sbRegLogEmailVerif.repositories.AppUserRepository;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static  String USER_NOT_FOUND_MESSAGE="user with email %s not found";
    private final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE,email)));
    }
}
