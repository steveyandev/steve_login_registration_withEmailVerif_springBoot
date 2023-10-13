package steve.dev.registrationApp.services;

import org.springframework.stereotype.Service;
import steve.dev.registrationApp.registration.RegistrationRequest;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "works";
    }
}
