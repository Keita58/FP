package Examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class MainExamen {

	public static void main(String[] args) {
		Scanner ohowo = new Scanner(System.in);
		
		//Exercici 1
		System.out.println("Quants jugadors vols afegir?");
		int maxJugadors = ohowo.nextInt();
		ohowo.nextLine();
		
		Queue<String> jugadors = new LinkedList<>();
		Map<String, Integer> torns = new HashMap();
		
		for(int i = 0;  i< maxJugadors; i++) {
			System.out.println("Escriu el nom del jugador: ");
			String nom = ohowo.nextLine();
			jugadors.offer(nom);
			torns.put(nom, 0);
		}
		Random r = new Random();
		System.out.println();
		
		int i = 0;
		while(!jugadors.isEmpty()) {
			int random = r.nextInt(0, maxJugadors);
			for(int j = 0; j < random; j++) {
                jugadors.add(jugadors.poll());
            }
			String nom = jugadors.peek();
			int torn = torns.get(nom);
			if(torn < 5) {
				torns.put(nom, torns.get(nom)+1);
			}
			else 
				System.out.print(jugadors.poll() + " ");
		}
		
		//Exercici 2
		System.out.println("Entra una llista de números separats per guions: ");
		String llista = ohowo.nextLine();
		String[] aux = llista.split("-");
		ArrayList<String> llistaSeparada = new ArrayList<String>();
		ArrayList<String> llistaMultiples = new ArrayList<String>();
		
		System.out.print("Llista de números: ");
		for(int j = 0; j < aux.length; j++) {
			if(Integer.parseInt(aux[j])%13 != 0) 
				llistaSeparada.add(aux[j]);
			else
				llistaMultiples.add(aux[j]);
			System.out.print(aux[j] + " ");
		}
		System.out.println();
		
		int sum = 0;
		for(int j = 0; j < llistaSeparada.size(); j++) {
			sum += Integer.parseInt(llistaSeparada.get(j));
		}
		System.out.println("Suma de números no múltiples 13: " + sum);
		
		int sum2 = 0;
		for(int j = 0; j < llistaMultiples.size(); j++) {
			sum2 += Integer.parseInt(llistaMultiples.get(j));
		}
		System.out.println("Suma de números múltiples 13: " + sum2);
		
		if(sum > sum2) {
			llistaMultiples.clear();
			System.out.println("La suma dels números de la llista no múltiples és més gran.");
		}
		else if(sum2 > sum) {
			llistaSeparada.clear();
			System.out.println("La suma dels números de la llista de múltiples és més gran.");
		}
		else
			System.out.println("La suma de les dues llistes és exactament igual");
		
		System.out.print("Llista de números no múltiples: [");
		for(int j = 0; j < llistaSeparada.size(); j++) {
			System.out.print(llistaSeparada.get(j) + ", ");
		}
		System.out.print("]");
		System.out.println();
		
		System.out.print("Llista de números múltiples: [");
		for(int j = 0; j < llistaMultiples.size(); j++) {
			System.out.print(llistaMultiples.get(j) + ", ");
		}
		System.out.print("]");
		
		//Exercici 3
		
		
		//Exercici 4
		
		Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        System.out.println();
        System.out.println("Imprimeixo a la base de dades");
		try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/videojocjdbc?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM jugador");
            System.out.println("Dades de la base de dades: ");
            while (rs.next()){
                System.out.print("Nom: " + rs.getString(2) + ", ");
                System.out.print("Saldo: "+rs.getDouble(3) + ", ");
                System.out.print("Punts: "+ rs.getDouble(4));
                System.out.println();
            }
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException sqlEx) {
                }
                st = null;
            }
        }
		
		System.out.println();
        System.out.println("M'afegeixo a la base de dades");
		try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/videojocjdbc?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM jugador");

            String sqlInsertar = "INSERT INTO jugador (nom, punts, saldo) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlInsertar);
            ps.setString(1, "Marc Sánchez López");
            ps.setDouble(2, 24);
            ps.setDouble(3, 240);
            ps.executeUpdate();
        

            rs = st.executeQuery("SELECT * FROM jugador");
            System.out.println("Dades de la base de dades: ");
            while (rs.next()){
                System.out.print("Nom: " + rs.getString(2) + ", ");
                System.out.print("Saldo: "+rs.getDouble(3) + ", ");
                System.out.print("Punts: "+ rs.getDouble(4));
                System.out.println();
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { }
                rs = null;
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException sqlEx) { }
                st = null;
            }

        }
		
		System.out.println();
        System.out.println("Actualitzo els valors de la base de dades");
		try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/videojocjdbc?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM jugador");

         
            while (rs.next()) {
                if (rs.getDouble("punts") > 35) {
                    String updatear = "UPDATE jugador SET saldo = ? WHERE idjugador = ?";
                    PreparedStatement ps = conn.prepareStatement(updatear);
                    ps.setDouble(1, (rs.getDouble("saldo")+100));
                    ps.setInt(2, rs.getInt("idjugador"));
                    ps.executeUpdate();
                }
            }
            
            rs = st.executeQuery("SELECT * FROM jugador");
            System.out.println("Dades de la base de dades: ");
            while (rs.next()){
                System.out.print("Nom: " + rs.getString(2) + ", ");
                System.out.print("Saldo: "+rs.getDouble(3) + ", ");
                System.out.print("Punts: "+ rs.getDouble(4));
                System.out.println();
            }
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException sqlEx) {
                }
                st = null;
            }
        }
		
		//Exercici 5
		
		
		//Exercici 6
		GelatFactory factory = new GelatFactory();
		Gelats tarrina = factory.crearGelat("Tarrina", Sabors.VAINILLA);
		Gelats tarrina1 = new Oreo(tarrina);
		Gelats tarrina2 = new Virutes(tarrina1);
		XaropFactory factory2 = new XaropFactory();
		Xarop xarop = factory2.crearXarop("xarop menta");
		Gelats tarrina3 = new XaropDecorator(tarrina2, xarop);
		System.out.println(tarrina3.getDescripcio());
		System.out.println("Preu del gelat base: " + tarrina3.getPreu());
		Adaptador a;
		if(tarrina3.getIncrement()) {
			tarrina3.setPreu(1.10); 
			System.out.println("Preu total del gelat amb increment: " + tarrina3.getPreu());
			a = new IncrementPreuAdapter(tarrina3.getPreu());
		}
		else
			a = new IncrementPreuAdapter(tarrina3.getPreu());
		
		System.out.println("Preu total del gelat amb IVA: " + a.getPreu());
	}
}
