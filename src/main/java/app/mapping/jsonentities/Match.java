package app.mapping.jsonentities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Collection;

@Document(indexName = "dota", type = "matches", shards = 1, replicas = 0, refreshInterval = "-1")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Match {

    @Id
    private final Long matchId;
    private final Long matchSequenceNumber;
    @Field(type= FieldType.Date)
    private final Long startTime;
    @Field(type = FieldType.Long, store = false)
    private final int lobbyType;
    @Field(type = FieldType.Long, store = false)
    private final int radiantTeamId;
    @Field(type = FieldType.Long, store = false)
    private final int direTeamId;
    @Field(type = FieldType.Object, index = FieldIndex.not_analyzed, store = true)
    private final Collection<Players> players;

    @JsonCreator
    public Match(
            @JsonProperty("match_id") Long matchId,
            @JsonProperty("match_seq_num") Long matchSequenceNumber,
            @JsonProperty("start_time") Long startTime,
            @JsonProperty("lobby_type") int lobbyType,
            @JsonProperty("radiant_team_id") int radiantTeamId,
            @JsonProperty("dire_team_id") int direTeamId,
            @JsonProperty("players") Collection<Players> players) {
        this.matchId = matchId;
        this.matchSequenceNumber = matchSequenceNumber;
        this.startTime = startTime;
        this.lobbyType = lobbyType;
        this.radiantTeamId = radiantTeamId;
        this.direTeamId = direTeamId;
        this.players = players;
    }

    public Long getMatchId() {
        return matchId;
    }

    public Long getMatchSequenceNumber() {
        return matchSequenceNumber;
    }

    public Long getStartTime() {
        return startTime;
    }

    public int getLobbyType() {
        return lobbyType;
    }

    public int getRadiantTeamId() {
        return radiantTeamId;
    }

    public int getDireTeamId() {
        return direTeamId;
    }

    public Collection<Players> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", matchSequenceNumber=" + matchSequenceNumber +
                ", startTime=" + startTime +
                ", lobbyType=" + lobbyType +
                ", radiantTeamId=" + radiantTeamId +
                ", direTeamId=" + direTeamId +
                ", players=" + players +
                '}' + "\n";
    }
}
