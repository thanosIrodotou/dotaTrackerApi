package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "app")
@EnableAutoConfiguration(exclude = {ElasticConfiguration.class})
public class DotaTrackerApp {

    @Autowired
    private GamesMapper gamesMapper;

    public static void main(String[] args) {
        SpringApplication.run(DotaTrackerApp.class, args);
    }
}
