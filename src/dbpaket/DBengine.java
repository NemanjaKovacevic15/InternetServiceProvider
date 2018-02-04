package dbpaket;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Nemanja
 */
public class DBengine {
   
        static Session s = HibernateUtil.createSessionFactory().openSession();
        static Transaction t = null;
    
   public static void insertNew(int SPEED, String TRAFFIC, int DURATION, String USERNAME, String ADDRESS){
        Ugovor u = new Ugovor(SPEED, TRAFFIC, DURATION, USERNAME, ADDRESS);
        try {
            Session sn = HibernateUtil.createSessionFactory().openSession();
            t = s.beginTransaction();
            s.persist(u);
            t.commit();
            //sn.close();
        }
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi insertNewUser"+ex);}
        finally {
            HibernateUtil.close();}
    }
  
   
   public static void Delete(int IDfromDB) {
        try {
            Session sd = HibernateUtil.createSessionFactory().openSession();
            t = sd.beginTransaction();
            Ugovor u = (Ugovor)sd.load(Ugovor.class, IDfromDB);
            sd.delete(u);
            t.commit();
            sd.close();
        } 
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi delete "+ex);}
        finally {
            HibernateUtil.close();}
    }
      
      
    public static List<Ugovor> sviUgovori;
   public static void ListAll() {
          //Session s2 = HibernateUtil.createSessionFactory().openSession();
            String getAll = "from Ugovor";
            Query q = s.createQuery(getAll);
        try {
            t = s.beginTransaction();
            sviUgovori = q.list();
            t.commit();
        //s2.close();
        } 
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi ListAll"+ex);}
        finally {
            HibernateUtil.close();}         
    }
   
   
   
   
   
   
   
   
}