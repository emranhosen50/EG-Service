package Controller;

import Main.*;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SPSingupController implements Initializable {

    public Circle close,minimize;
    public Pane SPChoosePane,AmbulancePane,CurrierPane,BloodPane,VoluntaryPane;
    public ImageView BackSpChoose,BackAmbulance,BackCurrier,BackBlood,BackVoluntary;
    public RadioButton RB_Blood,RB_Ambulance,RB_Currier,RB_Voluntary;
    public Button SpSignUpButton,NextBlood,NextAmbulance,NextCurrier,NextVoluntary;
    public Text Ambulamce_OutOf,Voluntary_OutOf,Blood_OutOf,Currier_OutOf;
    public TextField NidCard,B_ActiveNumber,A_LicenseNumber,A_DriverNumber,C_ActiveNumber,V_NYO;
    public CheckBox IAgree;
    public ComboBox<String> B_ServiceArea,B_BloodGroup,A_ServiceArea,C_VehiclesType,C_ServiceArea,V_ServiceArea;
    public String StoreValue;
    Double mouseX,mouseY;
    int A=0,B=1;
    String[] ArrayForChooseSelector;


    AlertMessage alertMessage=new AlertMessage();
    FrameCalling frameCalling=new FrameCalling();
    ConnectMySQL Con=new ConnectMySQL(); //Public SQLDatabase Connection
    PreferenceKey Pk=new PreferenceKey(); //Date Save For Device

    ObservableList<String> storeJeshoreAreaName = FXCollections.observableArrayList("Abhaynagar","Keshabpur","Chaugachha","Jhikargachha","Bagherpara","Manirampur","Jessore Sadar","Sharsha");
    ObservableList<String> storeVehiclesName = FXCollections.observableArrayList("Motor Cycle","Pick Up","Delivery Van","Cargo Van","Human Hauler");
    ObservableList<String> storeBloodsName = FXCollections.observableArrayList("A RhD positive (A+)","A RhD negative (A-)","B RhD positive (B+)","B RhD negative (B-)","O RhD positive (O+)","O RhD negative (O-)","AB RhD positive (AB+)","AB RhD negative (AB-)");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Con.createConnection();//Must be Connection Stable
        StoreValue=Pk.readPreference();
        SPChoosePane.toFront();
        B_ServiceArea.setItems(storeJeshoreAreaName);
        A_ServiceArea.setItems(storeJeshoreAreaName);
        C_ServiceArea.setItems(storeJeshoreAreaName);
        V_ServiceArea.setItems(storeJeshoreAreaName);
        V_ServiceArea.setItems(storeJeshoreAreaName);
        C_VehiclesType.setItems(storeVehiclesName);
        B_BloodGroup.setItems(storeBloodsName);
        //clockRun2();
    }


    public void MouseClick(MouseEvent mouseEvent) {
        if(mouseEvent.getSource()==close){
            System.exit(0);
        }else if(mouseEvent.getSource()==minimize){
            Stage stage=(Stage) minimize.getScene().getWindow();
            stage.setIconified(true);
        }else if(mouseEvent.getSource()==BackSpChoose){
            CallingHomeFrame(mouseEvent);
        }
        else if(mouseEvent.getSource()==BackBlood || mouseEvent.getSource()==BackAmbulance || mouseEvent.getSource()==BackCurrier || mouseEvent.getSource()==BackVoluntary){
            System.out.println("Back Icon Work ????");
            System.out.println("BBB: "+B);
            if(B==1){
                SPChoosePane.toFront();
                RB_Blood.setSelected(false);
                RB_Ambulance.setSelected(false);
                RB_Currier.setSelected(false);
                RB_Voluntary.setSelected(false);
                IAgree.setSelected(false);
                NidCard.setText("");
                NextBlood.setText("Next");NextAmbulance.setText("Next");NextCurrier.setText("Next");NextVoluntary.setText("Next");

                A=0;
            }else{
                String s=ArrayForChooseSelector[B-2];
                if(s.equals("BloodPane")){
                    BloodPane.toFront();
                    Blood_OutOf.setText(B-1+" out of "+A+" (Blood donation)");
                }else if(s.equals("AmbulancePane")){
                    AmbulancePane.toFront();
                    Ambulamce_OutOf.setText(B-1+" out of "+A+" (Ambulance service)");
                }else if(s.equals("CurrierPane")){
                    CurrierPane.toFront();
                    Currier_OutOf.setText(B-1+" out of "+A+" (Currier service)");
                }else if(s.equals("VoluntaryPane")){
                    VoluntaryPane.toFront();
                    Voluntary_OutOf.setText(B-1+" out of "+A+" (Voluntary and Relief)");
                }
                B=B-1;
            }

        }
    }

    public void RadioButtonActionEvent(ActionEvent event) {
    }

    public void ActionEvent(ActionEvent event) {
        ArrayForChooseSelector =new String[4];

        if(!NidCard.getText().equals("") && IAgree.isSelected()){

            if(RB_Blood.isSelected()){
                ArrayForChooseSelector[A]="BloodPane"; A++;
            }if(RB_Ambulance.isSelected()){
                ArrayForChooseSelector[A]="AmbulancePane"; A++;
            }if(RB_Currier.isSelected()){
                ArrayForChooseSelector[A]="CurrierPane"; A++;
            }if(RB_Voluntary.isSelected()){
                ArrayForChooseSelector[A]="VoluntaryPane"; A++;
            }
            CallingSelectedFirstPane(ArrayForChooseSelector[0]);
            //InitialCompleteButton();
        }
        else{
            alertMessage.AlertForWarning("Please Fill the from and Agree With Us");
        }

    }

    public void NextActionEvent(ActionEvent event) {

            if(event.getSource()==NextBlood){
                if(B_ActiveNumber.getText().toString().isEmpty() || B_ServiceArea.getValue()==null || B_BloodGroup.getValue()==null){
                    alertMessage.AlertForWarning("Please fill the all Information");
                }else{
                    String s=ArrayForChooseSelector[B++];

                    if(NextBlood.getText().equals("Complete")){
                        System.out.println("Finalaaaaa");
                        frameCalling.CalledFrame(event,"Home","SignInSignUp");
                    }
                    if(s!=null){
                        if(s.equals("BloodPane")){
                            BloodPane.toFront();
                            Blood_OutOf.setText(B+" out of "+A+" (Blood donation)");
                            if(A==B){NextBlood.setText("Complete");}
                        }else if(s.equals("AmbulancePane")){
                            AmbulancePane.toFront();
                            Ambulamce_OutOf.setText(B+" out of "+A+" (Ambulance service)");
                            if(A==B){NextAmbulance.setText("Complete");}
                        }else if(s.equals("CurrierPane")){
                            CurrierPane.toFront();
                            Currier_OutOf.setText(B+" out of "+A+" (Currier service)");
                            if(A==B){NextCurrier.setText("Complete");}
                        }else if(s.equals("VoluntaryPane")){
                            VoluntaryPane.toFront();
                            Voluntary_OutOf.setText(B+" out of "+A+" (Voluntary and Relief)");
                            if(A==B){NextVoluntary.setText("Complete");}
                        }
                    }else{
                        System.out.println("Exit");
                        CallingDatabaseSaveInfo();
                    }
                }


            }else if(event.getSource()==NextAmbulance){
                if(A_DriverNumber.getText().isEmpty() || A_LicenseNumber.getText().isEmpty() || A_ServiceArea.getValue()==null){
                    alertMessage.AlertForWarning("Please Fill the All Information");
                }else{
                    String s=ArrayForChooseSelector[B++];
                    if(NextBlood.getText().equals("Complete")){
                        System.out.println("Final");
                        frameCalling.CalledFrame(event,"Home","SignInSignUp");
                    }
                    if(s!=null){
                        if(s.equals("BloodPane")){
                            BloodPane.toFront();
                            Blood_OutOf.setText(B+" out of "+A+" (Blood donation)");
                            if(A==B){NextBlood.setText("Complete");}
                        }else if(s.equals("AmbulancePane")){
                            AmbulancePane.toFront();
                            Ambulamce_OutOf.setText(B+" out of "+A+" (Ambulance service)");
                            if(A==B){NextAmbulance.setText("Complete");}
                        }else if(s.equals("CurrierPane")){
                            CurrierPane.toFront();
                            Currier_OutOf.setText(B+" out of "+A+" (Currier service)");
                            if(A==B){NextCurrier.setText("Complete");}
                        }else if(s.equals("VoluntaryPane")){
                            VoluntaryPane.toFront();
                            Voluntary_OutOf.setText(B+" out of "+A+" (Voluntary and Relief)");
                            if(A==B){NextVoluntary.setText("Complete");}
                        }
                    }else{
                        System.out.println("Exit");
                        CallingDatabaseSaveInfo();
                    }
                }


            }else if(event.getSource()==NextCurrier){
            if(C_ActiveNumber.getText().isEmpty() || C_ServiceArea.getValue()==null || C_VehiclesType.getValue()==null){
                alertMessage.AlertForWarning("Please fill the All information");
            }else{
                String s=ArrayForChooseSelector[B++];
                if(NextBlood.getText().equals("Complete")){
                    System.out.println("Final");
                    frameCalling.CalledFrame(event,"Home","SignInSignUp");
                }
                if(s!=null){
                    if(s.equals("BloodPane")){
                        BloodPane.toFront();
                        Blood_OutOf.setText(B+" out of "+A+" (Blood donation)");
                        if(A==B){NextBlood.setText("Complete");}
                    }else if(s.equals("AmbulancePane")){
                        AmbulancePane.toFront();
                        Ambulamce_OutOf.setText(B+" out of "+A+" (Ambulance service)");
                        if(A==B){NextAmbulance.setText("Complete");}
                    }else if(s.equals("CurrierPane")){
                        CurrierPane.toFront();
                        Currier_OutOf.setText(B+" out of "+A+" (Currier service)");
                        if(A==B){NextCurrier.setText("Complete");}
                    }else if(s.equals("VoluntaryPane")){
                        VoluntaryPane.toFront();
                        Voluntary_OutOf.setText(B+" out of "+A+" (Voluntary and Relief)");
                        if(A==B){NextVoluntary.setText("Complete");}
                    }
                }else{
                    System.out.println("Exit");
                    CallingDatabaseSaveInfo();
                }
            }


            }else if(event.getSource()==NextVoluntary){

            if(V_NYO.getText().toString().isEmpty() || V_ServiceArea.getValue()==null){
                alertMessage.AlertForWarning("Please fill the All information");
            }else{
                try {
                    String s=ArrayForChooseSelector[B++];
                    if(NextBlood.getText().equals("Complete")){
                        System.out.println("Final");
                        frameCalling.CalledFrame(event,"Home","SignInSignUp");
                    }
                    if(s!=null){
                        if(s.equals("BloodPane")){
                            BloodPane.toFront();
                            Blood_OutOf.setText(B+" out of "+A+" (Blood donation)");
                            if(A==B){NextBlood.setText("Complete");}
                        }else if(s.equals("AmbulancePane")){
                            AmbulancePane.toFront();
                            Ambulamce_OutOf.setText(B+" out of "+A+" (Ambulance service)");
                            if(A==B){NextAmbulance.setText("Complete");}
                        }else if(s.equals("CurrierPane")){
                            CurrierPane.toFront();
                            Currier_OutOf.setText(B+" out of "+A+" (Currier service)");
                            if(A==B){NextCurrier.setText("Complete");}
                        }else if(s.equals("VoluntaryPane")){
                            VoluntaryPane.toFront();
                            Voluntary_OutOf.setText(B+" out of "+A+" (Voluntary and Relief)");
                            if(A==B){NextVoluntary.setText("Complete");}
                        }
                    }else{
                        System.out.println("Exit");
                        CallingDatabaseSaveInfo();
                    }
                }catch (Exception e){
                    System.out.println("Ex: "+e.getMessage());
                    CallingDatabaseSaveInfo();
                }

            }

            }
            else
                {
                System.out.println("Exit");
            }


        System.out.println("PPP: "+A);
        System.out.println("PPP: "+B);

    }

    private void CallingDatabaseSaveInfo() {
        Database_SPInfo();
        for(int x=0; x<A; x++){
            String s=ArrayForChooseSelector[x];
            if(s.equals("BloodPane")){
                DataBase_Blood();
            }else if(s.equals("AmbulancePane")){
                Database_Ambulance();
            }else if(s.equals("CurrierPane")){
                Database_Currier();
            }else if(s.equals("VoluntaryPane")){
                Database_Voluntary();
            }

        }
    }

    private void Database_SPInfo() {
        System.out.println("Database_SPInfo");
        try{
            System.out.println("1");

            PreparedStatement stmt = Con.con.prepareStatement("INSERT INTO info_sp VALUES( ?,?,?,?,?,? ) ");
            System.out.println("2");
            stmt.setString(1,"aaaaaaaaaaaaaaaaaa");
            stmt.setString(2,"aaaaaaaaaaaaaaaaa");
            stmt.setString(3,"aaaaaaaaaaaaaaaaa");
            stmt.setString(4,"aaaaaaaaaaaaaaaaa");
            stmt.setString(5,"aaaaaaaaaaaaaaaaa");
            stmt.setString(6,"aaaaaaaaaaaaaaaaa");

//            stmt.setString(3,ArrayForChooseSelector[0]);
//            stmt.setString(4,ArrayForChooseSelector[1]);
//            stmt.setString(5,ArrayForChooseSelector[2]);
//            stmt.setString(6,ArrayForChooseSelector[3]);

//            for(int b=0,databaseINC=3; b<4; b++,databaseINC++)
//            {
//                String s=ArrayForChooseSelector[b];
//                stmt.setString(databaseINC,s);
//                System.out.println("Number: "+databaseINC+" "+s);
//            }

            stmt.execute();
            stmt.close();
            System.out.println("3");
            NotificationTray("Successful", "Sign up successful "+StoreValue,1);
            System.out.println("4");

        }catch (Exception e){System.out.println("Hiiiiii");}
//        catch(SQLException ex)
//        {
//            System.out.println("SQLException");
//            if(ex.getMessage().equals("Duplicate entry '"+NidCard.getText()+"' for key 'signup_info.PRIMARY'"))
//            {
//                NotificationTray("Warning", "You have already signUp !",2);
//            }
//            NotificationTray("Network error","Tethering or hotspot has been disconnected due to network problems, Please try again leter.",3);
//            //SignUpWrongInfo.setText(ex.getMessage());
//        }
    }
    private void DataBase_Blood() {
        System.out.println("DataBase_Blood");
    }
    private void Database_Ambulance() {
        System.out.println("Database_Ambulance");
    }
    private void Database_Currier() {
        System.out.println("Database_Currier");
    }
    private void Database_Voluntary() {
        System.out.println("Database_Voluntary");
    }



    private void CallingHomeFrame(MouseEvent event) {
        frameCalling.CalledFrameWithME(event,"Home","SignInSignUp");
    }
    private void CallingSelectedFirstPane(String s) {
        if(NextBlood.getText().equals("Complete")){
            System.out.println("Final");

        }
        if(s!=null){
            if(s.equals("BloodPane")){
                BloodPane.toFront();
                Blood_OutOf.setText(B+" out of "+A+" (Blood donation)");
                if(A==B){NextBlood.setText("Complete");}
            }else if(s.equals("AmbulancePane")){
                AmbulancePane.toFront();
                Ambulamce_OutOf.setText(B+" out of "+A+" (Ambulance service)");
                if(A==B){NextAmbulance.setText("Complete");}
            }else if(s.equals("CurrierPane")){
                CurrierPane.toFront();
                Currier_OutOf.setText(B+" out of "+A+" (Currier service)");
                if(A==B){NextCurrier.setText("Complete");}
            }else if(s.equals("VoluntaryPane")){
                VoluntaryPane.toFront();
                Voluntary_OutOf.setText(B+" out of "+A+" (Voluntary and Relief)");
                if(A==B){NextVoluntary.setText("Complete");}
            }
        }else{
            System.out.println("Exit");
        }
    }

    void NotificationTray(String title,String message,int value)
    {
        tray.notification.TrayNotification tray = new TrayNotification();
        AnimationType type=AnimationType.SLIDE;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        if(value==1){
            tray.setNotificationType(NotificationType.SUCCESS);
        }
        else if(value==2){
            tray.setNotificationType(NotificationType.WARNING);
        }
        else if(value==3){
            tray.setNotificationType(NotificationType.INFORMATION);
        }
        tray.showAndDismiss(Duration.millis(2000));
        tray.showAndWait();
    }

    public void mouseDrag(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage=(Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX()-mouseX);
        stage.setY(mouseEvent.getScreenY()-mouseY);
    }

    public void mousePressed(javafx.scene.input.MouseEvent mouseEvent) {
        mouseX =mouseEvent.getSceneX();
        mouseY=mouseEvent.getSceneY();
    }
}
