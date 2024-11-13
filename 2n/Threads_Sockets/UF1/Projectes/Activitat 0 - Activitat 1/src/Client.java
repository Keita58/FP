import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 25000;
        try {
            Socket clientSocket = new Socket(hostName, port);
            PontEntreClasses pEC = new PontEntreClasses(clientSocket, true);
            pEC.receive("WELCOME");
            pEC.send("OH HI");
            pEC.send("BBYE");
            pEC.receive("KTHXBYE");
            pEC.close();
        } catch (Exception e) {
            
        }
    }
}
