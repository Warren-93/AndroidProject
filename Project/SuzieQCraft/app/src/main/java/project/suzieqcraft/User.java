package project.suzieqcraft;

public class User {

    protected String email;
    protected String username;
    protected String usertype;
    protected boolean loggedIn;


    public User() {
        this.email = "";
        this.username = "";
        this.usertype = "";
        this.loggedIn = false;
    }

    public boolean userLogin(){
        loggedIn = true;
        return true;
    }

    public boolean userLogout(){
        loggedIn = false;
        return false;
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }
}
