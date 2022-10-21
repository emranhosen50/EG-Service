package Main;

import animatefx.animation.Pulse;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;

public class FrameCalling {


    PathFinder pathFinder=new PathFinder();
    AlertMessage alertMessage=new AlertMessage();

    public void CalledFrame(ActionEvent event, String FXMLName,String CSSName) {

        Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); //get Stage....
        try {
            URL url = new URL(pathFinder.FXML(FXMLName));    //Set The FXML Path....
            System.out.println("Path ?: "+pathFinder.FXML(FXMLName));
            FXMLLoader loader = new FXMLLoader(url);
            try {
                System.out.println("Debug1");
                Parent root = loader.load();
                Scene scene = new Scene(root);
                new Pulse(root).play(); //Animation,.,,,,,
                System.out.println("Debug2");
                scene.getStylesheets().add(pathFinder.CSS(CSSName)); //Set The CSS Path.... ..//SignInSignUp
                stage.setScene(scene);
                System.out.println("Debug1");
                stage.show();
            }catch (Exception e){
                //AlertForFileMissing(e.getMessage()+2+"..");
                alertMessage.AlertForFileMissing(e.getMessage());
            }

        }catch (Exception e){
            alertMessage.AlertForFileMissing(e.getMessage());
        }
    }
    public void CalledFrameWithME(MouseEvent event, String FXMLName, String CSSName) {

        Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); //get Stage....
        try {
            URL url = new URL(pathFinder.FXML(FXMLName));    //Set The FXML Path....
            System.out.println("Path ?: "+pathFinder.FXML(FXMLName));
            FXMLLoader loader = new FXMLLoader(url);
            try {
                System.out.println("Debug1");
                Parent root = loader.load();
                Scene scene = new Scene(root);
                new Pulse(root).play(); //Animation,.,,,,,
                System.out.println("Debug2");
                scene.getStylesheets().add(pathFinder.CSS(CSSName)); //Set The CSS Path.... ..//SignInSignUp
                stage.setScene(scene);
                System.out.println("Debug1");
                stage.show();
            }catch (Exception e){
                //AlertForFileMissing(e.getMessage()+2+"..");
                alertMessage.AlertForFileMissing(e.getMessage());
            }

        }catch (Exception e){
            alertMessage.AlertForFileMissing(e.getMessage());
        }
    }

}
