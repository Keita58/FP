package Projecte;

import java.beans.PropertyChangeListener;
import java.sql.*;
import java.util.*;

public class MainSimulation {
    public static void main(String[] args) throws SQLException {

        JocFactory jocFactory;
        ArrayList<Enemic> llistaEnemics = new ArrayList<>();
        ArrayList<Galaxia> llistaGalaxies = new ArrayList<>();
        ArrayList<Bonus> llistaBonus = new ArrayList<>();
        ArrayList<Recurs> llistaRecurs = new ArrayList<>();
        ArrayList<Equipament> llistaEquipaments = new ArrayList<>();

        jocFactory = FactoryProvider.getFactory("Enemic");
        Enemic naucombat = (Enemic) jocFactory.create("Enemic", "Nau de combat");
        Enemic alien = (Enemic) jocFactory.create("Enemic", "Alien");
        Enemic meteorit = (Enemic) jocFactory.create("Enemic", "Meteorit");
        Enemic supernova = (Enemic) jocFactory.create("Enemic", "Supernova");
        llistaEnemics.add(naucombat);
        llistaEnemics.add(alien);
        llistaEnemics.add(meteorit);
        llistaEnemics.add(supernova);

        jocFactory = FactoryProvider.getFactory("Recurs");
        Recurs kebab = (Recurs) jocFactory.create("Recurs", "Kebab");
        Recurs cristall = (Recurs) jocFactory.create("Recurs", "Cristall d'energia");
        Recurs or = (Recurs) jocFactory.create("Recurs", "Or");
        llistaRecurs.add(kebab);
        llistaRecurs.add(cristall);
        llistaRecurs.add(or);

        jocFactory = FactoryProvider.getFactory("Equipament");
        Equipament escut = (Equipament) jocFactory.create("Equipament", "Escut protector");
        Equipament cano = (Equipament) jocFactory.create("Equipament", "Cano");
        Equipament imant = (Equipament) jocFactory.create("Equipament", "Imant magnetic");
        llistaEquipaments.add(escut);
        llistaEquipaments.add(cano);
        llistaEquipaments.add(imant);

        jocFactory = FactoryProvider.getFactory("Bonus");
        Bonus robot = (Bonus) jocFactory.create("Bonus", "Robot reparador");
        Bonus accelerador = (Bonus) jocFactory.create("Bonus", "Accelerador de velocitat");
        Bonus porta = (Bonus) jocFactory.create("Bonus", "Porta espacial");
        llistaBonus.add(robot);
        llistaBonus.add(accelerador);
        llistaBonus.add(porta);

        jocFactory = FactoryProvider.getFactory("Galàxia");
        Galaxia espiral = (Galaxia) jocFactory.create("Galàxia", "Espiral");
        Galaxia irregular = (Galaxia) jocFactory.create("Galàxia", "Irregular");
        Galaxia lenticular = (Galaxia) jocFactory.create("Galàxia", "Lenticular");
        Galaxia eliptica = (Galaxia) jocFactory.create("Galàxia", "El·liptica");
        llistaGalaxies.add(espiral);
        llistaGalaxies.add(irregular);
        llistaGalaxies.add(lenticular);
        llistaGalaxies.add(eliptica);

        Queue<Nau> llistaNaus = new LinkedList<>();
        NauFactory nFactory = new NauFactory();
        RecolectorPunts recolectorPunts1 = new RecolectorPunts();
        RecolectorPunts recolectorPunts2 = new RecolectorPunts();
        RecolectorPunts recolectorPunts3 = new RecolectorPunts();

        llistaNaus.add(nFactory.crearNau(recolectorPunts1, "Nau lleugera", 10.0, "Llança de la llibertat", 1000));
        llistaNaus.add(nFactory.crearNau(recolectorPunts2, "Nau pesada", 50.0, "Escut de la democràcia", 10000));
        llistaNaus.add(nFactory.crearNau(recolectorPunts3, "Nau exploracio", 5.0, "Mapa de les estrelles", 500));

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/videojocjdbc?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM jugador");

            for (Nau nau : llistaNaus) {
                String sqlInsertar = "INSERT INTO jugador (nom, punts, saldo) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sqlInsertar);
                ps.setString(1, nau.getNom());
                ps.setDouble(2, 0);
                ps.setDouble(3, nau.getSaldoRecursos());
                ps.executeUpdate();
            }

            rs = st.executeQuery("SELECT * FROM jugador");
            System.out.println("Naus base: ");
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

        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println("-------------------------------------");
            System.out.println("Bucle " + (i+1));
            int dau = r.nextInt(1,7);
            for(int j = 0; j < dau; j++) {
                llistaNaus.add(llistaNaus.poll());
            }

            int valorRand = r.nextInt(1,101);
            if(valorRand >= 1 && valorRand <= 25) {
                Nau nau = llistaNaus.peek();
                int recursRandom = r.nextInt(0, llistaRecurs.size());
                ObjecteCapturat recurs = new ObjecteCapturat<>(nau, llistaRecurs.get(recursRandom));
                assert nau != null;
                if(nau.getNau() instanceof NauExploracio) {
                    recolectorPunts3.setPunts(recurs);
                }
                else if(nau.getNau() instanceof NauLleugera) {
                    recolectorPunts1.setPunts(recurs);
                }
                else if(nau.getNau() instanceof NauPesada) {
                    recolectorPunts2.setPunts(recurs);
                }
            }
            else if(valorRand >= 26 && valorRand <= 50) {
                Nau nau = llistaNaus.peek();
                int bonusRandom = r.nextInt(0, llistaBonus.size());
                ObjecteCapturat bonus = new ObjecteCapturat(nau, llistaBonus.get(bonusRandom));
                assert nau != null;
                if(nau.getNau() instanceof NauExploracio) {
                    recolectorPunts3.setPunts(bonus);
                }
                else if(nau.getNau() instanceof NauLleugera) {
                    recolectorPunts1.setPunts(bonus);
                }
                else if(nau.getNau() instanceof NauPesada) {
                    recolectorPunts2.setPunts(bonus);
                }
            }
            else if(valorRand >= 50 && valorRand <= 75) {
                Nau nau = llistaNaus.peek();
                int enemicRandom = r.nextInt(0, llistaEnemics.size());
                ObjecteCapturat enemic = new ObjecteCapturat(nau, llistaEnemics.get(enemicRandom));
                assert nau != null;
                if(nau.getNau() instanceof NauExploracio) {
                    recolectorPunts3.setPunts(enemic);
                }
                else if(nau.getNau() instanceof NauLleugera) {
                    recolectorPunts1.setPunts(enemic);
                }
                else if(nau.getNau() instanceof NauPesada) {
                    recolectorPunts2.setPunts(enemic);
                }
            }
            else if(valorRand >= 75 && valorRand <= 100) {
                int decoratorRandom = r.nextInt(1, 4);
                switch (decoratorRandom) {
                    case 1:
                        System.out.println("Equipem un equipament a la nau " + llistaNaus.peek());
                        int equipamentRandom=r.nextInt(0, llistaEquipaments.size());
                        Nau eqd = new EquipamentDecorator(llistaNaus.peek(), llistaEquipaments.get(equipamentRandom));
                        llistaNaus.remove();
                        llistaNaus.add(eqd);
                        break;
                    case 2:
                        System.out.println("Equipem una Galàxia a la nau " + llistaNaus.peek());
                        int galaxiaRandom = r.nextInt(0, llistaGalaxies.size());
                        Nau gd = new GalaxiaDecorator(llistaNaus.peek(), llistaGalaxies.get(galaxiaRandom));
                        llistaNaus.remove();
                        llistaNaus.add(gd);
                        break;
                    case 3:
                        System.out.println("Equipem un color a la nau " + llistaNaus.peek());
                        int colorRandom=r.nextInt(0, ColorEnum.values().length);
                        Nau cd = new Color(llistaNaus.element(), ColorEnum.values()[colorRandom]);
                        llistaNaus.remove();
                        llistaNaus.offer(cd);
                        break;
                }
            }
        }
        System.out.println("-------------------------------------");
        System.out.println();
        System.out.println("Fi simulació");
        System.out.println();
        System.out.println("-------------------------------------");

        for(Nau nau : llistaNaus) {
            if(nau.getNau() instanceof NauExploracio) {
                recolectorPunts3.removePropertyChangeListener((PropertyChangeListener) nau.getNau());
                System.out.println("S'ha eliminat la nau " + nau.getNom() + " de l'observer.");
            }
            else if(nau.getNau() instanceof NauLleugera) {
                recolectorPunts1.removePropertyChangeListener((PropertyChangeListener) nau.getNau());
                System.out.println("S'ha eliminat la nau " + nau.getNom() + " de l'observer");
            }
            else if(nau.getNau() instanceof NauPesada) {
                recolectorPunts2.removePropertyChangeListener((PropertyChangeListener) nau.getNau());
                System.out.println("S'ha eliminat la nau " + nau.getNom() + " de l'observer");
            }
        }

        System.out.println("-------------------------------------");
        System.out.println("Naus abans d'eliminar i actualitzar les corresponents: ");
        for (Nau nausbeforeupdate: llistaNaus){
            System.out.print("Nom: " + nausbeforeupdate.getNau().getNom() + ", ");
            System.out.print("Saldo: " + nausbeforeupdate.getNau().getSaldoRecursos() + ", ");
            System.out.print("Punts: " + nausbeforeupdate.getNau().getPunts());
            System.out.println();
        }


        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/videojocjdbc?" + "user=root&password=super3");
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM jugador");

            for (Nau nau2 : llistaNaus) {
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
                    Iterator<Nau> it = llistaNaus.iterator();
                    while(it.hasNext()) {
                        Nau nau = it.next();
                        if(nau.getNau().getNom().equals(rs.getString("nom"))) {
                            it.remove();
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
        for(Nau nauDelete : llistaNaus) {
            System.out.println("Nom: " + nauDelete.getNau().getNom()+ ", Saldo: " + nauDelete.getNau().getSaldoRecursos()+ ", Punts: " + nauDelete.getNau().getPunts());
        }

        SortedSet<Nau> naves = new TreeSet<>();
        naves.addAll(llistaNaus);

        System.out.println("-------------------------------------");
        System.out.println("Ordenació per nom:");
        for(Nau nau : naves) {
            System.out.println("Nau: "+nau.getNau().getNom()+", Punts: "+nau.getNau().getPunts());
        }

        SortedSet<Nau> naves2 = new TreeSet<>(new PuntsComparator());
        naves2.addAll(naves);

        System.out.println("-------------------------------------");
        System.out.println("Ordenació per punts:");
        for(Nau nau : naves2) {
            System.out.println("Nau: "+nau.getNau().getNom()+", Punts: " +nau.getNau().getPunts());
        }
    }
}
