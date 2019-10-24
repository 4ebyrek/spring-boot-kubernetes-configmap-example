package kz.example.sbkcm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//import static net.logstash.logback.argument.StructuredArguments.v;

@RestController
@Slf4j
public class ExampleController {
    @Autowired
    Environment env;

    @Autowired
    DataSource dataSource;

    @Value("${example.message}")
    String message;

//    @Autowired
//    public Stopwatch stopwatch;

    @GetMapping
    public String getMessage() throws SQLException {
        Connection connection = dataSource.getConnection();
        String selectSql = "SELECT * FROM eldarbai WHERE id = ?";
        CallableStatement clb = connection.prepareCall(selectSql);
        clb.setString(1, "one");
        ResultSet rs = clb.executeQuery();
        rs.next();
        log.info("EEEEEEE {}, {}", env.getProperty("example.message"), rs.getString("name"));

        return null;
    }
}
