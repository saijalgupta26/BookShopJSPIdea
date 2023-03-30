package com.bookshop.to;

public class User {

    private String username;
    private String password;
    private String completeName;
    private String email;
    private String isAdmin;
    private Boolean blocked;

    public Boolean getUnBlocked() {

        return unBlocked;
    }

    public void setUnBlocked(Boolean unBlocked) {
        this.unBlocked = unBlocked;
    }

    private Boolean unBlocked;

    public User(String username, String password, String completeName, String email, String isAdmin, Boolean blocked, Boolean unBlocked) {
        this.username = username;
        this.password = password;
        this.completeName = completeName;
        this.email = email;
        this.isAdmin=isAdmin;
        this.blocked =blocked;
        this.unBlocked=unBlocked;
    }
    public Boolean getBlocked() {
        return blocked;
    }
    public void setBlocked(Boolean blocked)
    {this.blocked = blocked;

    }






    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setisAdmin(String isAdmin)
    {
        this.isAdmin=isAdmin;
    }
    public String getisAdmin() {
        return isAdmin;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCompleteName() {
        return completeName;
    }
    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
