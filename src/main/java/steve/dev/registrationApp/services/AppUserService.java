package steve.dev.registrationApp.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import steve.dev.registrationApp.appUser.AppUser;
import steve.dev.registrationApp.registration.token.ConfirmationToken;
import steve.dev.registrationApp.registration.token.ConfirmationTokenService;
import steve.dev.registrationApp.repositories.AppUserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static  String USER_NOT_FOUND_MESSAGE="user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MESSAGE,email)));
    }
    public String signUpUser(AppUser appUser){
       boolean userExists= appUserRepository.findByEmail(appUser.getEmail())
        .isPresent();
       if(userExists){
           throw  new IllegalStateException("email already exist(taken)");
       }
       String encodedPassword=bCryptPasswordEncoder.encode(
               appUser.getPassword()
       );
       appUser.setPassword(encodedPassword);
       appUserRepository.save(appUser);

        String  token=UUID.randomUUID().toString();
       //confirmation token
        ConfirmationToken confirmationToken=new ConfirmationToken(
             token,
             LocalDateTime.now()  ,
             LocalDateTime.now().plusMinutes(15)  ,
                appUser

        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //send email to user with token to confirmation

        return token;
    }
}
