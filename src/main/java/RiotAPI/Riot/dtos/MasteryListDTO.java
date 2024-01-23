package RiotAPI.Riot.dtos;


import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class MasteryListDTO {
    private long championId;

    private int masteryLevel;

    private String championName;

    public LocalDateTime getLastDatePlayed() {
        return lastDatePlayed;
    }

    public void setLastDatePlayed(LocalDateTime lastDatePlayed) {
        this.lastDatePlayed = lastDatePlayed;
    }

    private LocalDateTime lastDatePlayed;

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public int getMasteryLevel() {
        return masteryLevel;
    }

    public void setMasteryLevel(int masteryLevel) {
        this.masteryLevel = masteryLevel;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }


}
