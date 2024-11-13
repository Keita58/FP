import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        int port = 25000;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PontEntreClasses pEC = new PontEntreClasses(clientSocket, true);
            pEC.send("WELCOME");
            pEC.receive("OH HI");


            while(true) {
                String aux = pEC.receive();
                if(aux.equals("BBYE"))
                    break;
			}

            pEC.send("KTHXBYE");
            pEC.close();
        } catch (Exception e) {}
    }
}
