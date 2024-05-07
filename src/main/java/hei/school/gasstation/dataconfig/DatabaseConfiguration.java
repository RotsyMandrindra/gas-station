package hei.school.gasstation.dataconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@Component
public class DatabaseConfiguration {
    @Bean
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(
                System.getenv("dbUrl"), System.getenv("dbName"), System.getenv("dbPassword")
        );
        return connection;
    }
}
