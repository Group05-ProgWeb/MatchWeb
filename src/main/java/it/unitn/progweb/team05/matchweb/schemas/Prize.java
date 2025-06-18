package it.unitn.progweb.team05.matchweb.schemas;

public class Prize {
    private Long id;
    private int userId;
    private Long prizeTypeId;
    private java.sql.Timestamp awardedAt;

    public Prize() {}

    public Prize(Long id, int userId, Long prizeTypeId, java.sql.Timestamp awardedAt) {
        this.id = id;
        this.userId = userId;
        this.prizeTypeId = prizeTypeId;
        this.awardedAt = awardedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Long getPrizeTypeId() { return prizeTypeId; }
    public void setPrizeTypeId(Long prizeTypeId) { this.prizeTypeId = prizeTypeId; }
    public java.sql.Timestamp getAwardedAt() { return awardedAt; }
    public void setAwardedAt(java.sql.Timestamp awardedAt) { this.awardedAt = awardedAt; }
}
