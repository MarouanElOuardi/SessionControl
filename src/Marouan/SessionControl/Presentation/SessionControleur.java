package Marouan.SessionControl.Presentation;

import Marouan.SessionControl.Metier.ISessionMetier;
import lombok.Data;
@Data
public class SessionControleur implements ISessionControleur {
    ISessionMetier SessionMetier;
    @Override
    public void afficherSession_Time(long id_Session){
        var session = SessionMetier.calculerSessionTime(id_Session);
        System.out.println(session);

    }

}
