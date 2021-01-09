import com.merakianalytics.orianna.types.common.GameMode;
import com.merakianalytics.orianna.types.common.Map;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.util.Iterator;

public class PlayerStats {

    static final double KDA_MAX_SCORE = 10 - (10.0 / (1.0 + Math.pow(Math.E, 2.495 / 1.8)));
    static final double VISION_MAX_SCORE = 5 - (5.0 / (1.0 + Math.pow(Math.E, 25.0 / 10.0)));
    static final double DAMAGE_MAX_SCORE = 10 - (10.0 / (1.0 + Math.pow(Math.E, 20.0 / 5.0)));

    static int championMastery(Summoner summoner, String champion, String apiKey) {
        Champion champ = Champion.named(champion).get();
        return(summoner.getChampionMastery(champ).getPoints());
    }

    static Match [] getLastNMatchesOfSpecificType(int n, GameMode type, Iterator<Match>allMatches) {
        Match [] matches = new Match[n];

        int i = 0;
        Match match = allMatches.next();
        while (i < n) {
            if (match.getMap() != null && match.getMode().compareTo(type) == 0) {
                matches[i] = match;
                i++;
            }
            match = allMatches.next();
        }
        return matches;
    }

    static ParticipantStats statsForMatch(Match match, Summoner summoner) {return match.getParticipants().find(summoner).getStats();}

    static long timeOfGame (Match match) {return match.getDuration().getStandardMinutes(); }

    static double kdaScoreCalc(ParticipantStats stats) {
        double kills = stats.getKills();
        double assists = stats.getAssists();
        double deaths = stats.getDeaths();

        if(deaths == 0) {
            deaths = 1;
        }

        double kda = (kills + (assists / 2)) / deaths;

        double score = 10.0 / (1.0 + Math.pow(Math.E, ((-1.0 * kda) + 2.495) / 1.8));
        score = score - (10.0 / (1.0 + Math.pow(Math.E, 2.495 / 1.8)));
        return score;
    }

    static double visionScoreCalc(ParticipantStats stats) {
        double vision = stats.getWardsPlaced() + stats.getWardsKilled();
        double score = 5.0 / (1.0 + Math.pow(Math.E, ((-1.0 * vision) + 25.0) / 10.0));
        score = score - (5.0 / (1.0 + Math.pow(Math.E, 25.0 / 10.0)));
        return score;
    }

    static double damageScoreCalc(ParticipantStats stats) {
        double damageToChampions = stats.getDamageDealtToChampions();
        // need to implement objectives
        double damageToObjectives = stats.getDamageToObjectives();
        double totalDamage = damageToChampions / 1000;

        double score = 10.0 / (1.0 + Math.pow(Math.E, ((-1.0 * totalDamage) + 20) / 5));

        score = score - (10.0 / (1.0 + Math.pow(Math.E, 20.0 / 5.0)));
        return score;
    }

    static double findScoreForSingleMatch(Match match, Summoner summoner) {
        double kdaScore = kdaScoreCalc(statsForMatch(match, summoner));
        double visionScore = visionScoreCalc(statsForMatch(match, summoner));
        double damageScore = damageScoreCalc(statsForMatch(match, summoner));
        return (kdaScore + visionScore + damageScore) / (KDA_MAX_SCORE + VISION_MAX_SCORE + DAMAGE_MAX_SCORE);
    }

    static double [] compareTwoMatches(Match matchOne, Match matchTwo, Summoner summoner) {
        double [] scores = new double[2];
        double scoreMatchOne = findScoreForSingleMatch(matchOne, summoner);
        double scoreMatchTwo = findScoreForSingleMatch(matchTwo, summoner);

        scores[0] = scoreMatchOne;
        scores[1] = scoreMatchTwo;
        return scores;
    }


}
