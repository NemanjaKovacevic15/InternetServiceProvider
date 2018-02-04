package dbpaket;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author Nemanja
 */
@Entity
@Table(name = "Ugovor")
public class Ugovor {
    
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id", 0);
    private final IntegerProperty speed = new SimpleIntegerProperty(this, "brzina", 0);
    private final StringProperty traffic = new SimpleStringProperty(this, "paket", "");
    private final IntegerProperty duration = new SimpleIntegerProperty(this, "trajanje", 0);
    private final StringProperty userName = new SimpleStringProperty(this, "korisnickoIme", "");
    private final StringProperty address = new SimpleStringProperty(this, "adresa", "");
    
    
    public Ugovor(){}
    
    public Ugovor(int SPEED, String TRAFFIC, int DURATION, String USERNAME, String ADDRESS){
        this.speed.set(SPEED);
        this.traffic.set(TRAFFIC);
        this.duration.set(DURATION);
        this.userName.set(USERNAME);
        this.address.set(ADDRESS);
    }
    
    
    public IntegerProperty idProperty(){return id;}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getID(){return id.get();}
    public void setID(int ID){this.id.set(ID);}
    
    public IntegerProperty speedProperty(){return speed;}
    @Column(name = "brzina")
    public int getSpeed(){return speed.get();}
    public void setSpeed(int Speed){this.speed.set(Speed);}
    
    public StringProperty trafficProperty(){return traffic;}
    @Column(name = "paket")
    public String getTraffic(){return traffic.get();}
    public void setTraffic(String Traffic){this.traffic.set(Traffic);}
    
    public IntegerProperty durationProperty(){return duration;}
    @Column(name = "trajanje")
    public int getDuration(){return duration.get();}
    public void setDuration(int Duration){this.duration.set(Duration);}
    
    public StringProperty userNameProperty(){return userName;}
    @Column(name = "korisnickoIme")
    public String getuserName(){return userName.get();}
    public void setuserName(String userName){this.userName.set(userName);}
    
    public StringProperty addressProperty(){return address;}
    @Column(name = "adresa")
    public final String getAddress(){return address.get();}
    public final void setAddress(String Address){this.address.set(Address);} 

    
}
