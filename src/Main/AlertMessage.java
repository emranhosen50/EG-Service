package Main;

import javafx.scene.control.Alert;

public class AlertMessage {

    public void AlertForFileMissing(String errorMessage){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File missing");
        alert.setContentText(errorMessage);
        alert.setHeaderText("Resource File Missing");
        alert.setOnCloseRequest(event -> {
            System.out.println("Exit");
            System.exit(0);
        });
        alert.showAndWait();
    }
    public void AlertForCONFIRMATION(String confirmationMessage){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION DIALOG");
        alert.setContentText(confirmationMessage);
        alert.setHeaderText("Resource File Missing");
        alert.setOnCloseRequest(event -> {
            System.out.println("Exit");
            System.exit(0);
        });
        alert.showAndWait();
    }
    public void AlertForWarning(String warningMessage){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING DIALOG");
        alert.setContentText(warningMessage);
        alert.setHeaderText("WARNING");
        alert.showAndWait();
    }
    public void AlertForInformation(String informationMessage){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("INFORMATION DIALOG");
        alert.setContentText(informationMessage);
        alert.setHeaderText("INFORMATION");
        alert.showAndWait();
    }


}
