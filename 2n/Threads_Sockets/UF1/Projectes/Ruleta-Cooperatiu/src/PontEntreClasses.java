import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class PontEntreClasses {

    private Socket socket;
    private DataInputStream dataReader;
    private DataOutputStream dataWriter;
    private boolean verbose;

    // Bytes
    public static final byte ACK = 0b00000001;
    public static final byte S_BENVINGUT = 0b00000010;
    public static final byte S_NICK_EN_US = 0b00000011;
    public static final byte S_ESTAS_DINS = 0b00000100;
    public static final byte S_EN_CURS = 0b00000101;
    public static final byte S_APOSTA = 0b00000110;
    public static final byte S_CONTINUAR = 0b00000111;
    public static final byte C_SEGUIR = 0b00001000;
    public static final byte C_PLEGAR = 0b00001001;

    private String traduccioByte(byte text) {
        switch (text) {
            case 0b00000001:
                return "ACK";
            case 0b00000010:
                return "S_BENVINGUT";
            case 0b00000011:
                return "S_NICK_EN_US";
            case 0b00000100:
                return "S_ESTAS_DINS";
            case 0b00000101:
                return "S_EN_CURS";
            case 0b00000110:
                return "S_APOSTA";
            case 0b00000111:
                return "S_CONTINUAR";
            case 0b00001000:
                return "C_SEGUIR";
            case 0b00001001:
                return "C_PLEGAR";
            default:
                return null;
        }
    }
    
    public PontEntreClasses(Socket socket) throws IOException {
        this.socket = socket;
        this.dataReader = new DataInputStream(this.socket.getInputStream());
        this.dataWriter = new DataOutputStream(this.socket.getOutputStream());
    }

    public PontEntreClasses(Socket socket, boolean verbose) throws IOException {
        this.socket = socket;
        this.verbose = verbose;
        this.dataReader = new DataInputStream(this.socket.getInputStream());
        this.dataWriter = new DataOutputStream(this.socket.getOutputStream());
    }

    public void sendInt(int diners) throws IOException {
        this.dataWriter.writeInt(diners);
        this.dataWriter.flush();
        if(this.verbose) {
            System.out.println("S'està enviant els diners del jugador (" + diners + ") al servidor");
        }
    }

    public void sendByte(byte dades) throws IOException {
        this.dataWriter.writeByte(dades);
        this.dataWriter.flush();
        if(this.verbose) 
            System.out.println("S'està enviant el byte: " + traduccioByte(dades));
    }

    public void sendJSON(JSONObject dades) throws IOException, JSONException {
        byte[] aux = dades.toString().getBytes("UTF-8");
        this.dataWriter.writeInt(aux.length); // Enviem primer la llargada de l'array de bytes
        this.dataWriter.write(aux); //Després enviem els bytes tal qual
        if(this.verbose) 
            System.out.println("S'està enviant el JSON: " + dades.toString());
        this.dataWriter.flush();
    }

    public int receiveInt() throws Excepcio, NumberFormatException, IOException {
        int aux = this.dataReader.readInt();
        if(this.verbose)
            System.out.println("S'està rebent el número " + aux);
        return aux;
    }

    public byte receiveByte() throws ClassNotFoundException, IOException {
        byte dades = this.dataReader.readByte();
        if(this.verbose)
            System.out.println("S'està rebent el byte " + traduccioByte(dades));
        return dades;
    }

    public void receiveByte(byte b) throws Exception, IOException {
        byte aux = this.dataReader.readByte();
        if(b != aux)
            throw new Excepcio("El text que has enviat: " + traduccioByte(b) + ", és diferent al text actual: " + traduccioByte(aux));
        if(this.verbose)
            System.out.println("S'està rebent el text: " + traduccioByte(b));
    }

    public JSONObject receiveJSON() throws ClassNotFoundException, IOException {
        int length = dataReader.readInt(); //Agafem la llargada dels bytes enviats
        byte[] buffer = new byte[length]; //Creem un nou array de bytes
        dataReader.readFully(buffer); //Llegim tota la info de l'array de bytes
        String receivedString = new String(buffer, "UTF-8");
        JSONObject dades = new JSONObject(receivedString);
        if(this.verbose)
            System.out.println("S'està rebent el JSON " + dades.toString());
        return dades;
    }

    public void close() throws IOException {
        this.dataReader.close();
        this.dataWriter.close();
    }

    public boolean receiveNick() throws Excepcio, IOException, ClassNotFoundException {
        byte b = this.receiveByte();
        this.sendByte(ACK);
        if(b == ACK)
            return true;
        else 
            return false;
    }
}
