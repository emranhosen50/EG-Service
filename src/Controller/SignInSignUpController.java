package Controller;

import Main.AlertMessage;
import Main.ConnectMySQL;
import Main.FrameCalling;
import Main.PreferenceKey;
import animatefx.animation.Flash;
import animatefx.animation.Flip;
import animatefx.animation.Pulse;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import java.io.File;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class SignInSignUpController implements Initializable {

    public Pane Signup,SignIn,titleBar,SplashScreenPane;
    public ImageView mirror;
    public Text SignInText,SignUpText;
    public TextField SignInEmail,SignUpName,SignUpEmail,SignUpNumber;
    public PasswordField SignInPass,SignUpPass;
    public ComboBox<String> district;
    public CheckBox CheckBox_I_Agree;
    public Circle close,minimize;
    public Button SignInButton,SignUpButton;

    public String S_GetName,S_GetEmail,S_GetPass,S_GetPhone,S_GetDistrict;
    public String L_GetEmail,L_GetPass,L_SPAvailable;
    public String IpAndUser,UserName,SystemIP;

    //Splash Screen
    public MediaView mv;
    private MediaPlayer mp;
    private Media me;
    public int i=0,j=0;

    //ExtraClass
    ConnectMySQL Con=new ConnectMySQL(); //Public SQLDatabase Connection
    PreferenceKey Pk=new PreferenceKey(); //Date Save For Device
    AlertMessage alertMessage=new AlertMessage();
    FrameCalling frameCalling=new FrameCalling();


    public Double mouseX,mouseY;

    ObservableList<String> storeDistrictName = FXCollections.observableArrayList
            ("Barguna (বরগুনা)","Barisal (বরিশাল)","Bhola (ভোলা)","Jhalokati (ঝালকাঠী)","Patuakhali (পটুয়াখালী)", "Pirojpur (পিরোজপুর)","Bandarban (বান্দরবান )","Brahmanbaria (ব্রাহ্মনবাড়ীয়া)","Chandpur (চাঁদপুর)",
                    "Chittagong (চট্টগ্রাম)","Comilla (কুমিল্লা)","Cox's Bazar (কক্সবাজার)","Feni (ফেনী)","Khagrachari (খাগড়াছড়ি)","Lakshmipur (লক্ষীপুর)","Noakhali (নোয়াখালী)","Rangamati (রাঙ্গামাটি)","Dhaka (ঢাকা)",
                    "Faridpur (ফরিদপুর),","Gazipur (গাজীপুর)","Gopalganj (গোপালগঞ্জ)","Kishoreganj (কিশোরগঞ্জ)","Madaripur (মাদারীপুর)","Manikganj (মানিকগঞ্জ)","Munshiganj (মুন্সীগঞ্জ)","Narayanganj (নারায়ণগঞ্জ)","Narsingdi (নরসিংদী)",
                    "Rajbari (রাজবাড়ী)","Shariatpur (শরীয়তপুর)","Tangail (টাঙ্গাইল)","Jamalpur (জামালপুর)","Mymensingh (ময়মনসিংহ)","Netrakona (নেত্রকোনা)","Sherpur (শেরপুর)","Bagerhat (বাগেরহাট)","Chuadanga (চুয়াডাঙা)","Jessore (যশোর)",
                    "Jhenaidah (ঝিনাইদহ)","Khulna (খুলনা)","Kushtia (কুষ্টিয়া)","Magura (মাগুরা)","Meherpur (মেহেরপুর)","Narail (নড়াইল)","Shatkhira (সাতক্ষীরা)","Bogra (বগুরা)","Jaipurhat (জয়পুরহাট)","Naogaon (নওগাঁ)","Natore (নাটোর)",
                    "Nawabganj (নওয়াবগঞ্জ)","Pabna (পাবনা)","Rajshahi (রাজশাহী)","Sirajganj (সিরাজগঞ্জ)","Rangpur (রংপুর)","Gaibandha (গাইবান্ধা)","Kurigram (কুড়িগ্রাম)","Nilphamari (নীলফামারী)","Lalmonirhat (লালমনিরহাট)","Dinajpur (দিনাজপুর)",
                    "Thakurgaon (ঠাকুরগাঁও)","Panchagarh (পঞ্চগড়)","Rangpur (রংপুর)","Gaibandha (গাইবান্ধা)","Kurigram (কুড়িগ্রাম)","Nilphamari (নীলফামারী)","Lalmonirhat (লালমনিরহাট)","Dinajpur (দিনাজপুর)","Thakurgaon (ঠাকুরগাঁও)","Panchagarh (পঞ্চগড়)");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Con.createConnection();//Must be Connection Stable
        district.setItems(storeDistrictName);
        district.setPromptText("Select your District");
        MouseCursor();
        SplashScreenPane.toBack();
        SignIn.toFront();

        GetUserNameAndIP();


    }

    private void GetUserNameAndIP() {
        UserName=System.getProperty("user.name");

        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        SystemIP = localhost.getHostAddress().trim();
        IpAndUser=UserName+SystemIP;

        System.out.println("Host: "+SystemIP+"UserName: "+UserName);
    }


    public void ActionEvent(ActionEvent event) {
        S_GetName=SignUpName.getText();
        S_GetEmail=SignUpEmail.getText();
        S_GetPass=SignUpPass.getText();
        S_GetPhone=SignUpNumber.getText();
        S_GetDistrict=district.getValue();
        L_GetEmail=SignInEmail.getText();
        L_GetPass=SignInPass.getText();

        if(event.getSource()==SignUpButton)
        {

            if(S_GetName.isEmpty() || S_GetEmail.isEmpty() || S_GetPass.isEmpty() || S_GetPhone.isEmpty() || S_GetDistrict == null )
            {
                alertMessage.AlertForInformation("Fill the all Information please!");
            }
            else
            {
                //System.out.println("S_GetDistrict: "+S_GetDistrict);
                SignUpMethod(S_GetName,S_GetEmail,S_GetPass,S_GetPhone,S_GetDistrict,event);
            }

        }
        else if(event.getSource()==SignInButton)
        {
            if(L_GetEmail.isEmpty() || L_GetPass.isEmpty()){
                alertMessage.AlertForInformation("Fill the all Information please!");
            }else{
                SignInMethod(L_GetEmail,L_GetPass,event);
            }

        }
    }

    public void SignUpMethod(String s_GetName, String s_GetEmail, String s_GetPass, String s_GetPhone, String s_GetDistrict, ActionEvent event)
    {
        try{
            System.out.println("1");

            PreparedStatement stmt = Con.con.prepareStatement("INSERT INTO info_signup VALUES( ?,?,?,?,? ) ");
            System.out.println("2");
            stmt.setString(1,s_GetEmail);
            stmt.setString(2,s_GetPass);
            stmt.setString(3,s_GetName);
            stmt.setString(4,s_GetDistrict);
            stmt.setString(5,s_GetPhone);
            stmt.execute();
            stmt.close();
            System.out.println("3");
            NotificationTray("Successful", "Sign up successful "+s_GetName,1);
            SignUpName.setText(""); SignUpEmail.setText(""); SignUpPass.setText(""); SignUpNumber.setText("");
            System.out.println("4");
            //frameCalling.CalledFrame(event,"SPSignUp","SignInSignUp");
            //Wrong Idia
            SignIn.toFront();
        }
        catch(SQLException ex)
        {
            System.out.println("SQLException");
            if(ex.getMessage().equals("Duplicate entry '"+s_GetEmail+"' for key 'signup_info.PRIMARY'"))
            {
                NotificationTray("Warning", "You have already signUp !",2);
            }
            NotificationTray("Network error","Tethering or hotspot has been disconnected due to network problems, Please try again leter.",3);
            //SignUpWrongInfo.setText(ex.getMessage());
        }
//        catch (Exception e){
//            System.out.println("Exception: "+e.getMessage());
//            NotificationTray("Warning", e.getMessage(),2);
//        }
    }//SignUpMethod

    public void SignInMethod(String setEmail, String setPass,ActionEvent actionEvent)
    {
        String DBName,DBEmail,DBPassword;
        try {
            Statement stmt= Con.con.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT * FROM info_signup");
            while(rs.next())
            {
                DBName = rs.getString("Name");
                DBEmail = rs.getString("Email");
                DBPassword = rs.getString("Password");
                if(DBEmail.equals(setEmail) && DBPassword.equals(setPass))
                {
                    System.out.println("Login successful "+DBName);
                    NotificationTray("Successful", "Login successful "+DBName,1);
                    //SignInWrongInfo.setText("Login successful "+DBName);
                    SignInEmail.setText(""); SignInPass.setText("");
                    //Insert Login Information
//                    PreparedStatement Pstmt = Con.con.prepareStatement
//                            ("insert into info_login (Email,Password,IP_UserName) values ('"+DBEmail+"','"+DBPassword+"','');");
//                    Pstmt.execute();
//                    Pstmt.close();
                    Pk.savePreference(DBEmail); //Remember this name
                    //Insert Login Information
                    //MainFrame(actionEvent);
                    frameCalling.CalledFrame(actionEvent,"Home","SignInSignUp");
                    return;
                }
            }
            //SignInWrongInfo.setText("Your account email or password is incorrect.!!!");
            ///
            NotificationTray("Warring", "Your account email or password is incorrect.!!!",2);
            //stmt.close();
        }catch (SQLException ex)
        {
            //SignInWrongInfo.setText(ex.getMessage());
            System.out.println("SignIn: "+ex.getMessage());
            NotificationTray("Network error","Tethering or hotspot has been disconnected due to network problems, Please try again leter.",3);
        }

    }//SignInMethod



    public void MouseCursor(){
        close.setCursor(Cursor.DEFAULT);
        minimize.setCursor(Cursor.DEFAULT);
        titleBar.setCursor(Cursor.MOVE);
        SignInText.setCursor(Cursor.HAND);
        SignUpText.setCursor(Cursor.HAND);
        CheckBox_I_Agree.setCursor(Cursor.HAND);
        SignInEmail.setCursor(Cursor.TEXT);
        SignUpName.setCursor(Cursor.TEXT);
        SignUpEmail.setCursor(Cursor.TEXT);
        SignUpNumber.setCursor(Cursor.TEXT);
        SignInPass.setCursor(Cursor.TEXT);
        SignUpPass.setCursor(Cursor.TEXT);
        SignInButton.setCursor(Cursor.HAND);
        SignUpButton.setCursor(Cursor.HAND);
    }

    public void MouseClicked(MouseEvent mouseEvent){
        //System.out.println("Hi");
        if(mouseEvent.getSource()==SignInText){
            SignIn.toFront();
            Signup.toBack();
            new Flash(Signup).play();
            new Pulse(SignIn).play();
            new Flip(mirror).play();

        }else if(mouseEvent.getSource()==SignUpText){
            Signup.toFront();
            SignIn.toBack();
            new Pulse(Signup).play();
            new Flash(SignIn).play();
            new Flip(mirror).play();
        }
    }

    /////
    public void mouseDrag(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage=(Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX()-mouseX);
        stage.setY(mouseEvent.getScreenY()-mouseY);
    }

    public void mousePressed(javafx.scene.input.MouseEvent mouseEvent) {
        mouseX =mouseEvent.getSceneX();
        mouseY=mouseEvent.getSceneY();
    }

    public void MouseClick(MouseEvent mouseEvent) {
        if(mouseEvent.getSource()==close){
            System.exit(0);
        }else if(mouseEvent.getSource()==minimize){
            Stage stage=(Stage) minimize.getScene().getWindow();
            stage.setIconified(true);
        }

        System.out.println("S_GetDistrict: "+S_GetDistrict);
    }


    void NotificationTray(String title,String message,int value)
    {
        TrayNotification tray = new TrayNotification();
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


}
