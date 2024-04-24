import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Mokepon7 {

	public static void main(String[] args) throws Exception {
//		afegirGimnas("Gimnàs de Morredembarra", "Morredembarra", "Arnau");
//		afegirGimnas("Gimnàs de Mabanell", "Mabadell", "Ash");
//		afegirGimnas("Gimnàs de Marberà del Mallès", "Marmerà del Mallès", "Amelia");
//		afegirGimnas("Gimnàs de Mantmenat", "Mantmenat", "Héctor");
//		mostraGimnasos();
//		cercaLider("Gimnàs de Marberà del Mallès");
//		invictes(2);
		copiaSeguretat("resources/text/gimnasos.txt", "resources/text/gimnasosCopia.txt");
//		canviLider("Gimnàs de Marberà del Mallès", "Marc");
//		afegeixEntrenador("Gimnàs de Mabanell", "Ruri");
//		afegeixEntrenador("Gimnàs de Mabanell", "Peco");
//		esborraGimnas("Gimnàs de Morredembarra");
//		consultaEntrenadors("Gimnàs de Mabanell");
//		afegirGimnas("Gimnàs de Mabanell", "Mabadell", "Ash");
	}
	
	public static void afegirGimnas(String nomGimnas, String ciutat, String liderGimnas) throws Exception {
		try {
            File f = new File("resources/text/gimnasos.txt");
            FileWriter fw;
            fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);  
            
            //FUNCIONS IMPORTANTS DEL BUFFEREDWRITER
            //append. Escriu al buffer intern. No escriu al fitxer fins que fas un flush
            if(!buscaGimnas(nomGimnas))
            	bw.append(nomGimnas+";"+ciutat+";"+liderGimnas+";"+"0\n");
            else
            	throw new Exception("Ja existeix un gimnàs amb aquest nom");

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
	
	public static void mostraGimnasos() {
		try {
            File f = new File("resources/text/gimnasos.txt");
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);            
            System.out.println("Reader Carregat Correctament (llegint per mostrar gimnasos)");     
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	System.out.println(aux[0]);
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Excepció general de lectura");
            e.printStackTrace();
        }
	}
	
	public static void cercaLider(String nomGimnas) {
		try {
            File f = new File("resources/text/gimnasos.txt");
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);            
            System.out.println("Reader Carregat Correctament (llegint per cercar el líder de gimnàs)");     
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	if(aux[0].equals(nomGimnas))
            		System.out.println(aux[2]);
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Excepció general de lectura");
            e.printStackTrace();
        }
	}
	
	public static void invictes(int n) {
		try {
            File f = new File("resources/text/gimnasos.txt");
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);            
            System.out.println("Reader Carregat Correctament (llegint per buscar els gimnasos invictes)");     
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	if(Integer.parseInt(aux[3]) < n)
            		System.out.println(aux[0]);
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Excepció general de lectura");
            e.printStackTrace();
        }
	}
	
	public static void copiaSeguretat(String path1, String path2) {
		try {
			File f = new File(path1);
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr); 
            
            File f2 = new File(path2);
            FileWriter fw;
            fw = new FileWriter(f2, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            while(br.ready()) {
            	bw.append(br.readLine()+"\n");
            }
            bw.flush();
            br.close();        
            bw.close();
            
            System.out.println("Còpia de fitxer creada satisfactòriament");
            
		} catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
	    } catch (IOException e) {
            System.out.println("Excepció general de lectura/escriptura");
            e.printStackTrace();
	    }
	}
	
	public static void canviLider(String nomGimnas, String nouLider) {
		try {
			File f = new File("resources/text/gimnasos.txt");
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr); 
            
            File f2 = new File("resources/text/gimnasosTemp.txt");
            FileWriter fw;
            fw = new FileWriter(f2, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	if(aux[0].equals(nomGimnas)) {
            		for(int i = 0; i < aux.length - 1; i++) {
            			if(i == 2)
            				bw.append(nouLider+";");
            			else
            				bw.append(aux[i]+";");
            		}
            		bw.append(aux[aux.length - 1]+"\n");
            	}
            	else {
            		for(int i = 0; i < aux.length - 1; i++)
            			bw.append(aux[i]+";");
            		bw.append(aux[aux.length - 1]+"\n");
            	}
            }
            bw.flush();
            br.close();        
            bw.close();
            
            f.delete();
            File faux = new File("resources/text/gimnasos.txt");
	        //reanomenar. Reanomenes f a f2. És a dir, poses els continguts de f i el nom de f2
	        f2.renameTo(faux);
            
            System.out.println("Canvi d'entrenador fet satisfactòriament");
            
		} catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
	    } catch (IOException e) {
            System.out.println("Excepció general de lectura/escriptura");
            e.printStackTrace();
	    }
	}
	
	public static void afegeixEntrenador(String nomGimnas, String nomEntrenador) {
		try {
			File f = new File("resources/text/gimnasos.txt");
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr); 
            
            File f2 = new File("resources/text/gimnasosTemp.txt");
            FileWriter fw;
            fw = new FileWriter(f2, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	if(aux[0].equals(nomGimnas)) {
            		aux[3] = Integer.toString(Integer.parseInt(aux[3])+1);
            		for(int i = 0; i < aux.length; i++)
            			bw.append(aux[i]+";");
            		bw.append(nomEntrenador+"\n");
            	}
            	else {
            		for(int i = 0; i < aux.length - 1; i++)
            			bw.append(aux[i]+";");
            		bw.append(aux[aux.length - 1]+"\n");
            	}
            }
            bw.flush();
            br.close();        
            bw.close();
            
            f.delete();
            File faux = new File("resources/text/gimnasos.txt");
	        //reanomenar. Reanomenes f a f2. És a dir, poses els continguts de f i el nom de f2
	        f2.renameTo(faux);
            
            System.out.println("Entrenador guanyador afegit satisfactòriament");
            
		} catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
	    } catch (IOException e) {
            System.out.println("Excepció general de lectura/escriptura");
            e.printStackTrace();
	    }
	}
	
	public static void esborraGimnas(String nomGimnas) {
		try {
			File f = new File("resources/text/gimnasos.txt");
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr); 
            
            File f2 = new File("resources/text/gimnasosTemp.txt");
            FileWriter fw;
            fw = new FileWriter(f2, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	if(!aux[0].equals(nomGimnas)) {
            		for(int i = 0; i < aux.length - 1; i++)
            			bw.append(aux[i]+";");
            		bw.append(aux[aux.length - 1]+"\n");
            	}
            }
            bw.flush();
            br.close();        
            bw.close();
            
            f.delete();
            File faux = new File("resources/text/gimnasos.txt");
	        //reanomenar. Reanomenes f a f2. És a dir, poses els continguts de f i el nom de f2
	        f2.renameTo(faux);
            
            System.out.println("Gimnàs esborrat satisfactòriament");
            
		} catch (FileNotFoundException e) {
            System.out.println("El fitxer no existeix");
            e.printStackTrace();
	    } catch (IOException e) {
            System.out.println("Excepció general de lectura/escriptura");
            e.printStackTrace();
	    }
	}
	
	public static void consultaEntrenadors(String nomGimnas) {
		try {
            File f = new File("resources/text/gimnasos.txt");
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);            
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	if(aux[0].equals(nomGimnas)) {
            		if(Integer.parseInt(aux[3]) > 0)
	            		for(int i = 4; i < aux.length; i++)
	            			System.out.println(aux[i]);
            		else
            			System.out.println("Cap entrenador ha superat aquest gimnàs");
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
	}
	
	public static boolean buscaGimnas(String nomGimnas) {
		try {
            File f = new File("resources/text/gimnasos.txt");
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);            
            
            while(br.ready()) {
            	String[] aux = br.readLine().split(";");
            	if(aux[0].equals(nomGimnas)) {
            		return true;
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
		return false;
	}
}
