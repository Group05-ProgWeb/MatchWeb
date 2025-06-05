package it.unitn.progweb.team05.matchweb;

public class Review {
    private int author_id;
    private String text;
    private int score;

    public Review(
            int author_id,
            String text,
            int score
    ){
        this.author_id = author_id;
        this.text = text;
        this.score = score;
    }

    public void setAuthorId(int author_id) {this.author_id = author_id;}
    public void setText(String text) {this.text = text;}
    public void setScore(int score) {this.score = score;}

    public int getAuthorId() {return author_id;}
    public String getText() {return text;}
    public int getScore() {return score;}
}

