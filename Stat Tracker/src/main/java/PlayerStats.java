import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class PlayerStats {
    static int championMastery(Summoner summoner, String champion, String apiKey) {
        Champion champ = Champion.named(champion).get();
        return(summoner.getChampionMastery(champ).getPoints());
    }

    static Tier rank(Summoner summoner, Season season) {
        return summoner.getHighestTier(season);
    }

    static int level(Summoner summoner) {
        return summoner.getLevel();

    }

    static boolean isInGame(Summoner summoner) {
        return summoner.isInGame();
    }

    static void lastGame(Summoner summoner) {
    }
}
