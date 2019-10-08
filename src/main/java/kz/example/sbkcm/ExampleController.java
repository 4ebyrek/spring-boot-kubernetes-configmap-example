package kz.example.sbkcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class ExampleController {
    @Autowired
    Environment env;

    @GetMapping
    public String getMessage(){
        return env.getProperty("example.message");
    }
}
