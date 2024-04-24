package Main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import Objecte.*;

public class Test {
    
    public static void main(String[] args) {
        
        Mokepon mikachu = new Mokepon();
        
        mikachu.setNom("Mikachu");
        System.out.println(mikachu.getNom());
        
        mikachu.otorgarExp(100);
        System.out.println(mikachu.getExp() + " " + mikachu.getNivell());
        
        Mokepon missingNo = new Mokepon();
        System.out.println(missingNo.getNom());
        Mokepon marmander = new Mokepon("Marmander", Tipus.FOC);
        System.out.println(marmander.getNom()+" "+marmander.getAtk());
        Mokepon mulmasaur = new Mokepon("Mulmasaur", Tipus.AIGUA, 5);  
        System.out.println(mulmasaur.getNom()+" "+mulmasaur.getAtk());
        
        Atac a = new Atac("Pistola aigua", Tipus.AIGUA);
        mulmasaur.afegirAtac(a);
        Atac b = new Atac("Surf", 80, Tipus.AIGUA, 10);
        mulmasaur.afegirAtac(b);
        
        Atac c = new Atac("Flamarada", Tipus.FOC);
        marmander.afegirAtac(c);
        
        mulmasaur.atacar(marmander, 1);
        System.out.println("Marmander té " + marmander.getHp_actual() + " hp");
        
        marmander.atacar(mulmasaur, 0);
        System.out.println("Mulmasaur té " + mulmasaur.getHp_actual() + " hp");

        MokeponCapturat Mobbuffet = new MokeponCapturat("Mobbuffet", Tipus.AIGUA);
        Mobbuffet.afegirAtac(a);
        
        try {
            Mobbuffet.capturar("Marc", "Mobbuffet");
        } catch (MokeponJaCapturatException e) {
            e.printStackTrace();
        }
        
        System.out.println(Mobbuffet.nombreMokeponsCapturats);

        try {
            mikachu.capturar("Marc", "Mikachu");
        } catch (MokeponJaCapturatException e) {
            e.printStackTrace();
        }

        MokeponCapturat mo = capturar(marmander, "Marc", "Mo");

        System.out.println(mo.nomPosat);
        
        System.out.println(mulmasaur);
        System.out.println(mo);
        
        Arma AK47 = new Arma("AK47");
        System.out.println("Atac de mo: " + mo.getAtk());
        
        AK47.equipar(mo);
        
        System.out.println("Atac de mo: " + mo.getAtk());
        
        System.out.println(Mobbuffet.nombreMokeponsCapturats);
        
        System.out.println(Mobbuffet);
        Mobbuffet.otorgarExp(300);
        System.out.println(Mobbuffet);
        
        mulmasaur.atacar(Mobbuffet, 1);
        System.out.println("Mobbuffet té " + Mobbuffet.getHp_actual() + " hp");
        
        ObjecteFactory z = new ObjecteFactory();
        Objecte reviure = z.crearObjecte("REVIURE");
        Mobbuffet.objecte = reviure;
        System.out.println(Mobbuffet.objecteEquipat());
        Mobbuffet.utilitzaObjecte();
        System.out.println("Mobbuffet té " + Mobbuffet.getHp_actual() + " hp");
        System.out.println(Mobbuffet.objecteEquipat());
        
        Objecte poti = z.crearObjecte("POCIO");
        Mobbuffet.objecte = poti;
        Mobbuffet.utilitzaObjecte();
        System.out.println("Mobbuffet té " + Mobbuffet.getHp_actual() + " hp");
        
        Ou marmasaur = mulmasaur.reproduccio(mulmasaur);
        System.out.println(mulmasaur.getNom() + " està molt cansat");

//        try {
//            //intentem fer el codi en el try
//            Ou marmasaur = mulmasaur.reproduccio(mulmasaur);
//           //si salta una excepció al fer el codi que hi ha dintre del try, anirà a executar aquest codi, en comptes d'acabar el programa
//           } catch (Exception e) {
//               //Escrivim el missatge de l’excepció. El missatge és la String que            hem posat en el constructor de l’excepció
//               e.printStackTrace();
//         //el finally s'executa sempre, tant si ha saltat l'excepció com si no
//           } finally {
//               System.out.println(marmander.getNom() + " està molt cansat");
//           }
        
        ArrayList<Mokepon> mokedex = new ArrayList<>();
        mokedex.add(mulmasaur);
        mokedex.add(Mobbuffet);
        mokedex.add(marmander);
        //mokedex.add(missingNo);
        mokedex.add(mo);

        Collections.sort(mokedex);
        nomDeTots(mokedex);
        
      //si mokepon1 es més rapid, torna 1, si no, torna 2.
        int torn = MokeponMesRapid(Mobbuffet, marmander);
        while(true) {
                //usuari tria el numero d'atac amb un scanner
                int numAtac = triaAtac(torn, Mobbuffet, marmander);
                //si el torn es 1 mokepon1 ataca a mokepon2, si el torn es 2 al reves. Consulta si el mokepon atacat ha resultat debilitat, i retorna l'estat del Mokepon atacant
                boolean debilitat = gestionarAtac(Mobbuffet, marmander, torn, numAtac);
                //si l'altre Mokepon queda debilitat, s'acaba el combat i s'anuncia el guanyador
                if(fiCombat(debilitat, torn))
                	break;
                //es passa el torn al següent
                torn++;
        }

    }
    
    public static void nomDeTots(ArrayList<? extends Mokepon> list) {
        System.out.println("[");
    	for(Mokepon m : list) {
            m.diguesNom();
        }
    	System.out.println("]");
    }

    
	private static boolean fiCombat(boolean debilitat, int torn) {
		
		if(torn%2 == 1 && debilitat) {
			System.out.println("Felicitats Mobbuffet, has guanyat!");
			return true;
		}
		else if(torn%2 == 0 && debilitat) {
			System.out.println("Felicitats Marmander, has guanyat!");
			return true;
		}
			
		return false;
	}

	private static boolean gestionarAtac(MokeponCapturat mobbuffet, Mokepon marmander, int torn, int numAtac) {
		
		if(torn%2 == 1) {
			mobbuffet.atacar(marmander, numAtac);
			if(marmander.isDebilitat())
				return true;
		}
		else {
			marmander.atacar(mobbuffet, numAtac);
			if(mobbuffet.isDebilitat())
				return true;
		}
		return false;
	}

	private static int triaAtac(int torn, MokeponCapturat mobbuffet, Mokepon marmander) {
		
		Scanner ohowo = new Scanner(System.in);
		System.out.println("Tria l'atac del mokepon: ");
		while (true) {
			if(torn%2 == 1) {
				System.out.println(mobbuffet.getAtacs());
				int r = ohowo.nextInt();
				if(r <= mobbuffet.getAtacs().size() - 1)
					return r;
				else
					System.out.println("Si us plau, tria, un atac dels disponibles.");
			}
			else {
				System.out.println(marmander.getAtacs());
				int r = ohowo.nextInt();
				if(r <= marmander.getAtacs().size() - 1)
					return r;
				else
					System.out.println("Si us plau, tria, un atac dels disponibles.");
			}
		}
		
	}

	public static int MokeponMesRapid(Mokepon a, Mokepon b) {
		
		if(a.getVel() > b.getVel())
			return 1;
		else
			return 2;
	}

    public static MokeponCapturat capturar(Mokepon mok, String nomEntrenador, String nomDonat) {

        if(!(mok instanceof MokeponCapturat)) {
		    return new MokeponCapturat(mok, nomDonat, nomEntrenador);
		}
		else {
			System.out.println("No pots capturar un Mokepon que ja esta capturat");
			return (MokeponCapturat) mok;
		}
    }
}
