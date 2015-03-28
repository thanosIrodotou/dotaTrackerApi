package app.controller;

import app.mapping.jsonentities.Match;
import app.repository.MatchesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class MatchesController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MatchesRepository matchesRepository;
    private String lastMatchId;

    //    @PostConstruct
    public void deleteAllMatches() {
        System.out.println("DELETING ALL MATCHES");
        matchesRepository.deleteAll();
    }

    public void saveMatch(Match match) {
        System.out.println("SAVING MATCH");
        matchesRepository.save(match);
    }

    public void saveMatches(Collection<Match> matches) {
        logger.info("SAVING {} MATCHES", matches.size());
        for (Iterator iterator = matches.iterator(); iterator.hasNext(); ) {
            Match match = (Match) iterator.next();
            matchesRepository.save(match);
            setLastMatchId(String.valueOf(match.getMatchId()));
        }
    }

    public Match findMatch(long matchId) {
        logger.info("FINDING MATCH");
        return matchesRepository.findOne(matchId);
    }

    public Iterable<Match> findAllMatches() {
        logger.info("FINDING ALL MATCHES");
        return matchesRepository.findAll();
    }

    public String getLastMatchId() {
        return lastMatchId;
    }

    private void setLastMatchId(String lastMatchId) {
        this.lastMatchId = lastMatchId;
    }
}
