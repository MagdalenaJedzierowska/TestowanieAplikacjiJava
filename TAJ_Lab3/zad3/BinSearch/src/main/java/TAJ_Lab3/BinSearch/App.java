package TAJ_Lab3.BinSearch;

public class App{
	public static int binSearch( int x, int[] A, int low, int high ){
       int middle, mySol;

       if (A.length == 0){
    	   return (-1);
       }
       if ( low > high ){
    	   return (-1);   
       }
       else{
    	   middle = (low + high)/2;

    	   if ( x == A[middle] ){
    		   mySol = middle;         
    	   }
    	   else if ( x < A[middle] ){
    		   mySol = binSearch( x, A, low, middle-1 ); 
    	   }
    	   else{
    		   mySol = binSearch( x, A, middle+1, high );      
    	   }
    	   return ( mySol );
       }
	}
}
