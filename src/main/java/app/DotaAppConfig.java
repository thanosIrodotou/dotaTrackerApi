package app;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Configuration
@ComponentScan
public class DotaAppConfig {
    private static final String API_URI = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/";
    private static final String API_KEY = "80D0C2CA14E87814DB6A3D5A6368DDBB";

    @Bean
    public WebTarget getWebTarget() {
        ClientConfig clientConfig = new ClientConfig();

        return ClientBuilder
                .newClient(clientConfig)
                .register(new LoggingFilter())
                .target(API_URI)
                .queryParam("key", API_KEY)
                .queryParam("start_at_match_id", "");
    }
}
