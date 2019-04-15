package project.suzieqcraft.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class User extends ArrayList {

    protected  int userID;
    protected String name;
    protected String userEmail;
    protected boolean loggedIn;



    public User(int userID, String name, String userEmail) {
        this.userID = userID;
        this.name = name;
        this.userEmail = userEmail;
        this.loggedIn = loggedIn;
    }

    public User() {
    }

    public boolean setLoggedIn(){
        this.loggedIn = true;
        return loggedIn;
    }

    public boolean setLoggedOut(boolean loggedIn){
        this.loggedIn = false;
        return loggedIn;
    }

    public boolean isLoggedIn(){
        return this.loggedIn;
    }

    @JsonProperty("User_ID")
    public int getUserID() {
        return userID;
    }

    @JsonProperty("User_ID")
    public User setUserID(int userID) {
        this.userID = userID;
        return this;
    }

    @JsonProperty("Email")
    public String setEmail(String userEmail){
        this.userEmail = userEmail;
        return this.userEmail;
    }

    @JsonProperty("Email")
    public String getEmail(){
        return this.userEmail;
    }


    @JsonProperty("Product_ID")
    public String setName(String name){
        this.name = name;
        return this.name;
    }

    @JsonProperty("First_Name")
    public String getName(){
        return this.name;
    }



}
