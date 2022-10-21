package Controller;

import Main.AlertMessage;
import Main.FrameCalling;
import Main.PathFinder;
import Main.PreferenceKey;
import animatefx.animation.Pulse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class SplashScreenController implements Initializable {

    @FXML
    MediaView mv;
    private MediaPlayer mp;
    private Media me;
    public Button RefreshButton;
    public ProgressIndicator loading;

    public int i=0;
    PathFinder pathFinder=new PathFinder();
    AlertMessage alertMessage=new AlertMessage();
    FrameCalling frameCalling=new FrameCalling();
    PreferenceKey Pk=new PreferenceKey();

    @FXML
    Circle close,minimize;

    Double mouseX,mouseY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshButton.setVisible(false);
        VideoPlay();
        clockRun2();
    }
    public void clockRun2()
    {
        Thread clock=new Thread()
        {
            public void run()
            {
                try
                {
                    for(;;)
                    {
                        i++;
                        if(i==10){
                            RefreshButton.setVisible(true);
                            loading.setVisible(false);
                            break;
                        }
                        System.out.println("Increment: "+i);
                        sleep(1000);
                    }

                }
                catch (InterruptedException ex)
                {
                    System.out.println("Error E2H:"+ex);
                }
            }

        }; clock.start();
    }

    private void VideoPlay() {
        String path = new File("resource/LogoAnimation/logo_video.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.play();
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
        }
    }


    public void ActionEvent(ActionEvent event) {
        boolean condition=true;
        condition=CheckUserAlreadyLoginORNot();
        System.out.println(condition);
        if(condition){
            frameCalling.CalledFrame(event,"SignInSignUpTow","SignInSignUp");
        }else{
            frameCalling.CalledFrame(event,"Home","SignInSignUp");
        }

    }
    private boolean CheckUserAlreadyLoginORNot() {
        String Value=Pk.readPreference();
        System.out.println("PK: "+Value);
        if(Value.equals("NULL")){
            return true;
        }else{
            return false;
        }
    }

}
