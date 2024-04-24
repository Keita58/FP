package Main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Objecte.*;

public class Mokepon8 {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Pocio p = new Pocio("Superpocio", 100);
		afegirObjecte(p);
		Reviure r = new Reviure();
		afegirObjecte(r);
		Reviure r2 = new Reviure();
		afegirObjecte(r2);
		Pocio p2 = new Pocio("Pocio", 50);
		afegirObjecte(p2);
		Pocio p3 = new Pocio("Baya", 25);
		afegirObjecte(p3);
//------------------------------------------
		recuperaObjecte();
//------------------------------------------
		recuperaObjectes();
//------------------------------------------
		recuperaPocioConcreta(100);
//------------------------------------------
		Mokepon mekrom = new Mokepon("mekrom", Tipus.FOC);
		MokeponCapturat Mekrom = new MokeponCapturat(mekrom, "mekrom", "Marc");
		afegeixMokepon(Mekrom);
//------------------------------------------
		recuperarMokepon("mekrom", 1, "Marc", "mekrom");
//------------------------------------------
		teamMocketAtacaDeNou();
	}
	
	private static void teamMocketAtacaDeNou() {
		try {
            File f = new File("resources/text/objecte.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            File f2 = new File("resources/text/objecteAux.dat");
            FileOutputStream fos = new FileOutputStream(f2, true);
            AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos, true);
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
					if(o instanceof MokeponCapturat) {
						System.out.println(((MokeponCapturat) o).toString());
					 	((MokeponCapturat) o).setNomEntrenador("Team Mocket");
					 	System.out.println(((MokeponCapturat) o).toString());
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
            System.gc();
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

	public static void recuperarMokepon(String nom, int nivell, String entrenador, String nomDonat) {
		try {
            File f = new File("resources/text/objecte.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            File f2 = new File("resources/text/objecteAux.dat");
            FileOutputStream fos = new FileOutputStream(f2, true);
            AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos, true);
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
					if(o instanceof MokeponCapturat) {
					 	if(((Mokepon) o).getNom().equals(nom) && ((Mokepon) o).getNivell() == nivell && ((MokeponCapturat) o).getNomEntrenador().equals(entrenador) && ((MokeponCapturat) o).nomPosat.equals(nomDonat)) {
					 		System.out.println(((MokeponCapturat) o).toString());
					 	}
					 	else {
			            	System.out.println(o);
					 		oos.writeObject((MokeponCapturat) o);
					 	}
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
	
	public static void afegeixMokepon(MokeponCapturat mok) {
		try {
            File f = new File("resources/text/objecte.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean trobat = false;
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
					if(o instanceof MokeponCapturat) {
					 	if(((MokeponCapturat) o).equals(mok)) {
					 		trobat = true;
					 		System.out.println("El mokepon passat per paràmetre ja es troba actualment en el fitxer.");
					 		break;
					 	}
					}
            	} catch (EOFException eof) {
            		break;
            	} catch (FileNotFoundException e) {
                    System.out.println("no existeix el fitxer");
            	}
            }
            
            if(!trobat) {
                //funciona de forma similar a un fileWriter, amb append inclòs
                FileOutputStream fos = new FileOutputStream(f, true);
                AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos, true);
                System.out.println(mok);
                oos.writeObject(mok);
                System.out.println("Mokepon afegit correctament");
                oos.flush();
                oos.close();
                fos.close();
            }
            
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
	
	public static void recuperaPocioConcreta(int n) {
		try {
            File f = new File("resources/text/objecte.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
	                 
					if(o instanceof Pocio) {
					 	if(((Pocio) o).hp_curada == n) {
					 		System.out.println((Pocio) o);
					 		break;
					 	}
					}
            	} catch (EOFException eof) {
            		System.out.println("Fi de l'arxiu.");
            		break;
            	}
            }
            
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
	
	public static void recuperaObjectes() {
		try {
            File f = new File("resources/text/objecte.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while(true) {
            	try {
	            	Object o = ois.readObject();
	                 
					if(o instanceof Pocio) {
					 	Pocio p = (Pocio) o;
					 	System.out.println(p);
					}
					else if(o instanceof Reviure) {
					 	Reviure r = (Reviure) o;
					 	System.out.println(r);
					}
            	} catch (EOFException eof) {
            		System.out.println("Fi de l'arxiu.");
            		break;
            	}
            }
            
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
	
	public static void recuperaObjecte() {
		try {
            File f = new File("resources/text/objecte.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            while(true) {
            	Object o = ois.readObject();
	            if(o instanceof Pocio) {
	            	Pocio p = (Pocio) o;
	            	System.out.println(p);
	            	break;
	            }
	            else if(o instanceof Reviure) {
	            	Reviure r = (Reviure) o;
	            	System.out.println(r);
	            	break;
	            }
            }

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

	public static void afegirObjecte(Objecte o) {
		try {
            File f = new File("resources/text/objecte.dat");
            //funciona de forma similar a un fileWriter, amb append inclós
            FileOutputStream fos = new FileOutputStream(f, true);
            AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos, true);
            oos.writeObject(o);
            oos.flush();
            oos.close();
            fos.close(); //És molt important tancar bé el fos. Si no el fitxer queda obert
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
