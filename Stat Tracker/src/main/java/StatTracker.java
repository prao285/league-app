import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class StatTracker {
    public static void main(String[] args) throws IOException, InterruptedException {
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

        Iterator<Match> allMatches = summoner.matchHistory().get().stream().iterator();






        System.out.println("Finding stats for latest game...");

        Match [] recentMatches = PlayerStats.getLastNMatchesOfSpecificType(500, Queue.withId(400), allMatches);

        JDBCConn.resetTable(args[1], args[2], args[3]);
        PlayerMatch[] matchScores = new PlayerMatch[recentMatches.length];

        for (int i = 0; i < recentMatches.length; i ++) {

            ParticipantStats stats = PlayerStats.statsForMatch(recentMatches[i], summoner);
            System.out.println(i);
            matchScores[i] = new PlayerMatch(PlayerStats.findScoreForSingleMatch(stats), stats.isWinner());
        }

        JDBCConn.insertData(args[1], args[2], args[3], matchScores);




        userInput.close();
    }
}