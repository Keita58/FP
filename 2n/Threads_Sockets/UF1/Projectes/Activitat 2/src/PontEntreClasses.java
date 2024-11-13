import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PontEntreClasses {

    private Socket socket;
    private BufferedReader bufferReader;
    private PrintWriter printWriter;
    private boolean verbose;
    
    public PontEntreClasses(Socket socket) {
        this.socket = socket;
        try {
            this.bufferReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PontEntreClasses(Socket socket, boolean verbose) {
        this.socket = socket;
        this.verbose = verbose;
        try {
            this.bufferReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String text) {
        this.printWriter.println(text);
        if(this.verbose) {
            System.out.println("S'està enviant el text: " + text);
        }
    }

    public String receive() {
        try {
            String text = this.bufferReader.readLine(); // Només es pot fer un readLine()
            if(this.verbose)
                System.out.println("S'està rebent el text: " + text);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    } 

    public void receive(String text) throws Interrupcio, IOException {
        if(!text.equals(this.bufferReader.readLine()))
                throw new Interrupcio("El text que has enviat: " + text + ", és diferent al text actual: " + this.bufferReader.readLine());
            if(this.verbose)
                System.out.println("S'està rebent el text: " + text);
    }

    public void close() {
        try {
            this.bufferReader.close();
            this.printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public String sendReceive(String text) {
        this.send(text);
        return receive();
    }

    public void sendReceive(String send, String receive) throws Interrupcio, IOException {
        this.send(send);
        this.receive(receive);
    }
}
