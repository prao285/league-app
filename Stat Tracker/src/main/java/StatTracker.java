import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.GameType;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class StatTracker {
    public static void main(String[] args) {
        Orianna.setRiotAPIKey(args[0]);
        Orianna.setDefaultRegion(Region.NORTH_AMERICA);

        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your in game username?");
        String name = userInput.nextLine();

        Summoner summoner = Orianna.summonerNamed(name).get();
        if (!summoner.exists()) {
            System.out.println("Summoner with this name doesn't exist!");
            System.exit(1);
        }


        System.out.println("Finding stats for latest game...");

        ParticipantStats[] playerStats = PlayerStats.lastGameStats(summoner);
        System.out.println(playerStats[0]);





        userInput.close();

    }


}