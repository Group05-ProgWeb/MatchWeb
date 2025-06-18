package it.unitn.progweb.team05.matchweb.schemas;

public class Giornata {

    private int matchday;

    public Giornata() {}

    public Giornata(int matchday) {
        this.matchday = matchday;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }
}
