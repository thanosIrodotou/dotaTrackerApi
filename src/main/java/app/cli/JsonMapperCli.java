package app.cli;

import app.jsonentities.DotaHeroe;
import app.jsonentities.DotaHeroes;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

public class JsonMapperCli {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final String STATUS_MESSAGE_URL = Resources.toString(Resources.getResource(
                "dotaHeroes.json"), Charsets.UTF_8);

        DotaHeroes dotaHeroes = mapper.readValue(STATUS_MESSAGE_URL, DotaHeroes.class);

        Map<Integer, String> heroesMap = Maps.newHashMap();

        for (DotaHeroe hero : dotaHeroes.getDotaHero()) {
            heroesMap.put(hero.getHeroId(), hero.getHeroName());
        }

        System.out.println("heroesMap = " + heroesMap);

    }
}
