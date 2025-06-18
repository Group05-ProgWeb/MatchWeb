package it.unitn.progweb.team05.matchweb.schemas;

public class Giornata {

    private int matchday;
    private String username;

    public Giornata() {}

    public Giornata(String username, int matchday) {
        this.username = username;
        this.matchday = matchday;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
