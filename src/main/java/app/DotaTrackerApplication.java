package app;

import app.jsonentities.DotaGame;
import app.jsonentities.DotaHeroes;
import app.jsonentities.Match;
import app.jsonentities.Players;
import app.jsonentities.DotaHeroe;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "app")
@EnableAutoConfiguration(exclude = {ElasticConfiguration.class})
public class DotaTrackerApplication implements CommandLineRunner {

    @Autowired
    private MatchesRepository matchesRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void run(String... args) throws Exception {
        this.matchesRepository.deleteAll();
        parse();
    }

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

    public final void parse() throws IOException {
        final String toMap = Resources.toString(Resources.getResource(
                "dotaResponse.json"), Charsets.UTF_8);

        try (JsonParser jsonParser = getJsonParserForStream(IOUtils.toInputStream(toMap))) {
            parseMatches(jsonParser);
        } catch (IOException e) {
            System.out.println("parseMatches Exception = " + e);
        }
    }

    protected JsonParser getJsonParserForStream(InputStream stream)
            throws IOException {
        return mapper.getFactory().createParser(stream);
    }

    private void parseMatches(JsonParser jsonParser) throws IOException {
        final DotaGame dotaGame = mapper.readValue(jsonParser, DotaGame.class);
        setHeroName(dotaGame.getResult().getMatches());
        save(dotaGame.getResult().getMatches());
    }

    private void setHeroName(Collection<Match> matches) throws IOException {
        Map<Integer, String> heroeIdToName = createHeroIdToNameMap();
        for (Match match : matches) {
            for (Players player : match.getPlayers()) {
                player.setHeroName(heroeIdToName.get(player.getHeroId()));
            }
        }
    }

    public void save(Collection<Match> matches) {
        for (Iterator iterator = matches.iterator(); iterator.hasNext(); ) {
            Match match = (Match) iterator.next();
            this.matchesRepository.save(match);
        }
    }

    private void fetchAllGames() {
        System.out.println("Matches found with findAll():");
        System.out.println("-------------------------------");
        for (Match match : this.matchesRepository.findAll()) {
            System.out.println(match.toString());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SpringApplication.run(DotaTrackerApplication.class, args);
    }
}
