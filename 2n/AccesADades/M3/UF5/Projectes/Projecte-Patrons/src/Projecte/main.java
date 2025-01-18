package Projecte;
import java.util.*;
import java.sql.*;


public class main {
    public static void main(String[] args) throws SQLException {

        //Punt 1
        RecolectorPunts recolectorPunts = new RecolectorPunts(); //Observer de les naus
        RecolectorPunts recolectorPunts2 = new RecolectorPunts(); //Observer de les naus
        RecolectorPunts recolectorPunts3 = new RecolectorPunts(); //Observer de les naus

        Queue<Nau> llistaNaus = new LinkedList<>();

        NauFactory nFactory = new NauFactory();
        //Creem les naus amb la factory.
        llistaNaus.add(nFactory.crearNau(recolectorPunts, "Nau lleugera", 10.0, "Llança de la llibertat", 1000));
        llistaNaus.add(nFactory.crearNau(recolectorPunts2, "Nau pesada", 50.0, "Escut de la democràcia", 10000));
        llistaNaus.add(nFactory.crearNau(recolectorPunts3, "Nau exploracio", 5.0, "Mapa de les estrelles", 500));

        Iterator<Nau> itNaus = llistaNaus.iterator();
        while(itNaus.hasNext()) {
            Nau nau = itNaus.next();
            System.out.println(nau);
        }

        Iterator<Nau> it = llistaNaus.iterator();
        Nau aux = it.next();

        //Punt 2
        JocFactory jocFactory;


        //Creem un objecte de cada amb l'abstract factory.
        jocFactory = FactoryProvider.getFactory("Enemic");
        Enemic naucombat = (Enemic) jocFactory.create("Enemic", "Nau de combat");
        
        jocFactory = FactoryProvider.getFactory("Recurs");
        Recurs kebab = (Recurs) jocFactory.create("Recurs", "Kebab");
        
        jocFactory = FactoryProvider.getFactory("Equipament");
        Equipament escut = (Equipament) jocFactory.create("Equipament", "Escut protector");

        jocFactory = FactoryProvider.getFactory("Bonus");
        Bonus robot = (Bonus) jocFactory.create("Bonus", "Robot reparador");
        
        jocFactory = FactoryProvider.getFactory("Galàxia");
        Galaxia espiral = (Galaxia) jocFactory.create("Galàxia", "Espiral");
        
        String resultat = "Nau " + naucombat + ", Kebab: " + kebab + ", escut: " + escut + ", robot: " + robot + ", espiral: " + espiral;
        System.out.println(resultat);

        //Punt 3
        System.out.println("Punts del kebab: " + kebab.puntsASumar());
        System.out.println("Punts abans: " + aux.getPunts());

        //La nau es troba amb un recurs (un kebab)
        ObjecteCapturat objc = new ObjecteCapturat(aux, kebab);
        recolectorPunts.setPunts(objc);

        System.out.println("Punts despres: " + aux.getPunts());
        //La nau es troba amb un enemic (una nau de combat)
        ObjecteCapturat objc0 = new ObjecteCapturat(aux, naucombat);
        recolectorPunts.setPunts(objc0);
        System.out.println("Punts despres: " + aux.getPunts());

        //Desubscribim les naus de l'observer.
        for(Nau nau : llistaNaus) {
            if(nau.getNau() instanceof NauExploracio) {
                recolectorPunts3.removePropertyChangeListener((NauExploracio)nau.getNau());
            }
            else if(nau.getNau() instanceof NauLleugera) {
                recolectorPunts.removePropertyChangeListener((NauLleugera)nau.getNau());
            }
            else if(nau.getNau() instanceof NauPesada) {
                recolectorPunts2.removePropertyChangeListener((NauPesada)nau.getNau());
            }
        }

        //Punt 4 i 5
        //Creem un observer nou per la nau que crearem a continuació.
        //Necessitem un de nou perquè si no estaríem cridant a la nau anterior
        RecolectorPunts recolectorPunts4 = new RecolectorPunts();
        Nau aux1 = nFactory.crearNau(recolectorPunts4, "Nau exploracio", 5.0, "Mincron", 100);
        jocFactory = FactoryProvider.getFactory("Equipament");
        Equipament canon = (Equipament) jocFactory.create("Equipament", "Cano");
        Equipament escut1 = (Equipament) jocFactory.create("Equipament", "Escut protector");

        //Afegim decorators a una nau
        Nau n2 = new EquipamentDecorator(aux1, escut1);
        Nau n3 = new Color(n2, ColorEnum.AQUA);
        Nau n4 = new EquipamentDecorator(n3, canon);
        System.out.println(n4.getDescripcio());
        System.out.println(n4.getNau());

        //La nau amb decorators es troba amb un enemic (nau de combat)
        ObjecteCapturat objc1 = new ObjecteCapturat(n4, naucombat);
        recolectorPunts4.setPunts(objc1);

        //Punt 6

        //Creem diferents naus amb decorators i les afegim a una cua.
        RecolectorPunts recolectorPunts5 = new RecolectorPunts();
        Nau aux2 = nFactory.crearNau(recolectorPunts5, "Nau pesada", 500.0, "Rocinante", 80);
        Galaxia lenticular = (Galaxia) jocFactory.create("Galàxia", "Lenticular");
        Nau n5 = new GalaxiaDecorator(aux2, espiral);
        Nau n6 = new EquipamentDecorator(n5, canon);
        Nau n7 = new Color(n6, ColorEnum.HOTPINK);

        RecolectorPunts recolectorPunts6 = new RecolectorPunts();
        Nau aux3 = nFactory.crearNau(recolectorPunts6, "Nau lleugera", 10.0, "Urithiru", 10);
        Nau n8 = new Color(aux3, ColorEnum.FUCHSIA);
        Nau n9 = new EquipamentDecorator(n8, escut1);
        Nau n10 = new GalaxiaDecorator(n9, lenticular);
        Queue<Nau> llistaNausDecorator = new LinkedList<>();

        llistaNausDecorator.add(n4);
        llistaNausDecorator.add(n7);
        llistaNausDecorator.add(n10);

        Connection conn;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/videojocjdbc?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM jugador");
            boolean trobat = false;

            for (Nau nau: llistaNausDecorator) {
                while(rs.next()) {
                    //Mirem que la nau no estigui ja posada a la base de dades.
                    if (!rs.getString("nom").equals(nau.getNau().getNom())) {
                        trobat = true;
                        break;
                    }
                }

                //Si la nau no és a la bd, l'afegim amb un insert.

                if(!trobat) {
                    String sqlInsertar="INSERT INTO jugador (nom, punts, saldo) VALUES (?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sqlInsertar);
                    ps.setString(1, nau.getNau().getNom());
                    ps.setDouble(2, 0);
                    ps.setDouble(3, nau.getNau().getSaldoRecursos());
                    ps.executeUpdate();
                }
                trobat = false;
            }

            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM jugador");

            //Imprimim el resultat del select.
            System.out.println("Naus després de l'insert:");
            while(rs.next()){
                System.out.println("ID: " + rs.getInt("idjugador"));
                System.out.println("Nom: " + rs.getString("nom"));
                System.out.println("Saldo: " + rs.getString("saldo"));
                System.out.println("Punts: " + rs.getString("punts"));
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

        jocFactory = FactoryProvider.getFactory("Enemic");
        Enemic meteorit = (Enemic) jocFactory.create("Enemic", "Meteorit");

        //Funció per variar el número de punts de les naus i que funcioni la funció de la base de dades següent

        for(Nau nau : llistaNausDecorator) {
            ObjecteCapturat objc2 = new ObjecteCapturat(nau, kebab);
            ObjecteCapturat objc3 = new ObjecteCapturat(nau, naucombat);
            ObjecteCapturat objc4 = new ObjecteCapturat(nau, meteorit);
            ObjecteCapturat objc5 = new ObjecteCapturat(nau, robot);
            if(nau.getNau() instanceof NauExploracio) {
                recolectorPunts4.setPunts(objc2);
                recolectorPunts4.setPunts(objc3);
                recolectorPunts4.setPunts(objc4);
                recolectorPunts4.setPunts(objc5);
            }
            else if(nau.getNau() instanceof NauLleugera) {
                recolectorPunts6.setPunts(objc2);
                recolectorPunts6.setPunts(objc3);
                recolectorPunts6.setPunts(objc4);
                recolectorPunts6.setPunts(objc5);
            }
            else if(nau.getNau() instanceof NauPesada) {
                recolectorPunts5.setPunts(objc2);
                recolectorPunts5.setPunts(objc3);
                recolectorPunts5.setPunts(objc4);
                recolectorPunts5.setPunts(objc5);
            }
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/videojocjdbc?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM jugador");

            for (Nau nau2 : llistaNausDecorator) {
                while (rs.next()) {
                    if (rs.getString("nom").equals(nau2.getNau().getNom())) {
                        String updatear = "UPDATE jugador SET punts = ? WHERE nom = ?";
                        PreparedStatement ps = conn.prepareStatement(updatear);
                        ps.setDouble(1, nau2.getNau().getPunts());
                        ps.setString(2, nau2.getNau().getNom());
                        ps.executeUpdate();
                        break;
                    }
                }
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

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/videojocjdbc?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM jugador");

            while (rs.next()) {
                if (rs.getDouble("punts") < 400) {
                    Iterator<Nau> it2 = llistaNausDecorator.iterator();
                    while(it2.hasNext()) {
                        Nau nau = it2.next();
                        if(nau.getNau().getNom().equals(rs.getString("nom"))) {
                            it2.remove();
                            break;
                        }
                    }
                    String borrarSql ="DELETE FROM jugador WHERE nom = ?";
                    PreparedStatement ps = conn.prepareStatement(borrarSql);
                    ps.setString(1, rs.getString("nom"));
                    ps.executeUpdate();
                }
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

        System.out.println("-------------------------------------");
        System.out.println("Naus després d'eliminar i actualitzar les corresponents: ");
        for(Nau nauDelete : llistaNausDecorator) {
            System.out.println("Nom: " + nauDelete.getNau().getNom()+ ", Saldo: " + nauDelete.getNau().getSaldoRecursos()+ ", Punts: " + nauDelete.getNau().getPunts());
        }

        //Afegim una nau nova amb molts punts i amb la lletra A per si quedés alguna nau després d'haver realitzat
        //el delete anterior poder comprovar si es fa l'ordenació tant de punts com de nom correctament.

        RecolectorPunts recolectorPunts7= new RecolectorPunts();
        Nau patata = nFactory.crearNau(recolectorPunts7, "Nau lleugera", 8000, "A", 10);
        llistaNausDecorator.add(patata);
        //Utilitzem un SortedSet perquè els compareTo de les classes de les naus funcionin i s'ordenin les naus pel seu nom
        SortedSet<Nau> naves= new TreeSet<>();
        naves.addAll(llistaNausDecorator);

        System.out.println("-------------------------------------");
        System.out.println("Ordenació per nom:");

        //Mostrem les naus ordenades per nom.
        for(Nau nau : naves) {
            System.out.println("Nau: "+nau.getNom()+", Punts: "+nau.getPunts());
        }

        //Ho posem tot en un SortedSet per poder utilitzar la classe PuntsComparator i es compari per número el SortedSet
        //ordenat anteriorment
        SortedSet<Nau> naves2 = new TreeSet<>(new PuntsComparator());
        naves2.addAll(naves);

        System.out.println("-------------------------------------");
        System.out.println("Ordenació per punts:");

        //Mostrem les naus ordenades per punts i per nom.
        for(Nau nau : naves2) {
            System.out.println("Nau: "+nau.getNom()+", Punts: "+nau.getPunts());
        }
    }
}
