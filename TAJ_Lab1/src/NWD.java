public class NWD {
	
	public int NWD(int a, int b) {
		
		while(b > 0){
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
		
	}
}