package kz.example.sbkcm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class ExampleController {
    @Value("${message}")
    private String message;

    @GetMapping
    public String getMessage(){
        return message;
    }
}
