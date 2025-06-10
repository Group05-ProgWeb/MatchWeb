package it.unitn.progweb.team05.matchweb.models;

public class SingleBet {

    private String matchId;
    private int prediction;

    public SingleBet() {}

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public int getPrediction() {
        return prediction;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }

    @Override
    public String toString() {
        return "SingleBet{" +
                "matchId='" + matchId + '\'' +
                ", prediction=" + prediction +
                '}';
    }
}
