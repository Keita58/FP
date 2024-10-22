package Classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PriorityQueue;
import java.util.Queue;

public class MainExamen {
    public static void main(String[] args) {
        
        Queue<Missio> missions = new PriorityQueue<>();
        Missio m1 = new Missio("Rita repulsa", "Primera missió important", 2);
        Missio m2 = new Missio("Lord Zedd", "Missió ultra important!!!", 1);
        Missio m3 = new Missio("Goldar", "Missió no tant important", 3);

        missions.add(m1);
        missions.add(m3);
        missions.add(m2);

        while(!missions.isEmpty()) {
            System.out.println(missions.poll());
        }

        System.out.println();
        System.out.println("Afegim una missió nova");
        System.out.println();

        Missio m4 = new Missio("Eloi", "Examen de l'Eloi :(", 1);

        missions.add(m1);
        missions.add(m3);
        missions.add(m2);
        missions.add(m4);

        while(!missions.isEmpty()) {
            System.out.println(missions.poll());
        }

        System.out.println("-------------------------------------------");

        //*Les tornem a afegir per la base de dades
        missions.add(m1);
        missions.add(m3);
        missions.add(m2);
        missions.add(m4);

        Connection conn;
        Statement st = null;
        ResultSet rs = null;
        

        //Afegir missions a la base de dades
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/powerchamber?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM missions");

            while(!missions.isEmpty()) {
                String sqlInsertar = "INSERT INTO missions (nomEnemic, descripcio, urgencia) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sqlInsertar);
                Missio aux = missions.poll();
                ps.setString(1, aux.getNomEnemic());
                ps.setString(2, aux.getDescripcio());
                ps.setInt(3, aux.getUrgencia());
                ps.executeUpdate();
            }

            rs = st.executeQuery("SELECT * FROM missions");
            System.out.println("Missions guardades: ");
            while (rs.next()){
                System.out.print("Nom de l'enemic: " + rs.getString(3) + ", ");
                System.out.print("Descripció de la missió: "+rs.getString(2) + ", ");
                System.out.print("Urgència: "+ rs.getInt(4));
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

        //Eliminar missions que no tinguin descripció
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/powerchamber?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM missions");

            while (rs.next()) {
                if (rs.getString("descripcio") == null) {
                    String borrarSql ="DELETE FROM missions WHERE idmissions = ?";
                    PreparedStatement ps = conn.prepareStatement(borrarSql);
                    ps.setString(1, rs.getString("idmissions"));
                    ps.executeUpdate();
                }
            }

            rs = st.executeQuery("SELECT * FROM missions");
            System.out.println("Missions guardades després d'eliminar les que no tenen descripció: ");
            while (rs.next()){
                System.out.print("Nom de l'enemic: " + rs.getString(3) + ", ");
                System.out.print("Descripció de la missió: "+rs.getString(2) + ", ");
                System.out.print("Urgència: "+ rs.getInt(4));
                System.out.println();
            }
        }
        catch(SQLException ex){
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

        //Canviar urgència de les que no tenen enemics
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/powerchamber?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM missions");

            while (rs.next()) {
                if (rs.getString("nomEnemic") == null) {
                    String borrarSql ="UPDATE missions SET urgencia = ? WHERE idmissions = ?";
                    PreparedStatement ps = conn.prepareStatement(borrarSql);
                    ps.setInt(1, 4);
                    ps.setString(2, rs.getString("idmissions"));
                    ps.executeUpdate();
                }
            }
            
            rs = st.executeQuery("SELECT * FROM missions");
            System.out.println("Missions guardades després d'actualitzar les missions sense enemics: ");
            while (rs.next()){
                System.out.print("Nom de l'enemic: " + rs.getString(3) + ", ");
                System.out.print("Descripció de la missió: "+rs.getString(2) + ", ");
                System.out.print("Urgència: "+ rs.getInt(4));
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
        
        EnemicsFactory crearEnemics = new EnemicsFactory();
        
        PowerRanger maurici = new PowerRanger("Moruci'ya", "Tengen", Color.VERMELL, 2.0);
        Enemics rita = crearEnemics.crearEnemic(TipusEnemics.RitaRepulsa, "Rita", maurici);        
        PowerRanger erpiem = new PowerRanger("Erpiem", "EVA", Color.BLAU, 1.5);
        Enemics zedd = crearEnemics.crearEnemic(TipusEnemics.LordZedd, "Zedd", erpiem);
        System.out.println();

        rita.atacar();
        zedd.atacar();
        Arma espasa = new Arma(maurici);
        System.out.println(espasa.equipar());
        Mecha eva = new Mecha(espasa);
        System.out.println(eva.equipar());
        System.out.println(eva.getPoder());
    }
}
