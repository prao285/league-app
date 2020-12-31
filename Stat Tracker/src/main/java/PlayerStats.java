import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.lang.reflect.Array;
import java.util.Iterator;

public class PlayerStats {
    static int championMastery(Summoner summoner, String champion, String apiKey) {
        Champion champ = Champion.named(champion).get();
        return(summoner.getChampionMastery(champ).getPoints());
    }

    static ParticipantStats [] lastGameStats(Summoner summoner) {
        ParticipantStats [] lastTwoGames = new ParticipantStats[2];
        Iterator<Match> matches = summoner.matchHistory().get().stream().iterator();

        int i = 0;
        Match match = matches.next();
        while(i < 2) {
            if(match.getMap() != null && match.getMode().compareTo(GameMode.CLASSIC) == 0) {
                lastTwoGames[i] = match.getParticipants().find(summoner).getStats();
                i ++;
            }
            match = matches.next();

        }

        return lastTwoGames;
    }
}
