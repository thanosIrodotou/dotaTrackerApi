package app.mapping.jsonentities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DotaGame {

    private final Result result;

    @JsonCreator
    public DotaGame(
            @JsonProperty("result") Result result) {
        this.result = result;
    }


    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "DotaGame{" +
                "result=" + result +
                '}';
    }
}