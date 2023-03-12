package Marouan.SessionControl;

import Marouan.SessionControl.Presentation.SessionControleur;
import Marouan.SessionControl.Presentation.ISessionControleur;
import Marouan.SessionControl.Dao.IDao;
import Marouan.SessionControl.Dao.daoVolatile.SessionDao;
import Marouan.SessionControl.Metier.SessionMetier;
import Marouan.SessionControl.Metier.ISessionMetier;
import Marouan.SessionControl.Modele.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;
import java.util.Properties;

public class ElOuardi_Marouan_SessionControlApp {
    static ISessionControleur SessionControleur;

    public static void main(String[] args) throws Exception {
    Test3();
    }
    //    ===================================================================================================================================================
    //                                                                    TEST 1
    //    ===================================================================================================================================================
    public static void Test1(){
        IDao<Session, Long> dao = new SessionDao();
        SessionControleur = new SessionControleur();
        ISessionMetier metier = new SessionMetier();

        ((SessionMetier) metier).setSessionDao(dao);
        ((SessionControleur) SessionControleur).setSessionMetier(metier);
        try{
            SessionControleur.afficherSession_Time(1L);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Test 1 Completed");
    }
//    =================================================================================================================================================
//                                                                    TEST 2
//    =================================================================================================================================================
    public static void Test2(){
        IDao<Session, Long> dao = null;
        ISessionControleur controleur= null;
        ISessionMetier metier= null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        classLoader.getResourceAsStream("Marouan/SessionControl/config.properties");
        var config = classLoader.getResourceAsStream("Marouan/SessionControl/config.properties");
        Properties props = new Properties();
        try
    {
            props.load(config);
            var daoClass = props.getProperty("DAO");
            var serviceClass = props.getProperty("SERVICE");
            var controllerClass = props.getProperty("CONTROLLER");

            Class cDao = Class.forName(daoClass);
            dao = (IDao<Session, Long>) cDao.getDeclaredConstructor().newInstance();

            Class cMetier = Class.forName(serviceClass);
            metier = (ISessionMetier) cDao.getDeclaredConstructor().newInstance();

            Class cControleur = Class.forName(controllerClass);
            controleur = (ISessionControleur) cDao.getDeclaredConstructor().newInstance();

        //Dependecys Injection

        Method setMetier = cControleur.getMethod("setSessionMetier", ISessionMetier.class);
        setMetier.invoke(controleur,metier);

        Method setDao = cMetier.getMethod("setSessionDao", IDao.class);
        setDao.invoke(metier,dao);


        controleur.afficherSession_Time(1L);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            props.clear();
        }
    }
    //    ============================================================================================================================================
    //                                                                    TEST 3
    //    ============================================================================================================================================
    public static void Test3(){
        ApplicationContext application = new ClassPathXmlApplicationContext("sprinc-IOC.xml");
        ISessionControleur controleur = application.getBean(ISessionControleur.class);
        controleur.afficherSession_Time(1L);
    }
}