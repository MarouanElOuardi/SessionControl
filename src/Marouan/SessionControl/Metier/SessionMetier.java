package Marouan.SessionControl.Metier;

import Marouan.SessionControl.Dao.IDao;
import Marouan.SessionControl.Modele.Session;
import lombok.Data;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class SessionMetier implements ISessionMetier {

    IDao<Session,Long> SessionDao;

    @Override
    public Session calculerSessionTime(long id_Session){

            var session= SessionDao.trouverParId(id_Session);
            if(session==null)
                throw new RuntimeException("Session introuvable ou non ouverte");
            else {
                session.setLogout_Date();
                Duration duration = Duration.between(session.getLogin_Date(), session.getLogout_Date());
                session.setSession_Time(duration);
            }

            return session;
        }
    }

