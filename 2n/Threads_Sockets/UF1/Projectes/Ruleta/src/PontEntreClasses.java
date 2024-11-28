import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    private DataInputStream dataReader;
    private DataOutputStream dataWriter;
    private boolean verbose;
    
    public PontEntreClasses(Socket socket) throws IOException {
        this.socket = socket;
        this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);
        this.bufferReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.dataReader = new DataInputStream(this.socket.getInputStream());
        this.dataWriter = new DataOutputStream(this.socket.getOutputStream());
        this.escripturaObject = new ObjectOutputStream(this.socket.getOutputStream());
        this.lecturaObject = new ObjectInputStream(this.socket.getInputStream());
    }

    public PontEntreClasses(Socket socket, boolean verbose) throws IOException {
        this.socket = socket;
        this.verbose = verbose;
        this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);
        this.bufferReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.dataReader = new DataInputStream(this.socket.getInputStream());
        this.dataWriter = new DataOutputStream(this.socket.getOutputStream());
        this.escripturaObject = new ObjectOutputStream(this.socket.getOutputStream());
        this.lecturaObject = new ObjectInputStream(this.socket.getInputStream());
    }

    public void send(String text) {
        this.printWriter.println(text);
        if(this.verbose) {
            System.out.println("S'està enviant el text: " + text);
        }
    }

    public void sendInt(int diners) throws IOException {
        this.dataWriter.writeInt(diners);
        if(this.verbose) {
            System.out.println("S'està enviant els diners del jugador (" + diners + ") al servidor");
        }
    }

    public void sendArray(int[] array) throws IOException {
        this.escripturaObject.writeObject(array);
        if(this.verbose) {
            System.out.println("S'està enviant l'array: ");
            System.out.print("[");
            for(int i = 0; i < array.length - 1; i++)
                System.out.print(array[i] + ", ");
            System.out.print(array[array.length - 1]);
            System.out.print("]");
        }
        System.out.println();
    }

    public void sendMap(HashMap<String, Integer> dades) throws IOException {
        this.escripturaObject.writeObject(dades);
        if(this.verbose) 
            System.out.println("S'està enviant l'el diccionari: " + dades.toString());
        
    }

    public String receive() throws IOException {
        String text = this.bufferReader.readLine(); // Només es pot fer un readLine()
        if(this.verbose)
            System.out.println("S'està rebent el text: " + text);
        return text;
    } 

    public int receiveInt() throws Excepcio, NumberFormatException, IOException {
        int aux = this.dataReader.readInt();
        if(this.verbose)
            System.out.println("S'està rebent el número " + aux);
        return aux;
    }

    public int[] receiveArray() throws ClassNotFoundException, IOException, Excepcio {
        int[] aux = (int[]) this.lecturaObject.readObject();
        if(this.verbose)
            System.out.println("S'està rebent l'array " + aux);
        return aux;
    }

    public HashMap<String, Integer> receiveMap() throws ClassNotFoundException, IOException {
        HashMap<String, Integer> dades = (HashMap<String, Integer>)this.lecturaObject.readObject();
        if(this.verbose)
            System.out.println("S'està rebent el diccionari " + dades.toString());
        return dades;
    }

    public void receive(String text) throws Excepcio, IOException {
        String aux = this.bufferReader.readLine();
        if(!text.equals(aux))
            throw new Excepcio("El text que has enviat: " + text + ", és diferent al text actual: " + aux);
        if(this.verbose)
            System.out.println("S'està rebent el text: " + text);
    }

    public void receiveMap(HashMap<String, Integer> dades) throws Excepcio, IOException {
        if(!(dades.containsKey("Numero") && dades.containsKey("Color") && dades.containsKey("Parell_Imparell") && dades.containsKey("Guanys")))
            throw new Excepcio("El diccionari que s'ha rebut " + dades.toString() +" no té les claus correctes");
        if(this.verbose)
            System.out.println("S'està rebent el diccionari " + dades.toString());
    }

    public void close() throws IOException {
        this.bufferReader.close();
        this.printWriter.close();
        this.lecturaObject.close();
        this.escripturaObject.close(); 
    }

    public String sendReceive(String text) throws IOException {
        this.send(text);
        return receive();
    }

    public void sendReceive(String send, String receive) throws Excepcio, IOException {
        this.send(send);
        this.receive(receive);
    }
}
