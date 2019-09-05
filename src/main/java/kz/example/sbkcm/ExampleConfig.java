package kz.example.sbkcm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file:/configs/application.properties",ignoreResourceNotFound = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExampleConfig {
    String data;
}
