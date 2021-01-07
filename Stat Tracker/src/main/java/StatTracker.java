import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.util.Scanner;

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

        Match [] recentMatches = PlayerStats.getLastNMatchesOfSpecificType(summoner, 2, GameMode.CLASSIC);

        double[] score = PlayerStats.compareTwoMatches(recentMatches[0], recentMatches[1], summoner);


        System.out.println(score[0] - score[1]);



        userInput.close();

    }


}