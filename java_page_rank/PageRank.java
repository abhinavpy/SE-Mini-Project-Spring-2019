package pagerank; 
import java.util.*;
import java.lang.*;

import pagerank.multiplicationthread;
  

/*The idea is M*r(i)= r(i+1)
 *Initial r(i) should be 1/n for all the values of x
 * Final value of r(i) converge to some value..check the difference between r(i-1) and r(i)
 * Stop if it is less than some e
 * [nxn]x[nx1]=[nx1]
 */
class PageRank { 
	final static int NUM_OF_NODES = 7;
	final static double epsilon = 0.1;
	static double r[][],r_1[][];
	int count=0;
	
//	final static double M[][] = {{0.5,0.5,0},{0.5,0,1},{0,0.5,0}};
//	final static double M[][] = {{0,0,1,1/3,1/2},{1/4,0,0,1/3,0},{0.25,1,0,0,0},{0.25,0,0,0,0.5},{0.25,0,0,1/3,0}};
//	final static double M[][] = {{0,0,0,0.5,0.5},{1/3,0,0,0},{1/3,0,0,0.5},{1/3,1,1/2,0}};
//	final static double M[][] = {{1/(double)6,2/(double)3,1/(double)6},{5/(double)12,1/(double)6,5/(double)12},{1/(double)6,2/(double)3,1/(double)6}};
//final static double M[][] = {{0.02,0.02,0.88,0.02,0.02,0.02,0.02},{0.02,0.45,0.45,0.02,0.02,0.02,0.02},{0.31,0.02,0.31,0.31,0.02,0.02,0.02},{0.02,0.02,0.02,0.45,0.45,0.02,0.02},{0.02,0.02,0.02,0.02,0.02,0.02,0.88},{0.02,0.02,0.02,0.02,0.02,0.45,0.45},{0.02,0.02,0.02,0.31,0.31,0.02,0.31}};
final static double M[][] = {{0.02,0.02,0.31,0.02,0.02,0.02,0.02},{0.02,0.45,0.02,0.02,0.02,0.02,0.02},{0.88,0.45,0.31,0.02,0.02,0.02,0.02},{0.02,0.02,0.31,0.45,0.02,0.02,0.31},{0.02,0.02,0.02,0.45,0.02,0.02,0.31},{0.02,0.02,0.02,0.02,0.02,0.45,0.02},{0.02,0.02,0.02,0.02,0.88,0.45,0.31}};
	static multiplicationthread [][]multithread; 
	
      public static void main(String args[]) {
    	  r = new double [NUM_OF_NODES][1];
    	  //Initializes r 1/x
    	  for(int i=0;i<NUM_OF_NODES;i++) {
    		  r[i][0]=(double)1/NUM_OF_NODES;
    	  }
//    	  for(int i=0;i<NUM_OF_NODES;i++) {
//    		  System.out.println(r[i][0]);
//    	  }
//    	  
    	  //Initialize thread Pool
    	  multithread = new multiplicationthread[(int)NUM_OF_NODES][(int)1];
    	  r_1= new double[NUM_OF_NODES][1];
    	  int decider=1;
    	  int l=0;
    	  long startime = System.nanoTime();
    	  while(decider==1) {
//    		  System.out.println("r");
//    		  for(int i=0;i<NUM_OF_NODES;i++) {
//        		  System.out.println(r[i][0]);
//        		  
//        	  }
//    		  System.out.println("r1");
//    		  for(int i=0;i<NUM_OF_NODES;i++) {
//        		  System.out.println(r_1[i][0]);
//        		  
//        	  }
    		  
    		  	decider=powerIterate(++l);
    		  	
    	    	  
    	  }
    	  long finaltime = System.nanoTime();
    	  System.out.print("The execution time is :" + (finaltime - startime)/((long) 1000000)); 
    	
    	  
    	  
    	  }
      
	      public static int powerIterate(int l) {
//	    	  System.out.println(l);
//	    	  System.out.println("Start of threading:");
	    	  System.out.println("r");
			  for(int i=0;i<NUM_OF_NODES;i++) {
				 
					  System.out.println(r[i][0]);
				  }
				  
//				  
//			  
//			  System.out.println("r1");
//			  for(int i=0;i<NUM_OF_NODES;i++) {
//				  System.out.println(r_1[i][0]);
//				  
//			  }
	    	  for(int i = 0;i <NUM_OF_NODES; i++) {
	    		  	
					 multithread[i][0] = new multiplicationthread(M, r, r_1, i, 0, NUM_OF_NODES);
					 multithread[i][0].start();
					 
	    	  }
	    	  
	    	  
	    	  for(int i =0;i<NUM_OF_NODES;i++) {
	    		  try {
					multithread[i][0].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	  }
	    	  
	    	  double sumofr=0, sumofr1=0;
	    	  for(int i=0;i<NUM_OF_NODES;i++) {
	    		  sumofr+=Math.abs(r[i][0]-r_1[i][0]);
	    		
	    	  }
	    	  if(l==10000) {
	    		  System.out.println("sumofrv"+sumofr+"sumofr1 "+sumofr1+" return 0");
	    		  return 0;
	    	  }  	  
	    	  if(sumofr<=epsilon) {
//	    		  System.out.println("r");
//	    		  for(int i=0;i<NUM_OF_NODES;i++) {
//	        		  System.out.println(r[i][0]);
//	        		  
//	        	  }
//	    		  System.out.println("r1");
//	    		  for(int i=0;i<NUM_OF_NODES;i++) {
//	        		  System.out.println(r_1[i][0]);
//	        		  
//	        	  }
//	    		  System.out.println("sumofdfr "+sumofr+"sumofr1 "+sumofr1+"return 0");
//	    		  
	    		  System.out.println(l);
	    		  return 0;
	    	  }
	    	  else {
	    		  for(int i=0;i<NUM_OF_NODES;i++) {
	        		  r[i][0]=r_1[i][0];
	        	  }
//	    		  System.out.println("sumofr"+sumofr+"sumofr1"+sumofr1+"return 0");
	    		  return 1;
	    	  }
	    	  
	    	  
	  
	} 
}