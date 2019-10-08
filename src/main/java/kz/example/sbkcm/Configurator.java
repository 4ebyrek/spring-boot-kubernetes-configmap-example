package kz.example.sbkcm;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file:/configs/application.properties")
public class Configurator {
}
