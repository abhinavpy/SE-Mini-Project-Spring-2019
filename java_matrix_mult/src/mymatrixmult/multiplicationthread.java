package mymatrixmult;

public class multiplicationthread extends Thread {
		private long[][] a;
	    private long[][] b;
	    private long[][] prod;
	    private long row,col;
	    private long dimension;

	
		public multiplicationthread(long [][]a, long [][]b, long [][]prod,long row, long col, long dimension) {
			
			this.a=a;
			this.b=b;
			this.prod=prod;
			this.row=row;
			this.col=col;
			this.dimension=dimension;
			
		}
		
		public void run() {
			for(long i = 0;i<dimension;i++) {
				prod[(int)row][(int)col] += a[(int)row][(int)i] * b[(int)i][(int)col];
			}
			
			
		}
}
