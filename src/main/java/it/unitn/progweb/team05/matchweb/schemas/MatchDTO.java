package it.unitn.progweb.team05.matchweb.schemas;

public class MatchDTO {
    private String matchId;
    private TeamDTO home;
    private TeamDTO away;
    private int matchDay;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public TeamDTO getHome() {
        return home;
    }

    public void setHome(TeamDTO home) {
        this.home = home;
    }

    public TeamDTO getAway() {
        return away;
    }

    public void setAway(TeamDTO away) {
        this.away = away;
    }

    public int getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(int matchDay) {
        this.matchDay = matchDay;
    }
}
