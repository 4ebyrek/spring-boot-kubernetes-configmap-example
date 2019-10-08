package kz.example.sbkcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class ExampleController {
    @Autowired
    Environment env;

    @Autowired
    DataSource dataSource;

    @Value("${example.message}")
    String message;

    @GetMapping
    public String getMessage() throws SQLException {
        Connection connection = dataSource.getConnection();
        String selectSql = "SELECT * FROM eldarbai WHERE id = ?";
        CallableStatement clb = connection.prepareCall(selectSql);
        clb.setString(1, "one");
        ResultSet rs = clb.executeQuery();
        rs.next();
        System.out.println("name = "+ rs.getString("name"));
        return env.getProperty("example.message") + message;
    }
}
