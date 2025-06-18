package it.unitn.progweb.team05.matchweb.schemas;

public class BetSlipResult {

    private int score;

    public BetSlipResult() {}

    public BetSlipResult(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
