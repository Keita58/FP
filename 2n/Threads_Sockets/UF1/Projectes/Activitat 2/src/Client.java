import java.io.BufferedReader;
import java.io.InputStreamReader;
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

            BufferedReader inConsola = new BufferedReader(new InputStreamReader(System.in));
			String userInput;

            while(true) {
                userInput = inConsola.readLine();
                pEC.send(userInput);
                if(userInput.equals("BBYE")) 
                    break;
			}

            pEC.receive("KTHXBYE");
            pEC.close();
        } catch (Exception e) {
            
        }
    }
}
