package it.unitn.progweb.team05.matchweb.models;

import java.util.List;

public class BetSlip {

    private int matchday;
    private List<SingleBet> bets;

    public BetSlip() {}

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public List<SingleBet> getBets() {
        return bets;
    }

    public void setBets(List<SingleBet> bets) {
        this.bets = bets;
    }

    @Override
    public String toString() {
        return "BetSlip{" +
                "matchday=" + matchday +
                ", bets=" + bets +
                '}';
    }
}
