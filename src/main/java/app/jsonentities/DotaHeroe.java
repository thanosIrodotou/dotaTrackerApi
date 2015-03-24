package app.jsonentities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DotaHeroe {

    private final String heroName;
    private final int heroId;
    private final String localizedName;

    @JsonCreator
    public DotaHeroe(
            @JsonProperty("name") String heroName,
            @JsonProperty("id") int heroId,
            @JsonProperty("localized_name") String localizedName) {
        this.heroName = heroName;
        this.heroId = heroId;
        this.localizedName = localizedName;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getHeroId() {
        return heroId;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    @Override
    public String toString() {
        return "DotaHeroes{" +
                "heroName='" + heroName + '\'' +
                ", heroId=" + heroId +
                ", localizedName='" + localizedName + '\'' +
                '}';
    }
}
