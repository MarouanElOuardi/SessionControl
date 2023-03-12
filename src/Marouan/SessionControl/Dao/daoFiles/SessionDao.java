package Marouan.SessionControl.Dao.daoFiles;

import Marouan.SessionControl.Dao.IDao;
import Marouan.SessionControl.Modele.Session;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
public class SessionDao implements IDao <Session, Long>{

    public Session trouverParId(Long id) {
        return BD_Credits().stream().filter(credit -> credit.getId_Session()==id).findFirst().get();
    }
    static Set<Session> BD_Credits() {
        var sessions= new HashSet<Session>(
                Arrays.asList(
                        new Session("Cj", "CjJava", "Marouan", "El Ouardi", "Cj@Pinky.com", "0666666666"),
                        new Session("Reyna", "ReynaJava", "Pinky", "Pinko", "Pinky@Reyna.com", "077777777"),
                        new Session("OM", "OmarJava", "Omar", "Midaoui", "Omar@Midaoui.com", "0888888888")
                ));


        return sessions;

    }
}

