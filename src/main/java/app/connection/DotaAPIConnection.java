package app.connection;

import app.GamesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class DotaAPIConnection {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService executorService;

    @Autowired
    private WebTarget webTarget;
    @Autowired
    private GamesMapper gamesMapper;

    @PostConstruct
    public void init() {
        logger.info("INITIALISING DOTA API CONNECTION");

        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            String lastMatchId = "";
            try {
                do {
                    final String lastMatches = webTarget
                            .queryParam("start_at_match_id", lastMatchId)
                            .request(MediaType.APPLICATION_JSON)
                            .get(String.class);
                    gamesMapper.parse(lastMatches);
                    lastMatchId = String.valueOf(Long.parseLong(gamesMapper.getLastMatchId()) -1);
                    logger.info("NEXT MATCH ID: {}", lastMatchId);
                } while (true);

            } catch (IOException e) {
                logger.warn("IOException while parsing stream. {}", e);
            }
        });
    }

    @PreDestroy
    public void destroy() {
        logger.info("SHUTTING DOWN");
    }

}
