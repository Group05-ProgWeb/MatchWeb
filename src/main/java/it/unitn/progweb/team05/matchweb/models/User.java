package it.unitn.progweb.team05.matchweb.models;

import java.sql.Date;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String sport;
    private String favoriteTeam;
    private String role;
    private long totalScore;

    public User() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.username = "";
        this.password = "";
        this.role = "";
        this.totalScore = 0;
    }

    public User(String firstName, String lastName, String email, String username, String password, String role) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getLastName() {return lastName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}

    public Date getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth(Date dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public String getSport() {return sport;}
    public void setSport(String sport) {this.sport = sport;}

    public void setFavoriteTeam(String favoriteTeam) {this.favoriteTeam = favoriteTeam;}
    public String getFavoriteTeam() {return favoriteTeam;}

    public void setRole(String role) {this.role = role;}
    public String getRole() {return role;}

    public long getTotalScore() {return totalScore;}
    public void setTotalScore(long totalScore) {this.totalScore = totalScore;}
}
