package RiotAPI.Riot.controllers;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuerySummonerDTO {


    private String puuid;
    private String name;
    private long summonerLevel;



    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
}
