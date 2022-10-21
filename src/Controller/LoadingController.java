package Controller;

import Main.AlertMessage;
import Main.FrameCalling;
import Main.PathFinder;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {

    public Circle close,minimize;
    public Button CSAButton;
    public ProgressBar LoadingProgress;
    public Text MessageForUser;

    public int i=0;

    Double mouseX,mouseY;

    PathFinder pathFinder=new PathFinder();
    AlertMessage alertMessage=new AlertMessage();
    FrameCalling frameCalling=new FrameCalling();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CSAButton.setVisible(false);
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
                        if(i==5){
                            CSAButton.setVisible(true);
                            MessageForUser.setText("You are not a service provider yet");
                            LoadingProgress.setVisible(false);

                            break;
                        }
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



    public void MouseClick(MouseEvent mouseEvent) {
        if(mouseEvent.getSource()==close){
            System.exit(0);
        }else if(mouseEvent.getSource()==minimize){
            Stage stage=(Stage) minimize.getScene().getWindow();
            stage.setIconified(true);
        }
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


    public void ActionEvent(ActionEvent event) {
        frameCalling.CalledFrame(event,"SPSignUp","SignInSignUp");
    }
}
