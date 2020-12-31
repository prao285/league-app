import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

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

        Champion yasuo = Orianna.championNamed("Yasuo").get();
        System.out.println("Finding stats for latest game...");
        Iterator<Match> test = summoner.matchHistory().get().stream().iterator();



        userInput.close();

    }


}