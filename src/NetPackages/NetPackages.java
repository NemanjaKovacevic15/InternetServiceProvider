package NetPackages;

import dbpaket.DBengine;
import dbpaket.Ugovor;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 *
 * @author Nemanja
 */
public class NetPackages extends Application {
    
    public static Label actionInfo = new Label("");
    
    public static void main(String[] args) {
     Application.launch(args);
   }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button1 = new Button("Prikazi Sve Klijente");
        Button button2 = new Button("Dodaj Novog Klijenta");
        
        button1.setOnAction((ActionEvent event) -> {listAll();});
        button2.setOnAction((ActionEvent event) -> {addNew();});
        
        GridPane pane = new GridPane();
        pane.setVgap(20.0);
        pane.setHgap(20.0);
        pane.addRow(3,new Label(" "), new Label("Izaberi:"), button1, button2);
        pane.add(actionInfo,2,5,4,1);
        pane.setColumnSpan(actionInfo, 6);
        Scene s = new Scene(pane);
        primaryStage.setTitle("Internet Provajder");
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(400);
        primaryStage.setResizable(false);
        primaryStage.setScene(s);
        primaryStage.show();
    }
    
    public static void listAll(){
        Stage listStage = new Stage();
        GridPane gPane = new GridPane();
        gPane.setVgap(10.0);
        gPane.setHgap(20.0);
        gPane.add(new Label("ID"),0,1,4,1);
        gPane.add(new Label("IME"),1,1,4,1);
        gPane.add(new Label("ADRESA"),2,1,4,1);
        gPane.add(new Label("TRAJANJE"),3,1,4,1);
        gPane.add(new Label("BRZINA"),4,1,4,1);
        gPane.add(new Label("PAKET"),5,1,4,1);
        
        DBengine.ListAll();
        List<Ugovor> svi = DBengine.sviUgovori;
        int x = 3;
        
        for(int i=0;i<svi.size();i++){
         final int j = svi.get(i).getID();
         Button[] btn = new Button[svi.size()];
         btn[i] = new Button("izbrisi");
         gPane.addRow(x, new Label(String.valueOf(svi.get(i).getID())), new Label(svi.get(i).getuserName()), new Label(svi.get(i).getAddress()), new Label(String.valueOf(svi.get(i).getDuration()+" godina(a)")), new Label(String.valueOf(svi.get(i).getSpeed()+" Mbits")), new Label(svi.get(i).getTraffic()+" GB"),btn[i]);
         btn[i].setOnAction((ActionEvent event) -> {DBengine.Delete(j); listStage.hide(); actionInfo.setText("Klijent Je Izbrisan!"); });
         x++;
        }
        ScrollPane SKROLpane = new ScrollPane(gPane);
        Scene scene = new Scene(SKROLpane);
        listStage.setScene(scene);
        listStage.setTitle("Prikazi Sve Klijente");
        listStage.setMinWidth(600);
        listStage.setMaxHeight(600);
        listStage.setResizable(false);
        listStage.show();
    }
    
    public static int selectedSPEED;
    public static String selectedTRAFFIC;
    public static int selectedDURATION;
    TextField nameField = new TextField();
    TextField adrsField = new TextField();
    RadioButton speed1 = new RadioButton("2");
    RadioButton speed2 = new RadioButton("5");
    RadioButton speed3 = new RadioButton("10");
    RadioButton speed4 = new RadioButton("20");
    RadioButton speed5 = new RadioButton("50");
    RadioButton speed6 = new RadioButton("100");
    RadioButton traffic1 = new RadioButton("1");
    RadioButton traffic2 = new RadioButton("5");
    RadioButton traffic3 = new RadioButton("10");
    RadioButton traffic4 = new RadioButton("100");
    RadioButton traffic5 = new RadioButton("Flat");
    RadioButton duration1 = new RadioButton("1");
    RadioButton duration2 = new RadioButton("2");
    Label speedInfo = new Label();
    Label trafficInfo = new Label();
    Label durationInfo = new Label();
    Label insertinfo = new Label();
    Button button = new Button("SACUVAJ");
    
    
    public void addNew() {
        
        Stage primaryStage = new Stage();
        speed1.setPrefWidth(50);
        speed2.setPrefWidth(50);
        speed3.setPrefWidth(50);
        speed4.setPrefWidth(50);
        speed5.setPrefWidth(50);
        speed6.setPrefWidth(50);
        traffic1.setPrefWidth(50);
        traffic2.setPrefWidth(50);
        traffic3.setPrefWidth(50);
        traffic4.setPrefWidth(50);
        traffic5.setPrefWidth(50);
        duration1.setPrefWidth(50);
        duration2.setPrefWidth(50);
        button.setPrefWidth(100);
        
        button.setOnAction((ActionEvent event) -> {
         if(!nameField.getText().trim().equals("")&&!adrsField.getText().trim().equals("")) {
            DBengine.insertNew(selectedSPEED, selectedTRAFFIC, selectedDURATION, nameField.getText().trim(), adrsField.getText().trim());
            actionInfo.setText("Klijent je Dodat!");
            primaryStage.close();
         } else {insertinfo.setText("Ime i Adresa polja ne mogu biti prazna!");}
        });
        
        ToggleGroup groupSpeed = new ToggleGroup();
        groupSpeed.getToggles().addAll(speed1,speed2,speed3,speed4,speed5,speed6);
        groupSpeed.selectedToggleProperty().addListener((observable, oldValue, newValue) -> toggleSpeed(observable, oldValue, newValue));
        
        ToggleGroup groupTraffic = new ToggleGroup();
        groupTraffic.getToggles().addAll(traffic1,traffic2,traffic3,traffic4,traffic5);
        groupTraffic.selectedToggleProperty().addListener((observable, oldValue, newValue) -> toggleTraffic(observable, oldValue, newValue));
        
        ToggleGroup groupDuration = new ToggleGroup();
        groupDuration.getToggles().addAll(duration1, duration2);
        groupDuration.selectedToggleProperty().addListener((observable, oldValue, newValue) -> toggleDuration(observable, oldValue, newValue));
        
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10.0);
        gridPane.setHgap(10.0);
        gridPane.addRow(1,new Label("Ime:"),nameField);
        gridPane.setColumnSpan(nameField, 6);
        gridPane.addRow(2,new Label("Adresa:"),adrsField);
        gridPane.setColumnSpan(adrsField, 6);
        gridPane.addRow(3,new Label("Trajanje ugovora u godinama:"),duration1,duration2);
        gridPane.addRow(4,new Label("Paket u GB:"),traffic1,traffic2,traffic3,traffic4,traffic5);
        gridPane.addRow(5,new Label("Speed u Mbits:"),speed1,speed2,speed3,speed4,speed5,speed6);
        gridPane.add(durationInfo,1,7,4,1);
        gridPane.add(trafficInfo,1,8,4,1);
        gridPane.add(speedInfo,1,9,4,1);
        gridPane.add(insertinfo,1,13,4,1);
        gridPane.add(button,5,15,4,1);
        
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dodaj Novog Klijenta");
        primaryStage.setResizable(false);
        primaryStage.show();
        
        speed1.setSelected(true);
        traffic1.setSelected(true);
        duration1.setSelected(true);
    }
    
    private void toggleSpeed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue){
        
        if(newValue != null){
            ToggleButton toggleButton = (ToggleButton) newValue;
            speedInfo.setText("Izabrana Brazina Je " + toggleButton.getText() + " Mbits");
            selectedSPEED = Integer.valueOf(toggleButton.getText());
        }
    }
    
    private void toggleTraffic(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue){
        
        if(newValue != null){
            ToggleButton toggleButton = (ToggleButton) newValue;
            trafficInfo.setText("Vas Izabrani Paket Je " + toggleButton.getText() + " GB");
            selectedTRAFFIC = toggleButton.getText();
        }
    }
     
    private void toggleDuration(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue){
        
        if(newValue != null){
            ToggleButton toggleButton = (ToggleButton) newValue;
            durationInfo.setText("Vas Izabrani Ugovor Je " + toggleButton.getText() + " godina(e)");
            selectedDURATION = Integer.valueOf(toggleButton.getText());
         }
    } 
    
    
}