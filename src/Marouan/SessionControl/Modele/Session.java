package Marouan.SessionControl.Modele;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class Session {

    public static int session_Counter = 0;

    private  long id_Session;
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    private boolean isLogged;

    //private long Login_Date;
    //private long Logout_Date;

    LocalDateTime Login_Date;
    LocalDateTime Logout_Date;

    private Duration Session_Time;


    public Session(String login, String password, String nom, String prenom, String email, String telephone) {
        this.id_Session = session_Counter++;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.Login_Date = LocalDateTime.now();
        isLogged = true;
    }

    public void setLogout_Date() {
        Logout_Date = this.Login_Date.plusMinutes(3);
    }
    public void setSession_Time(Duration session_Time){
        Session_Time = session_Time;
    }

    @Override
    public String toString() {
        //each line is a field
        return
                "Id_Session= " + id_Session + "\n" +
                "Login= " + login + "\n" +
                "Password= " + password + "\n" +
                "Nom= " + nom + "\n" +
                "Prenom= " + prenom + "\n" +
                "Email= " + email + "\n" +
                "Telephone= " + telephone + "\n" +
                "IsLogged= " + isLogged + "\n" +
                "Login_Date= " + Login_Date.getHour()+ ":" + Login_Date.getMinute() + ":" + Login_Date.getSecond() + "s" + "\n" +
                "Logout_Date= " + Logout_Date.getHour()+ ":" + Logout_Date.getMinute() + ":" + Logout_Date.getSecond() + "s" + "\n" +
                "Session_Time= " + Session_Time.toMinutes() + " Minutes" + "\n";

    }

}


