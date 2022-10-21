package Controller;

import Main.AlertMessage;
import Main.ConnectMySQL;
import Main.FrameCalling;
import Main.PathFinder;
import Main.PreferenceKey;
import animatefx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
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

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class HomeController implements Initializable {

    public Button homeButton,covid_19Button,profileButton,serviceButton,aboutButton,logoutButton,CreateSPACBTN;
    public Button OBloodBank,OAmbulance,OSOS,OFireService,OCurrier,OVoluntary;
    public ImageView home_icon,covid_icon,profile_icon,service_icon,about_icon;
    public Pane ServiceReqPane,ProfilePane,CovidUpdatePane,HomePane,AboutPane,OfferServicesPane;
    public Pane SeparateAmbulance,SeparateCurrier,SeparateVoluntary,ActiveOfferServices,SeparateSOS,SeparateBlood,SeparateFireService;
    public Text clockText,AMORPM,DATE,OfferServicesText,GetServiceText,HomeGetServiceText,HomeOfferService;

    public Text userID1,displayName,displayLocation;
    //ExtraClass
    ConnectMySQL Con=new ConnectMySQL(); //Public SQLDatabase Connection
    PreferenceKey Pk=new PreferenceKey(); //Date Save For Device
    public String EmailGet,NameGet,DistrictGet,PhoneGet,PasswordGet;
    //public TextField w10;

    public ComboBox<String> district;


    public Date CurrentDate;
    public GregorianCalendar date;
    public ToggleButton SwitchSPA;
    //About Sector
    public Button SZMailButton,ERMailButton;
    public ImageView SZMirror,ERMirror,ARMirror;
    public ImageView SZFacebook,SZWhatsApps,SZSkype,SZLinkedin;
    public ImageView ERFacebook,ERWhatsapp,ERSkype,ERLinkedin;
    public ImageView ERFacebook1,ERWhatsapp1,ERSkype1,ERLinkedin1;
    public int X=0,Y=0;
    //About Sector

    public Circle close,minimize;

    //PK: imran127.0.0.1

    Double mouseX,mouseY;
    PathFinder pathFinder=new PathFinder();
    AlertMessage alertMessage=new AlertMessage();
    FrameCalling frameCalling=new FrameCalling();
    PreferenceKey preferenceKey=new PreferenceKey();


    Image about_b = new Image(pathFinder.PNG("about_b"));
    Image about_h = new Image(pathFinder.PNG("about_h"));
    Image covid_b = new Image(pathFinder.PNG("covid_b"));
    Image covid_h = new Image(pathFinder.PNG("covid_h"));
    Image home_b = new Image(pathFinder.PNG("home_b"));
    Image home_h = new Image(pathFinder.PNG("home_h"));
    Image profile_b = new Image(pathFinder.PNG("profile_b"));
    Image profile_h = new Image(pathFinder.PNG("profile_h"));
    Image service_b = new Image(pathFinder.PNG("service_b"));
    Image service_h = new Image(pathFinder.PNG("service_h"));




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Con.createConnection();

        HomePane.toFront();
        Date();
        SetIconForCode();
        ClockFunction();
        AboutFrame();
        databaseForProfileSection();
    }

    private void databaseForProfileSection() {
        String Value=Pk.readPreference();
        System.out.println("PK: "+Value);


        try {

            Statement stmt= Con.con.createStatement();
            ResultSet rs =stmt.executeQuery("SELECT * FROM info_signup");
            while(rs.next())
            {
                 EmailGet = rs.getString("Email");
                 NameGet=rs.getString("Name");
                 DistrictGet= rs.getString("District");
                 PhoneGet= rs.getString("Phone");
                 PasswordGet= rs.getString("Password");

                System.out.println("PK: "+EmailGet);

                if(EmailGet.equals(Value))
                {

                    ObservableList<String> storeDistrictName = FXCollections.observableArrayList
                            ("Barguna (বরগুনা)","Barisal (বরিশাল)","Bhola (ভোলা)","Jhalokati (ঝালকাঠী)","Patuakhali (পটুয়াখালী)", "Pirojpur (পিরোজপুর)","Bandarban (বান্দরবান )","Brahmanbaria (ব্রাহ্মনবাড়ীয়া)","Chandpur (চাঁদপুর)",
                                    "Chittagong (চট্টগ্রাম)","Comilla (কুমিল্লা)","Cox's Bazar (কক্সবাজার)","Feni (ফেনী)","Khagrachari (খাগড়াছড়ি)","Lakshmipur (লক্ষীপুর)","Noakhali (নোয়াখালী)","Rangamati (রাঙ্গামাটি)","Dhaka (ঢাকা)",
                                    "Faridpur (ফরিদপুর),","Gazipur (গাজীপুর)","Gopalganj (গোপালগঞ্জ)","Kishoreganj (কিশোরগঞ্জ)","Madaripur (মাদারীপুর)","Manikganj (মানিকগঞ্জ)","Munshiganj (মুন্সীগঞ্জ)","Narayanganj (নারায়ণগঞ্জ)","Narsingdi (নরসিংদী)",
                                    "Rajbari (রাজবাড়ী)","Shariatpur (শরীয়তপুর)","Tangail (টাঙ্গাইল)","Jamalpur (জামালপুর)","Mymensingh (ময়মনসিংহ)","Netrakona (নেত্রকোনা)","Sherpur (শেরপুর)","Bagerhat (বাগেরহাট)","Chuadanga (চুয়াডাঙা)","Jessore (যশোর)",
                                    "Jhenaidah (ঝিনাইদহ)","Khulna (খুলনা)","Kushtia (কুষ্টিয়া)","Magura (মাগুরা)","Meherpur (মেহেরপুর)","Narail (নড়াইল)","Shatkhira (সাতক্ষীরা)","Bogra (বগুরা)","Jaipurhat (জয়পুরহাট)","Naogaon (নওগাঁ)","Natore (নাটোর)",
                                    "Nawabganj (নওয়াবগঞ্জ)","Pabna (পাবনা)","Rajshahi (রাজশাহী)","Sirajganj (সিরাজগঞ্জ)","Rangpur (রংপুর)","Gaibandha (গাইবান্ধা)","Kurigram (কুড়িগ্রাম)","Nilphamari (নীলফামারী)","Lalmonirhat (লালমনিরহাট)","Dinajpur (দিনাজপুর)",
                                    "Thakurgaon (ঠাকুরগাঁও)","Panchagarh (পঞ্চগড়)","Rangpur (রংপুর)","Gaibandha (গাইবান্ধা)","Kurigram (কুড়িগ্রাম)","Nilphamari (নীলফামারী)","Lalmonirhat (লালমনিরহাট)","Dinajpur (দিনাজপুর)","Thakurgaon (ঠাকুরগাঁও)","Panchagarh (পঞ্চগড়)");

                    userID1.setText(NameGet);
                    //wwwwww.setText(NameGet);
                    //qqqqqqq.setText(EmailGet);
                    district.setItems(storeDistrictName);
                    displayName.setText(NameGet);
                    displayLocation.setText(DistrictGet);

                    return;
                }
            }
            //SignInWrongInfo.setText("Your account email or password is incorrect.!!!");
            ///
            NotificationTray("Warring", "Your account email or password is incorrect.!!!",2);
            stmt.close();
        }catch (SQLException ex)
        {
            //SignInWrongInfo.setText(ex.getMessage());
            System.out.println("SignIn: "+ex.getMessage());
            NotificationTray("Network error","Tethering or hotspot has been disconnected due to network problems, Please try again leter.",3);
        }









    }

    private void Date() {
        CurrentDate=new Date();
        DateFormat dateFormate=new SimpleDateFormat("EEEEEE, dd");
        DateFormat dateFormate1=new SimpleDateFormat(" MMMM");
        String formateDate=dateFormate.format(CurrentDate);
        String formateDate1=dateFormate1.format(CurrentDate);
        DATE.setText(formateDate+"th"+formateDate1);
    }

    public void ClockFunction()
    {
        Thread clock=new Thread()
        {
            public void run()
            {
                try
                {
                    for(;;)
                    {
                        Calendar cal=new GregorianCalendar();
                        int HH= cal.get(Calendar.HOUR);
                        int MM= cal.get(Calendar.MINUTE);
                        int SS = cal.get(Calendar.SECOND);
                        int DAY=cal.get(Calendar.DATE);
                        int MONTH=cal.get(Calendar.WEEK_OF_MONTH);
                        int YEAR=cal.get(Calendar.YEAR);
                        String HR=String.valueOf(HH);
                        String MR=String.valueOf(MM);
                        String SR=String.valueOf(SS);

                        clockText.setText(HR+" : "+MR);

                        sleep(1000);
                    }

                }
                catch (InterruptedException ex)
                {
                    System.out.println("Error Clock 1");
                }
            }

        }; clock.start();
    }

    private void SetIconForCode() {

    }

    public void MainBTN_ActionEvent(javafx.event.ActionEvent actionEvent) {
        X=0;Y=0;
        if(actionEvent.getSource()==homeButton){
            home_icon.setImage(home_h);
            covid_icon.setImage(covid_b);
            profile_icon.setImage(profile_b);
            service_icon.setImage(service_b);
            about_icon.setImage(about_b);

            homeButton.setStyle("-fx-background-color: #578BCA; -fx-text-fill:#ffffff;"); //Unique

            covid_19Button.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            profileButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            serviceButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            aboutButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");

            //AboutPane.toFront();
            ServiceReqPane.toBack();
            ProfilePane.toBack();
            CovidUpdatePane.toBack();
            HomePane.toFront();
            AboutPane.toBack();

        }else if(actionEvent.getSource()==covid_19Button){
            home_icon.setImage(home_b);
            covid_icon.setImage(covid_h);
            profile_icon.setImage(profile_b);
            service_icon.setImage(service_b);
            about_icon.setImage(about_b);
            //YO
            covid_19Button.setStyle("-fx-background-color: #578BCA; -fx-text-fill:#ffffff;"); //Unique

            homeButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            profileButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            serviceButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            aboutButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");

            ServiceReqPane.toBack();
            ProfilePane.toBack();
            CovidUpdatePane.toFront();
            HomePane.toBack();
            AboutPane.toBack();

        }else if(actionEvent.getSource()==profileButton){
            home_icon.setImage(home_b);
            covid_icon.setImage(covid_b);
            profile_icon.setImage(profile_h);
            service_icon.setImage(service_b);
            about_icon.setImage(about_b);

            profileButton.setStyle("-fx-background-color: #578BCA; -fx-text-fill:#ffffff;"); //Unique

            homeButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            covid_19Button.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            serviceButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            aboutButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");

            ServiceReqPane.toBack();
            ProfilePane.toFront();
            CovidUpdatePane.toBack();
            HomePane.toBack();
            AboutPane.toBack();

        }else if(actionEvent.getSource()==serviceButton){
            home_icon.setImage(home_b);
            covid_icon.setImage(covid_b);
            profile_icon.setImage(profile_b);
            service_icon.setImage(service_h);
            about_icon.setImage(about_b);

            serviceButton.setStyle("-fx-background-color: #578BCA; -fx-text-fill:#ffffff;"); //Unique

            homeButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            covid_19Button.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            profileButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            aboutButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");

            ServiceReqPane.toFront();
            ProfilePane.toBack();
            CovidUpdatePane.toBack();
            HomePane.toBack();
            AboutPane.toBack();

        }else if(actionEvent.getSource()==aboutButton){
            home_icon.setImage(home_b);
            covid_icon.setImage(covid_b);
            profile_icon.setImage(profile_b);
            service_icon.setImage(service_b);
            about_icon.setImage(about_h);

            aboutButton.setStyle("-fx-background-color: #578BCA; -fx-text-fill:#ffffff;"); //Unique

            homeButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            covid_19Button.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            profileButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");
            serviceButton.setStyle("-fx-background-color : rgba(76, 175, 80, 0.0); -fx-text-fill:#578BCA;");

            ServiceReqPane.toBack();
            ProfilePane.toBack();
            CovidUpdatePane.toBack();
            HomePane.toBack();
            AboutPane.toFront();

        }
        else if(actionEvent.getSource()==CreateSPACBTN){
            frameCalling.CalledFrame(actionEvent,"SPSignUp","SignInSignUp");
        }
        else if(actionEvent.getSource()==logoutButton){
            preferenceKey.savePreference("NULL");
            frameCalling.CalledFrame(actionEvent,"SignInSignUpTow","SignInSignUp");
        }else if(actionEvent.getSource()==SwitchSPA){
            frameCalling.CalledFrame(actionEvent,"Loading","SignInSignUp");
        }
        else{
            System.out.println("NO Chance !!!");
        }
    }

    public void AboutFrame(){

        //new Flip(ERMirror).play();
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

    public void MouseClick(MouseEvent mouseEvent) {
        if(mouseEvent.getSource()==close){
            System.exit(0);
        }else if(mouseEvent.getSource()==minimize){
            Stage stage=(Stage) minimize.getScene().getWindow();
            stage.setIconified(true);
        }else if(mouseEvent.getSource()==GetServiceText || mouseEvent.getSource()==HomeGetServiceText){
            HomePane.toFront();
            OfferServicesPane.toBack();
        }else if(mouseEvent.getSource()==OfferServicesText || mouseEvent.getSource()==HomeOfferService){
            HomePane.toBack();
            OfferServicesPane.toFront();
        }else {
            System.out.println("Click 321");
        }
    }


    public void AnimationEntered(MouseEvent mouseEvent) {
        if(mouseEvent.getSource()==SZMirror){
            if(X==0){
                new Flip(SZMirror).play(); //X++;
            }
        }
        else if(mouseEvent.getSource()==ERMirror){
            if(Y==0){
                new Flip(ERMirror).play(); //Y++;
            }
        }
        else if(mouseEvent.getSource()==ARMirror){
            new Flip(ARMirror).play();
        }

        else if(mouseEvent.getSource()==ERFacebook){
            new Tada(ERFacebook).play();
        }
        else if(mouseEvent.getSource()==ERLinkedin){
            new Tada(ERLinkedin).play();
        }
        else if(mouseEvent.getSource()==ERSkype){
            new Tada(ERSkype).play();
        }
        else if(mouseEvent.getSource()==ERWhatsapp){
            new Tada(ERWhatsapp).play();
        }
        else if(mouseEvent.getSource()==SZFacebook){
            new Tada(SZFacebook).play();
        }
        else if(mouseEvent.getSource()==SZLinkedin){
            new Tada(SZLinkedin).play();
        }
        else if(mouseEvent.getSource()==SZSkype){
            new Tada(SZSkype).play();
        }
        else if(mouseEvent.getSource()==SZWhatsApps){
            new Tada(SZWhatsApps).play();
        }

        else if(mouseEvent.getSource()==ERFacebook1){
            new Tada(ERFacebook1).play();
        }
        else if(mouseEvent.getSource()==ERLinkedin1){
            new Tada(ERLinkedin1).play();
        }
        else if(mouseEvent.getSource()==ERSkype1){
            new Tada(ERSkype1).play();
        }
        else if(mouseEvent.getSource()==ERWhatsapp1){
            new Tada(ERWhatsapp1).play();
        }


    }

    public void BrowserLink(MouseEvent mouseEvent) {
        try {
            Desktop d=Desktop.getDesktop();
            if(mouseEvent.getSource()==ERFacebook){
                d.browse(new URI("https://www.facebook.com/emranhosen500"));
            }
            else if(mouseEvent.getSource()==ERLinkedin){
                d.browse(new URI("https://www.linkedin.com/in/emranhosen50/"));
            }
            else if(mouseEvent.getSource()==ERSkype){
                d.browse(new URI("imranhosan.imon11@outlook.com"));
            }
            else if(mouseEvent.getSource()==ERWhatsapp){
                d.browse(new URI("+8801642657005"));
            }
            else if(mouseEvent.getSource()==SZFacebook){
                d.browse(new URI("https://www.facebook.com/emranhosen500"));
            }
            else if(mouseEvent.getSource()==SZLinkedin){
                d.browse(new URI("https://www.facebook.com/emranhosen500"));
            }
            else if(mouseEvent.getSource()==SZSkype){
                d.browse(new URI("https://www.facebook.com/emranhosen500"));
            }
            else if(mouseEvent.getSource()==SZWhatsApps){
                d.browse(new URI("https://www.facebook.com/emranhosen500"));
            }
        }catch (Exception e){
            alertMessage.AlertForWarning ("You don't have any Browser");
        }
    }

    public void OptionButton(ActionEvent actionEvent) {
        if(actionEvent.getSource()==OBloodBank){
            new ZoomIn(SeparateBlood).play();
            SeparateBlood.toFront();
        }else if(actionEvent.getSource()==OAmbulance){
            new ZoomIn(SeparateAmbulance).play();
            SeparateAmbulance.toFront();
        }else if(actionEvent.getSource()==OSOS){
            new ZoomIn(SeparateSOS).play();
            SeparateSOS.toFront();
        }else if(actionEvent.getSource()==OCurrier){
            new ZoomIn(SeparateCurrier).play();
            SeparateCurrier.toFront();
        }else if(actionEvent.getSource()==OFireService){
            new ZoomIn(SeparateFireService).play();
            SeparateFireService.toFront();
        }else if(actionEvent.getSource()==OVoluntary){
            new ZoomIn(SeparateVoluntary).play();
            SeparateVoluntary.toFront();
        }
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
} //HomeController
