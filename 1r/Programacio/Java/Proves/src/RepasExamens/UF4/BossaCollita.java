package RepasExamens.UF4;

import java.util.ArrayList;

public class BossaCollita<T extends Hortalissa> extends ArrayList<T>{

	@Override
	public T remove(int index) {
		
		T aux = this.get(index);
		this.remove(index);
		aux.vendre();
		return aux;
	}
}
