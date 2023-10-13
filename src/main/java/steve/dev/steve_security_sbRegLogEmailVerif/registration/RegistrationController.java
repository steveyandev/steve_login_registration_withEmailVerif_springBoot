package steve.dev.steve_security_sbRegLogEmailVerif.registration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import steve.dev.steve_security_sbRegLogEmailVerif.services.RegistrationService;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
