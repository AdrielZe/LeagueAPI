package RiotAPI.Riot.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RestController;

public class SummonerTotalMasteryDTO {
    private int totalMastery;

    public SummonerTotalMasteryDTO(int totalMastery) {
        this.totalMastery = totalMastery;
    }

    public Number getTotalMastery() {
        return totalMastery;
    }

    public void setTotalMastery(int totalMastery) {
        this.totalMastery = totalMastery;
    }
}
