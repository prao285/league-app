import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.util.Scanner;

public class StatTracker {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("What is your in game username?");
        String name = userInput.nextLine();
        System.out.println("What champion would you like to find your mastery for?");
        String champ = userInput.nextLine();
        System.out.println(name + " mastery with " + champ + " is: " + masteryFinder(name, champ));
        userInput.close();

    }

    private static int masteryFinder(String name, String champion) {
        Orianna.setRiotAPIKey("RGAPI-fdf7d536-4562-492a-9b7f-15b80d9b9ffb");
        Orianna.setDefaultRegion(Region.NORTH_AMERICA);

        Summoner summoner = Orianna.summonerNamed(name).get();

        Champion champ = Champion.named(champion).get();

        return(summoner.getChampionMastery(champ).getPoints());

    }
}