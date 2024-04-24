
public class exercici2 {

	public static void main(String[] args) {
		
		int[] a = new int[] {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,3,3,3,3,3,3,3,3,3,3,3,3,3,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6};
		int l = 0, r = a.length - 1;
		int num = cercaBinaria(a, 6, l, r);
		System.out.println(num);
	}
	
	private static int cercaBinaria(int arr[], int x, int l, int r)
    {
        //cerques el punt mitjà entre els l i r
        //l és punt inicial i r és punt final
        int m = l + (r - l) / 2;
        //mira si X està present al punt mig
        if (arr[m] == x)
            return m;
        else if (l < r){
	        //si x és més gran, aleshores poses el punt inicial allà per tornar a buscar
	        if (arr[m] < x)
	            l = m + 1;
	        //si x és més petita, poses el punt final entre alla
	        else
	            r = m - 1;
	        return cercaBinaria(arr, x, l, r);
        } 
        //si hem arribat aquí és que l'element no era present
        return -1;
    }
}
