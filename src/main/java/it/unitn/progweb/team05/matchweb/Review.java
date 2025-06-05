package it.unitn.progweb.team05.matchweb;

public class Review {

    private int id;

    private int author_id;
    private String text;
    private int score;

    public void setId(int id) {this.id = id;}
    public void setAuthorId(int author_id) {this.author_id = author_id;}
    public void setText(String text) {this.text = text;}
    public void setScore(int score) {this.score = score;}

    public int getId() {return id;}
    public int getAuthorId() {return author_id;}
    public String getText() {return text;}
    public int getScore() {return score;}
}

