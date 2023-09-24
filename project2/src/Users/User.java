package Users;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.*;


public class User implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    private String eid;
    private String username;
    private String password;
    private String role;

    public User(){

    }
    public User(String Eid, String username, String password, String role){
        this.eid = Eid;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean equals(User user) {
        if (user == this) {
            System.out.println("true");
            return true;
        }

        return this.eid.equals(user.eid) && this.username.equals(user.username);
    }



    //-----------------------------------------------Getters-----------------------------//
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEid(){
        return this.eid;
    }

    public String getRole() {
        return role;
    }

    //-------------------------------------------------Setters---------------------------------//


    public void setEid(String eid) {
        this.eid = eid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

}


















