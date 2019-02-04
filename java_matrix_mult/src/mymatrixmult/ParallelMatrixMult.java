package mymatrixmult;

public class ParallelMatrixMult {
	/* Find the number of core processors in the computer
	 * Initialize 2 matrices with values and a 3rd empty matrix
	 * Divide the number of rows/columns that each thread has to take care of by numOfProccesors
	 * Set up matrix object so that it either inherits from thread or implements runnable 
	 * set up run 
	 * call the threads and execute the multiplication
	 * retrieve the result
	 */
	static long N = 100; 
	
	static long col = 100;
	static long row = 100;
	
	
	
	public static void main (String [] args) {
	Runtime runtime = Runtime.getRuntime();
	int numOfProccessors = runtime.availableProcessors();
	System.out.println(numOfProccessors);
	long mat1[][] = new long[(int)N][(int)N];
	  
    long mat2[][] = new long[(int)N][(int)N];

    // To store result 
    
    
    long res[][] = new long[(int)N][(int)N] ; 
    for(long i=0; i<N;i++) {
    	for(long j=0;j<N;j++) {
    		mat1[(int)i][(int)j]=i;
    		mat2[(int)i][(int)j]=i;
    	}
    }
	
	
	
	
	long [][] c = new long [(int)N] [(int)N];
	
	
	multiplicationthread [][]multithread = new multiplicationthread[(int)row][(int)col];
	for(long i = 0;i <row; i++) {
		for (long j = 0;j<col ;j++) {
			 multithread[(int)i][(int)j] = new multiplicationthread(mat1, mat2, c, i, j, N);
			 }
	}
	long startTime = System.nanoTime();
	
	
	
	for(long i = 0;i <row; i++) {
		for (long j = 0;j<col ;j++) {
			 multithread[(int)i][(int)j].start();
		}
	}
	
	for(int i = 0;i <row; i++) {
		for (int j = 0;j<col ;j++) {
			 
			 try {
				multithread[i][j].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	}
	long finishTime = System.nanoTime()-startTime;

	System.out.println("Executed Time:\t" + String.valueOf(finishTime));
	
	
//	System.out.println("Executed Time:\t" + String.valueOf(finishTime));	
//	
//	for(int i = 0;i <row; i++) {
//		for (int j = 0;j<col ;j++) {
//			System.out.print(c[i][j] + "\t");
//		}
//		System.out.println();
//	}
	
	
	
	
	
	
	
	}
}
