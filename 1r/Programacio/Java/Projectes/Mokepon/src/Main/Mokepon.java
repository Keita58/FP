package Main;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Mokepon implements Comparable<Mokepon>, Serializable{
    
    //Atributs del pokemon
    private String nom;
    private int nivell;
    private int atk;
    private int def;
    private int vel;
    private int exp;
    private int hp_max;
    private int hp_actual;
    private boolean debilitat;
    private Sexe sexe;
    private Tipus tipus;
    private ArrayList<Atac> atacs = new ArrayList<>();
    
    public Mokepon() {
    	
    	this.nom = "Default";
    	this.nivell = 1;
    	this.atk = 1;
    	this.def = 1;
    	this.vel = 1;
    	this.hp_max = 10;
    	Random r = new Random();
    	int aux = r.nextInt(2);
    	if(aux == 0)
    		this.sexe = Sexe.MASCULI;
    	else
    		this.sexe = Sexe.FEMENI;
    }
    
    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNivell() {
		return nivell;
	}

	public int getAtk() {
		return atk;
	}
	
	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}
	
	public void setDef(int def) {
		this.def = def;
	}

	public int getVel() {
		return vel;
	}

	public int getExp() {
		return exp;
	}

	public int getHp_max() {
		return hp_max;
	}

	public void setHp_max(int hp_max) {
		if(hp_max < 0)
			this.hp_max = 0;
		else
			this.hp_max = hp_max;
	}

	public int getHp_actual() {
		return hp_actual;
	}

	public void setHp_actual(int hp_actual) {
		if(hp_actual < 0)
			this.hp_actual = 0;
		else
			this.hp_actual = hp_actual;
	}

	public boolean isDebilitat() {
		return debilitat;
	}

	public Tipus getTipus() {
		return tipus;
	}

	public ArrayList<Atac> getAtacs() {
		return atacs;
	}
	
	public Sexe getSexe() {
		return sexe;
	}
	//Afegit per a la pràctica de mokepon 8

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	//Afegit per a la pràctica de mokepon 8

	public void diguesNom() {
		System.out.println(nom);
	}

	public Mokepon(String nom, Tipus tipus) {
    	
    	this.nom = nom;
    	this.nivell = 1;
    	this.atk = 1;
    	this.def = 1;
    	this.vel = 1;
    	this.hp_max = 10;
    	this.tipus = tipus;
    	Random r = new Random();
    	int aux = r.nextInt(2);
    	if(aux == 0)
    		this.sexe = Sexe.MASCULI;
    	else
    		this.sexe = Sexe.FEMENI;
    }
    
    public Mokepon(String nom, Tipus tipus, int nivell) {
    	
    	this(nom, tipus);

    	for(int i = 0; i < nivell; i++)
    		pujarNivell();
    }
    
    public Mokepon(String nom, Tipus tipus, int nivell, int hp_max, int atk, int def, int vel) {
    	
    	this.nom = nom;
    	this.tipus = tipus;
    	this.hp_max = hp_max;
    	this.hp_actual = hp_max;
    	this.atk = atk;
    	this.def = def;
    	this.vel = vel;
    	this.nivell = nivell;
    	Random r = new Random();
    	int aux = r.nextInt(2);
    	if(aux == 0)
    		this.sexe = Sexe.MASCULI;
    	else
    		this.sexe = Sexe.FEMENI;
    }
    
    public Mokepon capturar(String nomEntrenador, String nomDonat) throws MokeponJaCapturatException {
    	
		if(!(this instanceof MokeponCapturat)) {
		    return new MokeponCapturat(this, nomDonat, nomEntrenador);
		}
		else {
			throw new MokeponJaCapturatException("El mokepon " + this.nom + " ja està capturat!");
		}
    }
    
    public void otorgarExp(int experiencia) {
    	
    	this.exp += experiencia;
    	
    	while(this.exp >= 100) {
    		this.exp -= 100;
    		pujarNivell();
    	}
    }
    
    private void pujarNivell() {
    	
    	Random r = new Random();
    	this.nivell++;
    	this.hp_max += r.nextInt(6);
    	this.atk += r.nextInt(3);
    	this.def += r.nextInt(3);
    	this.vel += r.nextInt(3);
    }
    
	public void afegirAtac(Atac at) {

		if(this.atacs.size() < 2) {
			this.atacs.add(at);
		}
	}

	static double efectivitat(Tipus atac, Tipus defensa) {
		
        if(atac == Tipus.FOC && defensa == Tipus.AIGUA ||atac == Tipus.AIGUA && defensa == Tipus.PLANTA ||atac == Tipus.PLANTA && defensa == Tipus.FOC ) 
            return 0.5;
		else if (atac == Tipus.AIGUA && defensa == Tipus.FOC ||atac == Tipus.FOC && defensa == Tipus.PLANTA ||atac == Tipus.PLANTA && defensa == Tipus.AIGUA ) 
            return 2;
		else 
            return 1;    
    }
	
	@Override
	public String toString() {
		return "[Nom=" + getNom() + ", Nivell=" + getNivell() + ", Atk=" + getAtk()
				+ ", Def=" + getDef() + ", Vel=" + getVel() + ", Exp=" + getExp() + ", Hp_max="
				+ getHp_max() + ", Hp_actual=" + getHp_actual() + ", Debilitat=" + isDebilitat()
				+ ", Tipus=" + getTipus() + ", Sexe=" + getSexe() + ", Atacs=" + getAtacs() + "]";
		//Afegit getSexe() per a la pràctica de mokepon 8
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(atacs, atk, def, exp, hp_max, nivell, nom, sexe, tipus, vel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mokepon other = (Mokepon) obj;
		return Objects.equals(atacs, other.atacs) && atk == other.atk && def == other.def && exp == other.exp
				&& hp_max == other.hp_max && nivell == other.nivell && Objects.equals(nom, other.nom)
				&& sexe == other.sexe && tipus == other.tipus && vel == other.vel;
	}

	public void atacar(Mokepon atacat, int num_atac) {
		
		if(!this.debilitat) {
			Atac aux = this.atacs.get(num_atac);
			double efecte = efectivitat(aux.tipus, atacat.tipus);
			
			double mal = ((((((2*this.nivell)/5)+2)*aux.poder*(this.atk/atacat.def))/50)+2)*efecte;

			atacat.hp_actual -= mal;
			if(atacat.hp_actual <= 0) {
				atacat.debilitarse();
				atacat.hp_actual = 0;
			}
		}
	}
	
	public void debilitarse() {
		
		this.debilitat = !this.debilitat;
	}
	
	public void curar(int hp) {
		
		this.debilitat = false;
		this.hp_actual = this.hp_max;
	}

	public Ou reproduccio(Mokepon mok) {
		
		if(this.tipus == mok.tipus && this.sexe != mok.sexe && !this.debilitat && !mok.debilitat)
			return new Ou(this.nom, mok.tipus);
		else {
			assert this.tipus == mok.tipus : "Error, són de diferent tipus!";
			assert this.sexe != mok.sexe : "Error, són del mateix sexe!";
			assert !this.debilitat : "Error, " + this.getNom() + " està debilitat!";
			assert !mok.debilitat : "Error, " + mok.getNom() + " està debilitat!";
		}
		return null;
	}

	@Override
	public int compareTo(Mokepon o) {
		
		if(this.tipus.ordinal() < o.tipus.ordinal())
			return -1;
		else if(this.tipus.ordinal() > o.tipus.ordinal())
			return 0;
		else {
			if(this.nom.compareTo(o.nom) == -1)
				return -1;
			else if(this.nom.compareTo(o.nom) == 1)
				return 1;
			else {
				if(this.nivell > o.nivell)
					return 1;
				else if(this.nivell < o.nivell)
					return -1;
				else
					return 0;
			}
		}
			
	}
}
