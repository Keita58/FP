package Main;
import java.io.Serializable;
import java.util.Objects;
import Objecte.*;

public class MokeponCapturat extends Mokepon implements Serializable{

	String nomPosat;
    private String nomEntrenador;
    private int felicitat;
    static int nombreMokeponsCapturats;
    Objecte objecte;
    Equipament equip;
    
	public String getNomEntrenador() {
		return nomEntrenador;
	}

	public void setNomEntrenador(String nomEntrenador) {
		this.nomEntrenador = nomEntrenador;
	}
    
    public MokeponCapturat(String nom, Tipus tipus) {
    	
    	super(nom, tipus);
    	
    	this.nomPosat = nom;
        this.nomEntrenador = "Marc";
        this.felicitat = 50;
        MokeponCapturat.nombreMokeponsCapturats++;
    }
    
    public Objecte getObjecte() {
		return objecte;
	}

	public void setObjecte(Objecte objecte) {
		this.objecte = objecte;
	}

	public Equipament getEquip() {
		return equip;
	}

	public void setEquip(Equipament equip) {
		this.equip = equip;
	}

	public MokeponCapturat(Mokepon mok, String nomPosat, String nomEntrenador) {
    	
    	super(nomPosat, mok.getTipus(), mok.getNivell(), mok.getHp_max(), mok.getAtk(), mok.getDef(), mok.getVel());
    	
    	this.nomPosat = nomPosat;
    	this.nomEntrenador = nomEntrenador;
    	this.felicitat = 50;
    	MokeponCapturat.nombreMokeponsCapturats++;
    }
    
    public MokeponCapturat(String nom, Tipus tipus, int nivell) {
    	
    	super(nom, tipus, nivell);
    	
    	this.nomPosat = nom;
        this.nomEntrenador = "Marc";
        this.felicitat = 50;
        MokeponCapturat.nombreMokeponsCapturats++;
    }
    
    public MokeponCapturat(String nom, Tipus tipus, int nivell, int hp_max, int atk, int def, int vel) {
    	
    	super(nom, tipus, nivell, hp_max, atk, def, vel);
    	
    	this.nomPosat = nom;
        this.nomEntrenador = "Marc";
        this.felicitat = 50;
        MokeponCapturat.nombreMokeponsCapturats++;
    }
    
    public void acariciar() {
    	
    	if(this.felicitat < 100)
    		this.felicitat += 10;
    }
    
    public void atacar(Mokepon atacat, int num_atac) {
		
		if(!this.isDebilitat()) {
			Atac aux = this.getAtacs().get(num_atac);
			double efecte = super.efectivitat(aux.tipus, atacat.getTipus());
			
			double mal = ((((((2*this.getNivell())/5)+2)*aux.poder*(this.getAtk()/atacat.getDef()))/50)+2)*efecte;
			
			if(this.felicitat >= 50)
				mal *= 1.2;
			else
				mal *= 0.8;
			
			atacat.setHp_actual(atacat.getHp_actual() - (int) mal);
			if(atacat.getHp_actual() <= 0) {
				atacat.debilitarse();
				atacat.setHp_actual(0);
			}
		}
	}

	@Override
	public String toString() {
		return super.toString() + ", [Nom entrenador=" + this.nomEntrenador + "]";
		//Nom de l'entrenador afegit per a la pràctica de Moképon 8
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nomEntrenador, nomPosat);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MokeponCapturat other = (MokeponCapturat) obj;
		return Objects.equals(nomEntrenador, other.nomEntrenador) && Objects.equals(nomPosat, other.nomPosat);
	}

	public void utilitzaObjecte() {
		objecte.utilitzar(this);
		if(objecte.getQuantitat() == 0)
			objecte = null;
	}
    
	public String objecteEquipat() {
		if(objecte == null)
			return "No té objectes";
		else
			return "[Nom=" + objecte.getNom() + ", " + "Quantitat=" + objecte.getQuantitat() + "]";
	}
}
