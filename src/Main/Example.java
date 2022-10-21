package Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Example {

    public static void main(String[] args)
    {
        PreferenceKey Pk=new PreferenceKey();
        ConnectMySQL connectMySQL=new ConnectMySQL();
        connectMySQL.createConnection();

        String Value=Pk.readPreference();
        System.out.println("PK: "+Value);

        GetUserNameAndIP();

        String[] List;
        List =new String[4];

        List[0]="A";
        List[1]="A";
        List[2]="A";
        List[3]="A";

        System.out.println(List[0]+List[1]);

    }
    public static void GetUserNameAndIP() {
        String UserName=System.getProperty("user.name");

        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String SystemIP = localhost.getHostAddress().trim();
        String IpAndUser=UserName+SystemIP;

        System.out.println("IpAndUser: "+IpAndUser);
    }


}
