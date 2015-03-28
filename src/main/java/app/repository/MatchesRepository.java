package app.repository;

import app.mapping.jsonentities.Match;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MatchesRepository extends ElasticsearchRepository<Match, Long> {

    public Match findByMatchId(long matchId);

}
