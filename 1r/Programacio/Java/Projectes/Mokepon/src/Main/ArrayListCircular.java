package Main;
import java.util.ArrayList;

public class ArrayListCircular<T> extends ArrayList<T>{
   
    @Override
    public T get(int index) {
        index = index % super.size();
        return super.get(index);
    }
}
