package a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) throws IOException {
		
		System.out.println(equippedStratagems().get(0) + " hauria de ser eagle strike");
        System.out.println(cercaStratagem("Orbital Laser") + " hauria de ser -1");
        Stratagem orb = new Stratagem("Orbital Laser",7500,false);
        afegirStratagem(orb);
        System.out.println(cercaStratagem("Orbital Laser") + " hauria de ser 7500");
        System.out.println(equiparStratagem("Orbital Laser") + " hauria de ser false");
        System.out.println(equiparStratagem("Eagle Strike") + " hauria de ser true");
        System.out.println(equiparStratagem("Orbital Laser") + " hauria de ser true");
        System.out.println(equippedStratagems().get(0) + " hauria de ser 500 KT Bomb");
        System.out.println(equippedStratagems().get(3) + " hauria de ser Orbital Laser");

        SuperEarthShip s1 = new SuperEarthShip("Patriota del Patriotismo", 2500, 1);
        escriureShip(s1);
        System.out.println(reforcar() + "hauria de ser true");
        System.out.println(reforcar() + "hauria de ser true");
        System.out.println(reforcar() + "hauria de ser false");
        Helldiver h1 = new Helldiver("Siddy",100,true,true,equippedStratagems());
        Helldiver h2 = new Helldiver("Motomongo",100,true,true,equippedStratagems());
        Helldiver h3 = new Helldiver("Drein",100,false,true,equippedStratagems());

        ArrayList<Helldiver> hell = new ArrayList<>();
        hell.add(h1);hell.add(h2);hell.add(h3);

        escriureMissio(hell);
        System.out.println(llegirHelldivers().get(0) + "hauria de ser Siddy");
        canviarHPHelldiver("Motomongo",-150);
        System.out.println(llegirHelldivers().get(1) + "Motomongo hauria de tenir la vida a 0 i mort");
        canviarHPHelldiver("Motomongo",10);
        System.out.println(llegirHelldivers().get(1) + "Motomongo hauria de tenir la vida a 10 i viu");
        Helldiver critic = HellDiverCritic();
        System.out.println(critic + "Hauria de ser Motomongo");
        System.out.println(oficialDemocracia()+" hauria de ser 1");
        System.out.println(llegirHelldivers().size()+" hauria de ser 2");

        System.out.println(atacInsectesFascistes(20)+" hauria de ser true");
        s1 = new SuperEarthShip("Patriota del Patriotismo", 500, 1);
        System.out.println(atacInsectesFascistes(100)+" hauria de ser true");
        System.out.println(atacInsectesFascistes(100)+" hauria de ser false");
        canviarHPHelldiver("Motomongo",10);
        canviarHPHelldiver("Siddy",5);
        siJoMoroVosaltresTambe();
        System.out.println(llegirHelldivers().get(0) + "Siddy hauria d'estar mort");
        System.out.println(equippedStratagems()+"haurien d'estar desequipats els de valor inferior a 5000");


        Arma ar1 = new Arma("Dominator",2000,2.5);
        equipar(ar1);
        Stratagem st1 = new Stratagem("Patriot Exosuit",4000,false);
        equipar(st1);
        //feu vosaltres lo d'afegir les armes i els estratagemes
        rebaixa(50,25);
        //recupereu l'arma i l'estratagema
        agafar();
	}
	
	private static void agafar() {
		
		try {
            File f = new File("resources/tenda.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while(true) {
            	try {
            		Object o = ois.readObject();
            		if(o instanceof Arma) {
						System.out.println((Arma) o);
					}
					else if(o instanceof Stratagem) {
						System.out.println((Stratagem) o);
					}
            	} catch (EOFException eof) {
            		break;
            	} 
            }
           
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("no existeix el fitxer");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("excepció d'entrada/sortida");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void equipar(Stratagem st1) {
		
		try {
            File f = new File("resources/tenda.dat");
            File f2 = new File("resources/tendaAux.dat");
            FileOutputStream fos = new FileOutputStream(f2, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
           
            oos.writeObject(st1);
            oos.flush();
            oos.close();
            fos.close();
            //System.gc(); // Això està perquè sinó java no ens deixa eliminar l'arxiu per poder-lo reemplaçar amb el nou. Extret de <<https://stackoverflow.com/a/4213208>>
            f.delete();
            f2.renameTo(f);
        } catch (FileNotFoundException e) {
            System.out.println("no existeix el fitxer");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("excepció d'entrada/sortida");
            e.printStackTrace();
        }
	}

	public static void equipar(Arma ar1) {
		
		try {
            File f = new File("resources/tenda.dat");
            File f2 = new File("resources/tendaAux.dat");
            FileOutputStream fos = new FileOutputStream(f2, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            
            oos.writeObject(ar1);
            oos.flush();
            oos.close();
            fos.close();
            //System.gc(); // Això està perquè sinó java no ens deixa eliminar l'arxiu per poder-lo reemplaçar amb el nou. Extret de <<https://stackoverflow.com/a/4213208>>
            f.delete();
            f2.renameTo(f);
        } catch (FileNotFoundException e) {
            System.out.println("no existeix el fitxer");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("excepció d'entrada/sortida");
            e.printStackTrace();
        }
	}
	
	public static void rebaixa(int rebaixaStratagem, int rebaixaArma) {
		
		try {
            File f = new File("resources/tenda.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            File f2 = new File("resources/tendaAux.dat");
            FileOutputStream fos = new FileOutputStream(f2);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
					if(o instanceof Arma) {
						((Arma) o).price = (((Arma) o).price*rebaixaArma)/100;
					}
					else if(o instanceof Stratagem) {
						((Stratagem) o).price = (((Stratagem) o).price*rebaixaStratagem)/100;
					}
	            	oos.writeObject(o);
            	} catch (EOFException eof) {
            		break;
            	} catch (FileNotFoundException e) {
                    System.out.println("no existeix el fitxer");
            	}
            }
          
            oos.flush();
            oos.close();
            fos.close();
            ois.close();
            fis.close();
            System.gc(); // Això està perquè sinó java no ens deixa eliminar l'arxiu per poder-lo reemplaçar amb el nou. Extret de <<https://stackoverflow.com/a/4213208>>
            f.delete();
            f2.renameTo(f);
        } catch (FileNotFoundException e) {
            System.out.println("no existeix el fitxer");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("excepció d'entrada/sortida");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("no s'ha trobat la classe demanada");
            e.printStackTrace();
        }
	}
	
	public static void siJoMoroVosaltresTambe() throws IOException {
		
		Helldiver h = HellDiverCritic();
		System.out.println(h.name);
		ArrayList<Stratagem> s = h.stratagems;
		
		for(int i = 0; i < s.size(); i++) {
			if(cercaStratagem(s.get(i).name) < 5000)
				equiparStratagem(s.get(i).name);
		}
		
		canviarHPHelldiver(h.name, -1000);
	}
	
	public static boolean atacInsectesFascistes(int atacRebut) throws IOException {
		
		ArrayList<Helldiver> a = llegirHelldivers();
		int count = 0;
		
		for(Helldiver h : a) {
			canviarHPHelldiver(h.name, -atacRebut);
			if(!h.alive)
				if(reforcar())
					canviarHPHelldiver(h.name, 100);
				else
					count++;
		}
		
		if(a.size() > count)
			return true;
		else
			return false;
	}
	
	public static int oficialDemocracia() {
		
		ArrayList<Helldiver> a = llegirHelldivers();
		ArrayList<Helldiver> anti = new ArrayList<>();
		ArrayList<Helldiver> pro = new ArrayList<>();
		
		for (Helldiver h : a) {
			if(!h.democracy) {
				anti.add(h);
			}
			else
				pro.add(h);
		}
		
		escriureMissio(pro);
		return anti.size();
	}
	
	public static int canviarHPHelldiver(String nomHelldiver, int hp){
		
		int hpReturn = 0;
		try {
			File f = new File("resources/missio.dat");
	        FileInputStream fis = new FileInputStream(f);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        
	        File f2 = new File("resources/missioAux.dat");
	        FileOutputStream fos = new FileOutputStream(f2);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        
	        while(true) {
	        	Object o;
				try {
					o = ois.readObject();
					if(((Helldiver) o).name.equals(nomHelldiver)) {
		        		((Helldiver) o).hp += hp;
			        	if(((Helldiver) o).hp <= 0) {
			        		((Helldiver) o).hp = 0;
			        		((Helldiver) o).alive = false;
			        	}
			        	else {
			        		((Helldiver) o).alive = true;
			        		if(((Helldiver) o).hp > 100)
			        			((Helldiver) o).hp = 100;
			        	}
		        		hpReturn = ((Helldiver) o).hp;
		        		oos.writeObject(o);
					}
					else
						oos.writeObject(o);
				} catch (EOFException eof) {
	        		break;
	        	} catch (FileNotFoundException e) {
	                System.out.println("no existeix el fitxer");
	        	}
	        }
	        
	        oos.flush();
            oos.close();
            fos.close();
            ois.close();
            fis.close();
            System.gc(); // Això està perquè sinó java no ens deixa eliminar l'arxiu per poder-lo reemplaçar amb el nou. Extret de <<https://stackoverflow.com/a/4213208>>
            f.delete();
            f2.renameTo(f);
		}
		catch (FileNotFoundException e) {
            System.out.println("no existeix el fitxer");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("excepció d'entrada/sortida");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("no s'ha trobat la classe demanada");
            e.printStackTrace();
        }
        
        return hpReturn;
	}
	
	public static Helldiver HellDiverCritic() {
		
		 ArrayList<Helldiver> a = llegirHelldivers();
		 Helldiver minim = new Helldiver("a", 1000, false, false, null);
		 
		 for(Helldiver h : a) {
			 if(h.hp < minim.hp)
				 minim = h;
		 }
		 return minim;
	}
	
	public static ArrayList<Helldiver> llegirHelldivers() {
		
		ArrayList<Helldiver> helldivers = new ArrayList<>();
		try {
            File f = new File("resources/missio.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
		            helldivers.add((Helldiver) o);
            	} catch (EOFException eof) {
            		break;
            	}
            }
            ois.close();
            fis.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("no existeix el fitxer");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("excepció d'entrada/sortida");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("no s'ha trobat la classe demanada");
            e.printStackTrace();
        }
		return helldivers;
	}
	
	public static void escriureMissio(ArrayList<Helldiver> helldivers) {
		
		try {
            File f = new File("resources/missio.dat");
            //funciona de forma similar a un fileWriter, amb append inclós
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
        	for(int i = 0; i < helldivers.size(); i++)
        		oos.writeObject(helldivers.get(i));
           
            oos.flush();
            oos.close();
            fos.close(); //És molt important tancar bé el fos. Si no el fitxer queda obert
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static boolean reforcar() throws IOException {
		
		SuperEarthShip a = llegirShip();
		if(a.reinforcements > 0) {
			a.reinforcements--;
			escriureShip(a);
			return true;
		}
		else if(a.reinforcements == 0 && a.credits > 2000) {
			a.credits -= 2000;
			escriureShip(a);
			return true;
		}
		else
			return false;
	}
	
	public static SuperEarthShip llegirShip() throws IOException {
		
		File f = new File("resources/ship.bin");
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        SuperEarthShip m = new SuperEarthShip(ois.readUTF(), ois.readInt(), ois.readInt());
        
        ois.close();
        return m;
	}
	
	public static void escriureShip(SuperEarthShip s) {
		
		try {
			File f = new File("resources/ship.bin");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeUTF(s.name);
            oos.writeInt(s.credits);
            oos.writeInt(s.reinforcements);
            
            oos.close();
			
		} catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
	    } catch (IOException e) {
            System.out.println("Excepció general de lectura/escriptura");
            e.printStackTrace();
	    }
	}
	
	public static boolean equiparStratagem(String nom) throws IOException {
		
		ArrayList<Stratagem> stratagems = new ArrayList<>();
		stratagems = equippedStratagems();
		//System.out.println(stratagems.size());
		boolean trobat = false;
		boolean retorn = false;
		
		for(Stratagem a : stratagems) {
			if(a.name.equals(nom)) {
				//System.out.println(a.name);
				trobat = true;
				break;
			}
		}
		
		File f = new File("resources/stratagem.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr); 
        
        File f2 = new File("resources/stratagemTemp.txt");
        FileWriter fw = new FileWriter(f2, true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        while(br.ready()) {
        	String a = br.readLine();
        	String[] aux = a.split(";");
        	if(aux[0].equals(nom)) { // Si s'afegeix el +"" es transforma l'int a un string
        		if(trobat) {
        			aux[2] = "false";
        			retorn = true;
        		}
        		else {
        			if(stratagems.size() < 4) {
        				aux[2] = "true";
            			retorn = true;
        			}
        			else
        				retorn = false;
        		}
        		bw.append(aux[0]+";"+aux[1]+";"+aux[2]+"\n");
        	}
        	else {
        		bw.append(a+"\n");
        	}
        }
        bw.flush();
        br.close();        
        bw.close();
        System.gc();
        f.delete();
        f2.renameTo(f);
		
        return retorn;
	}
	
	public static void afegirStratagem(Stratagem nouStratagem) {
		
		try {
            File f = new File("resources/stratagem.txt");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);  
            
            //FUNCIONS IMPORTANTS DEL BUFFEREDWRITER
            //append. Escriu al buffer intern. No escriu al fitxer fins que fas un flush
            bw.append(nouStratagem.name+";"+nouStratagem.price+";"+nouStratagem.equipped+"\n");

            //Guarda tots els canvis que has fet al buffer intern. Escriu a fitxer
            bw.flush();            
            //close. Tanca el bufferedWriter i evita que quedi la memòria penjant
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Excepció general d'escriptura");
            e.printStackTrace();
        }
	}
	
	public static int cercaStratagem(String nomStratagem) {
		
		try {
            File f = new File("resources/stratagem.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);            
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	if(aux[0].equals(nomStratagem)) {
            		return Integer.parseInt(aux[1]);
            	}
            }
            
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Excepció general de lectura");
            e.printStackTrace();
        }
		
		return -1;
	}
	
	public static ArrayList<Stratagem> equippedStratagems() {
		
		ArrayList<Stratagem> ret = new ArrayList<>();
		try {
			File f = new File("resources/stratagem.txt");
			FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);  
            
            while(br.ready()) {
            	String[] a = br.readLine().split(";");
            	if(a[2].equals("true")) {
            		Stratagem aux = new Stratagem(a[0], Integer.parseInt(a[1]), true);
            		ret.add(aux);
            	}
            }
			br.close();
		} catch (FileNotFoundException e) {
            System.out.println("no existeix el fitxer");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("excepció d'entrada/sortida");
            e.printStackTrace();
        }
		
		return ret;
	}
	
}
