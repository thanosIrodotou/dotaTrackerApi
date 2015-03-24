package app;

import app.jsonentities.Match;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MatchesRepository extends ElasticsearchRepository<Match, Long> {

    public Match findByMatchId(long matchId);

}
