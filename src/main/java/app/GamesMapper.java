package app;

import app.jsonentities.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@Component
public class GamesMapper {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MatchesController matchesController;

    @PostConstruct
    private Map<Integer, String> createHeroIdToNameMap() throws IOException {
        final String STATUS_MESSAGE_URL = Resources.toString(Resources.getResource(
                "dotaHeroes.json"), Charsets.UTF_8);

        DotaHeroes dotaHeroes = mapper.readValue(STATUS_MESSAGE_URL, DotaHeroes.class);

        Map<Integer, String> heroesMap = Maps.newHashMap();

        for (DotaHeroe hero : dotaHeroes.getDotaHero()) {
            heroesMap.put(hero.getHeroId(), hero.getHeroName());
        }

        return heroesMap;
    }

    public final void parse(String lastMatches) throws IOException {
        try (JsonParser jsonParser = getJsonParserForStream(lastMatches)) {
            parseMatches(jsonParser);
        } catch (IOException e) {
            System.out.println("parseMatches Exception = " + e);
        }
    }

    private JsonParser getJsonParserForStream(String lastMatches)
            throws IOException {
        return mapper.getFactory().createParser(lastMatches);
    }

    private void parseMatches(JsonParser jsonParser) throws IOException {
        final DotaGame dotaGame = mapper.readValue(jsonParser, DotaGame.class);
        setHeroName(dotaGame.getResult().getMatches());
        matchesController.saveMatches(dotaGame.getResult().getMatches());
    }

    private void setHeroName(Collection<Match> matches) throws IOException {
        Map<Integer, String> heroeIdToName = createHeroIdToNameMap();
        for (Match match : matches) {
            for (Players player : match.getPlayers()) {
                player.setHeroName(heroeIdToName.get(player.getHeroId()));
            }
        }
    }

    public String getLastMatchId() {
        return matchesController.getLastMatchId();
    }
}
