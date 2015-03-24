package app.jsonentities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Collection;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Result {

    private final int status;
    private final int numberOfResults;
    private final int totalResults;
    private final int resultsRemaining;
    private final Collection<Match> matches;

    @JsonCreator
    public Result(
            @JsonProperty("status") int status,
            @JsonProperty("num_results") int numberOfResults,
            @JsonProperty("total_results") int totalResults,
            @JsonProperty("results_remaining") int resultsRemaining,
            @JsonProperty("matches") Collection<Match> matches) {
        this.status = status;
        this.numberOfResults = numberOfResults;
        this.totalResults = totalResults;
        this.resultsRemaining = resultsRemaining;
        this.matches = matches;
    }

    public int getStatus() {
        return status;
    }

    public int getNumberOfResults() {
        return numberOfResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getResultsRemaining() {
        return resultsRemaining;
    }

    public Collection<Match> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", numberOfResults=" + numberOfResults +
                ", totalResults=" + totalResults +
                ", resultsRemaining=" + resultsRemaining +
                ", matches=" + matches +
                '}';
    }
}
