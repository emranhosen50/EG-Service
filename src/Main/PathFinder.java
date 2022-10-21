package Main;

import java.io.File;
import java.nio.file.Paths;

public class PathFinder {

    public final String file="file:/";

    public String FXML(String fxmlName){
        try{
            String path_FXML = new File("resource/FXML/"+fxmlName+".fxml").getAbsolutePath();
            String pathChangeFXML = path_FXML.replace("\\","/");
            return file+pathChangeFXML;
        }catch(Exception e){
            System.out.println("Error E2H:"+e);
            return "emranhosen50";
        }
    }

    public String CSS(String cssName){
        try{
            return Paths.get("resource/CSS/"+cssName+".css").toUri().toString();
        }catch(Exception e){
            System.out.println("Error E2H:"+e);
            return "emranhosen50";
        }
    }

    public String PNG(String pngName){
        try {
            return Paths.get("resource/ICON/"+pngName+".png").toUri().toString();
        }catch (Exception e){
            System.out.println("Error E2H:"+e);
            return "emranhosen50";
        }
    }

    public String JPG(){
        return "emranhosen50";
    }


}
