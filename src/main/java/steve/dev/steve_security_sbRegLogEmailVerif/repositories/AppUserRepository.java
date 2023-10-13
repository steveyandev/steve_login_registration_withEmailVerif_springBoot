package steve.dev.steve_security_sbRegLogEmailVerif.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import steve.dev.steve_security_sbRegLogEmailVerif.appUser.AppUser;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository {
    Optional<AppUser> findByEmail(String email);
}
