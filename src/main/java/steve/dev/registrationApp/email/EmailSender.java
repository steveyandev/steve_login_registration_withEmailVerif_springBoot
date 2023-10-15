package steve.dev.registrationApp.email;

import org.springframework.stereotype.Service;


public interface EmailSender {

    void send(String to,String email);
}
