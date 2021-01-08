import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;

public class StatTracker {
    public static void main(String[] args) {
        Orianna.setRiotAPIKey(args[0]);
        Orianna.setDefaultRegion(Region.NORTH_AMERICA);

        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your in game username?");
        String name = userInput.nextLine();

        Summoner summoner = Orianna.summonerNamed(name).get();
        Iterator<Match> allMatches = summoner.matchHistory().get().stream().iterator();

        if (!summoner.exists()) {
            System.out.println("Summoner with this name doesn't exist!");
            System.exit(1);
        }


        System.out.println("Finding stats for latest game...");

        Match [] recentMatches = PlayerStats.getLastNMatchesOfSpecificType(20, GameMode.CLASSIC, allMatches);



        JDBCConn.resetTable(args[1], args[2], args[3]);
        for (Match recentMatch : recentMatches) {
            JDBCConn.insertData(args[1], args[2], args[3], PlayerStats.findScoreForSingleMatch(recentMatch, summoner), PlayerStats.statsForMatch(recentMatch, summoner).isWinner());
        }

        userInput.close();



    }


}