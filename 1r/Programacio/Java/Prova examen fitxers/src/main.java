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
		
		System.out.println(llegirBill().get(0)); //hauria de tornar HumanLeather, Hat, 40
		Bill b1 = new Bill("Steel", "Chair", 25);
		afegirBill(b1);
		System.out.println(cercaBill("Steel","Chair")); //hauria de tornar 25
		System.out.println(cercaBill("Wood","Table")); //hauria de tornar -1
		modificarBill("Wood","Table",40);
		System.out.println(cercaBill("Wood","Table")); //hauria de tornar 40
		MaquinaProduccio m1 = new MaquinaProduccio(0, 5, "Component");
		escriureMaquina(m1);
		MaquinaProduccio m2 = llegirMaquina();
		System.out.println(m2.materialReparacio); //hauria de ser Component
		System.out.println(gastar()); //hauria de ser true
		MaquinaProduccio m3 = llegirMaquina();
		System.out.println(m3.usos); //hauria de ser 1
		Item i1 = new Item("Wood",10,1.16);
		Item i2 = new Item("Steel",200,2.5);
		Item i3 = new Item("Component",30,56.0);
		ArrayList<Item> mag1 = new ArrayList<>();
		mag1.add(i1);mag1.add(i2);mag1.add(i3);
		escriureMagatzem(mag1);
		ArrayList<Item> mag2 = llegirMagatzem();
		System.out.println(mag1.equals(mag2));//hauria de ser true
		Item i4 = new Item("Hat",2,156.0);
		afegirItemAMagatzem(i4.name, 2);
		afegirItemAMagatzem(i4.name, 5);
		System.out.println( llegirMagatzem().get(3));  //hauria de dir Nom Hat, ammount  7
		System.out.println(esborrarItemDeMagatzem(i4.name,10)); //false
		System.out.println(esborrarItemDeMagatzem(i4.name,5)); //true
		System.out.println( llegirMagatzem().get(3));  //hauria de dir Nom Hat, ammount  2
		System.out.println(esborrarItemDeMagatzem(i4.name,2)); //true
		System.out.println( llegirMagatzem().size());  //hauria de dir 3
		System.out.println( craftejar("Steel","Component") ); //true
		System.out.println( llegirMagatzem()); //Steel hauria de ser 184 i Component 31
		System.out.println( craftejar("Wood","Shelf") ); //false
		System.out.println(repararMaquina()); //false
		gastar();
		gastar();
		gastar();
		System.out.println(repararMaquina()); //true
		System.out.println(llegirMaquina()); //usos hauria de ser 1
		System.out.println( llegirMagatzem()); //Component hauria de ser 30
		Item i5 = new ItemOrganic("Corn",10,0.7, false);
		Item i6 = new ItemOrganic("Pemmican",6,2.1, true);
		mag1.add(i6);mag1.add(i5);
		escriureMagatzem(mag1);
		plaga();
		System.out.println( llegirMagatzem()); //Wood 10, Steel 184, Component 30, Corn 5 i Pemmican 6

	}
	
	public static int plaga() {
		
		ArrayList<Item> a = llegirMagatzem();
		int res = 0;
		for(Item i : a) {
			if(i instanceof ItemOrganic) {
				if(!((ItemOrganic) i).preservat) {
					res = i.amount/2;
					esborrarItemDeMagatzem(i.name, i.amount/2);
				}
			}
		}
		return res;
	}
	
	public static boolean repararMaquina() throws IOException {
		
		MaquinaProduccio m = llegirMaquina();
		if(m.usos == m.usosMax) {
			boolean pot = esborrarItemDeMagatzem(m.materialReparacio, 1);
			if(pot) {
				m.usos = 0;
				escriureMaquina(m);
				return true;
			}
			else 
				return false;
		}
		else
			return false;
	}
	
	public static boolean craftejar(String in, String out) throws IOException {
		
		int ammount = cercaBill(in, out);
		
		if(ammount == -1)
			return false;
		
		gastar();
		boolean a = esborrarItemDeMagatzem(in, ammount);
		if (a)
			afegirItemAMagatzem(out, ammount);
		return a;
	}
	
	public static boolean esborrarItemDeMagatzem(String nomItem, int ammount) {
		
		try {
            File f = new File("resources/magatzem.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            File f2 = new File("resources/magatzemAux.dat");
            FileOutputStream fos = new FileOutputStream(f2);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            boolean trobat = false;
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
					if(((Item) o).name.equals(nomItem) && ((Item) o).amount < ammount) {
						return false;
					}
					if(((Item) o).name.equals(nomItem) && ((Item) o).amount > ammount) {
						((Item) o).amount -= ammount;
						oos.writeObject(o);
						trobat = true;
					}
					else if(((Item) o).name.equals(nomItem) && ((Item) o).amount == ammount) {
						trobat = true;
					}
					else
						oos.writeObject(o);
	            	
            	} catch (EOFException eof) {
            		break;
            	} catch (FileNotFoundException e) {
                    System.out.println("no existeix el fitxer");
            	}
            }
            
            if(!trobat) {
            	f2.delete();
            	return false;
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
		return true;
	}
	
	public static int afegirItemAMagatzem(String nomItem, int ammount) {
		
		int ret = ammount;
		try {
            File f = new File("resources/magatzem.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            File f2 = new File("resources/magatzemAux.dat");
            FileOutputStream fos = new FileOutputStream(f2);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            boolean trobat = false;
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
					if(((Item) o).name.equals(nomItem)) {
						((Item) o).amount += ammount;
						ret = ((Item) o).amount;
						trobat = true;
					}
	            	oos.writeObject(o);
            	} catch (EOFException eof) {
            		break;
            	} catch (FileNotFoundException e) {
                    System.out.println("no existeix el fitxer");
            	}
            }
            
            if(!trobat) {
            	Item aux = new Item(nomItem, ammount, 0);
            	oos.writeObject(aux);
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
		return ret;
	}
	
	public static ArrayList<Item> llegirMagatzem() {
		
		ArrayList<Item> items = new ArrayList<>();
		try {
            File f = new File("resources/magatzem.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
		            items.add((Item) o);
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
		return items;
	}
	
	public static void escriureMagatzem(ArrayList<Item> magatzem) {
		
		try {
            File f = new File("resources/magatzem.dat");
            //funciona de forma similar a un fileWriter, amb append inclós
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
        	for(int i = 0; i < magatzem.size(); i++)
        		oos.writeObject(magatzem.get(i));
           
            oos.flush();
            oos.close();
            fos.close(); //És molt important tancar bé el fos. Si no el fitxer queda obert
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static boolean gastar() throws IOException {
		
		MaquinaProduccio m = llegirMaquina();
		m.usos++;
		if(m.usos > m.usosMax)
			return false;
		else {
			escriureMaquina(m);
			return true;
		}
	}
	
	public static MaquinaProduccio llegirMaquina() throws IOException {
	
		File f = new File("resources/maquina.bin");
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        MaquinaProduccio m = new MaquinaProduccio(ois.readInt(), ois.readInt(), ois.readUTF());
        
        ois.close();
        return m;
	}
	
	public static void escriureMaquina(MaquinaProduccio mp) {
		
		try {
			File f = new File("resources/maquina.bin");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeInt(mp.usos);
            oos.writeInt(mp.usosMax);
            oos.writeUTF(mp.materialReparacio);
            
            oos.close();
			
		} catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
	    } catch (IOException e) {
            System.out.println("Excepció general de lectura/escriptura");
            e.printStackTrace();
	    }
	}
	
	public static void modificarBill(String itemIn, String itemOut, int ammount) {
		
		try {
			File f = new File("resources/bills.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr); 
            
            File f2 = new File("resources/billsTemp.txt");
            FileWriter fw = new FileWriter(f2, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            while(br.ready()) {
            	String a = br.readLine();
            	String[] aux = a.split(";");
            	if(aux[0].equals(itemIn) && aux[2].equals(ammount+"")) { // Si s'afegeix el +"" es transforma l'int a un string
            		aux[1] = itemOut;
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
                        
		} catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
	    } catch (IOException e) {
            System.out.println("Excepció general de lectura/escriptura");
            e.printStackTrace();
	    }
	}
	
	public static void afegirBill(Bill bill) {
		
		try {
            File f = new File("resources/bills.txt");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);  
            
            //FUNCIONS IMPORTANTS DEL BUFFEREDWRITER
            //append. Escriu al buffer intern. No escriu al fitxer fins que fas un flush
            bw.append(bill.in+";"+bill.out+";"+bill.inAmmount+"\n");

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
	
	public static int cercaBill(String itemIn, String itemOut) {
		
		try {
            File f = new File("resources/bills.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);            
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	if(aux[0].equals(itemIn) && aux[1].equals(itemOut)) {
            		return Integer.parseInt(aux[2]);
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
	
	public static ArrayList<Bill> llegirBill() {
		
		ArrayList<Bill> ret = new ArrayList<>();
		try {
			File f = new File("resources/bills.txt");
			FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);  
            
            while(br.ready()) {
            	String[] a = br.readLine().split(";");
				Bill aux = new Bill(a[0], a[1], Integer.parseInt(a[2]));
				ret.add(aux);
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
