package pagerank;

public class multiplicationthread extends Thread {
	private double[][] a;
	private double[][] b;
	private double[][] prod;
	private int row,col;
	private int dimension;


//	public multiplicationthread(long [][]a, long [][]b, long [][]prod,long row, long col, long dimension) {
//		
//		this.a=a;
//		this.b=b;
//		this.prod=prod;
//		this.row=row;	
//		this.col=col;
//		this.dimension=dimension;
//		
//	}

	public multiplicationthread(double[][] a, double[][] b, double[][] prod, int row, int col, int dimension) {
		this.a=a;
		this.b=b;
		this.prod=prod;
		this.row=row;	
		this.col=col;
		this.dimension=dimension;
		
	}

	public void run() {
		double sum=0;
		for(int i = 0;i<dimension;i++) {
			
			sum+= (double)a[(int)row][(int)i] * b[(int)i][(int)col];
			
//			System.out.println("sum"+ prod[(int)row][(int)col] +"a[row][i]"+a[(int)row][(int)i]+"b[i][col]"+b[(int)i][(int)col]+" row"+row+" col:"+col + " i:"+i+" dimension"+dimension);
		}
		prod[(int)row][(int)col]=sum;
	
	}
}


