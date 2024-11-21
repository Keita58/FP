import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class PontEntreClasses {

    private Socket socket;
    private ObjectInputStream lecturaObject;
    private ObjectOutputStream escripturaObject;
    private BufferedReader bufferReader;
    private PrintWriter printWriter;
    private boolean verbose;
    
    public PontEntreClasses(Socket socket) {
        this.socket = socket;
        try {
            this.bufferReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);
            this.lecturaObject = new ObjectInputStream(this.socket.getInputStream());
            this.escripturaObject = new ObjectOutputStream(this.socket.getOutputStream());
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
            this.lecturaObject = new ObjectInputStream(this.socket.getInputStream());
            this.escripturaObject = new ObjectOutputStream(this.socket.getOutputStream());
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

    public void send(int[] array) {
        try {
            this.escripturaObject.writeObject(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(this.verbose) {
            System.out.println("S'està enviant l'array: ");
            for(int i = 0; i < array.length; i++)
                System.out.print(array[i] + ", ");
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

    public void receive(String text) throws Excepcio, IOException {
        if(!text.equals(this.bufferReader.readLine()))
                throw new Excepcio("El text que has enviat: " + text + ", és diferent al text actual: " + this.bufferReader.readLine());
            if(this.verbose)
                System.out.println("S'està rebent el text: " + text);
    }

    public void receive(HashMap<String, Integer> dades) {
        
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

    public void sendReceive(String send, String receive) throws Excepcio, IOException {
        this.send(send);
        this.receive(receive);
    }
}
