package app.jsonentities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Players {

    private final Long accountId;
    private final int playerSlot;
    private final int heroId;

    private String heroName;

    @JsonCreator
    public Players(
            @JsonProperty("account_id") Long accountId,
            @JsonProperty("player_slot") int playerSlot,
            @JsonProperty("hero_id") int heroId) {
        this.accountId = accountId;
        this.playerSlot = playerSlot;
        this.heroId = heroId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public int getPlayerSlot() {
        return playerSlot;
    }

    public int getHeroId() {
        return heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }


    @Override
    public String toString() {
        return "Players{" +
                "accountId=" + accountId +
                ", playerSlot=" + playerSlot +
                ", heroId=" + heroId +
                ", heroName='" + heroName + '\'' +
                '}';
    }
}
