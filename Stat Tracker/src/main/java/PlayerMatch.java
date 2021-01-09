public class PlayerMatch {
    double score;
    boolean won;

    public PlayerMatch(double score, boolean won) {
        this.score = score;
        this.won = won;
    }

    public double getScore() {
        return score;
    }

    public boolean isWon() {
        return won;
    }
}
