package app.mapping.jsonentities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Collection;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DotaHeroes {

    private final Collection<DotaHeroe> dotaHero;

    @JsonCreator
    public DotaHeroes(
            @JsonProperty("heroes") Collection<DotaHeroe> dotaHero) {
        this.dotaHero = dotaHero;
    }

    public Collection<DotaHeroe> getDotaHero() {
        return dotaHero;
    }

    @Override
    public String toString() {
        return "DotaHeroes{" +
                "dotaHero=" + dotaHero +
                '}';
    }
}
