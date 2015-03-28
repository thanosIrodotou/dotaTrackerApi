package app.repository;

import app.mapping.jsonentities.Match;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface MatchesRepository extends ElasticsearchRepository<Match, Long> {

    public Match findByMatchId(long matchId);

}
