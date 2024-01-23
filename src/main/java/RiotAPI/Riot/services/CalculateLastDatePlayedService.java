package RiotAPI.Riot.services;

import java.time.Instant;
import java.time.InstantSource;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CalculateLastDatePlayedService {

    public LocalDateTime calculateLastDatePlayed(long unix){
        Instant instant = Instant.ofEpochMilli(unix);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime;
    }
}
