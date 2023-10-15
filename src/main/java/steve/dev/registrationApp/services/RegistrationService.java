package steve.dev.registrationApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import steve.dev.registrationApp.appUser.AppUser;
import steve.dev.registrationApp.enums.AppUserRole;
import steve.dev.registrationApp.registration.EmailValidator;
import steve.dev.registrationApp.registration.RegistrationRequest;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
     boolean isValidEmail= emailValidator.test(request.getEmail());

     if(!isValidEmail){
         throw  new IllegalStateException("email not valid");
     }

        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
