package Main;
import java.util.ArrayList;

public class ArrayListAcotada<T> extends ArrayList<T>{

	@Override
    public T get(int index) {
        if(index > super.size() - 1)
        	return super.get(super.size() - 1);
        else if(index < 0)
        	return super.get(0);
        else
        	return super.get(index);
    }

}
