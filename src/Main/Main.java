package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;


public class Main extends Application {

    PathFinder pathFinder=new PathFinder();
    AlertMessage alertMessage=new AlertMessage();
    FrameCalling frameCalling=new FrameCalling();
    ConnectMySQL Con=new ConnectMySQL();

    @Override
    public void start(Stage primaryStage){
        primaryStage.initStyle(StageStyle.UNDECORATED);


        CallingScreen(primaryStage);

    }



    private void CallingScreen(Stage stage) {

        try {

            URL url = new URL(pathFinder.FXML("SplashScreen"));    //Set The FXML Path....
            System.out.println("Path ?: "+pathFinder.FXML("SplashScreen"));
            FXMLLoader loader = new FXMLLoader(url);
            try {
                System.out.println("Debug1");
                Parent root = loader.load();
                Scene scene = new Scene(root);
                System.out.println("Debug2");
                scene.getStylesheets().add(pathFinder.CSS("SignInSignUp")); //Set The CSS Path....
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

//PPPP
//    private void CallingScreen(Stage stage) {
//
//        try {
//            URL url = new URL(pathFinder.FXML("SplashScreen"));    //Set The FXML Path....
//            System.out.println("Path ?: "+pathFinder.FXML("SplashScreen"));
//            FXMLLoader loader = new FXMLLoader(url);
//            try {
//                System.out.println("Debug1");
//                Parent root = loader.load();
//                Scene scene = new Scene(root);
//                System.out.println("Debug2");
//                scene.getStylesheets().add(pathFinder.CSS("SignInSignUp")); //Set The CSS Path....
//                stage.setScene(scene);
//                System.out.println("Debug1");
//                stage.show();
//            }catch (Exception e){
//                //AlertForFileMissing(e.getMessage()+2+"..");
//                alertMessage.AlertForFileMissing(e.getMessage());
//            }
//
//        }catch (Exception e){
//            alertMessage.AlertForFileMissing(e.getMessage());
//        }
//
//    }



    //Main.........
    public static void main(String[] args) {
        launch(args);
    }

}
