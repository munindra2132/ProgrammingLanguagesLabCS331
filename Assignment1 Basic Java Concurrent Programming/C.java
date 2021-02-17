/*
 *CS 331 - Programming Language Lab
 *Assignment 1 - Basic Java Concurrent Programming
 *
 *Student Details
 *Name - Munindra Naik
 *Roll No - 180101045
 *
 *Part C -  Matrix multiplication of matrix of size 10^3 x 10^3 using O(n^3) method
 *
 *Instruction to Execute
 *1) On Linux terminal  type "javac C.java" and hit enter
 *2) On successful compilation then type "java C" and hit enter
 *3) You will be prompted to enter the value of thread, enter it and the code executes the rest
 */

import java.util.*;
import java.io.*;
import java.lang.*;

//threadclass to randomize the matrix
class RandomizeThread extends Thread
{
  int[][] A;
  int start, N; // starting rows and number of rows
                                                     
  public RandomizeThread(int[][] A, int s, int n)
  {
    this.A = A;
    start  = s;
    N      = n;
  }
  
  @Override                                                     
  public void run()
  { 
    Random rand = new Random();
    int cols = A[0].length;                                                   
    for(int i=start; i < N+start && i < cols; ++i ){
    	 for( int j= 0; j< cols ; ++j){
            A[i][j] = rand.nextInt(11);
	    }
     }
   }
}

//matrix multiplication using threads
//class for thread
class MyThread extends Thread
{
  int[][] A;
  int[][] B;
  int[][] C;
  int start, N; // starting rows and number of rows
                                                     
  public MyThread(int[][] A, int[][] B, int[][] C, int s, int n)
  {
    this.A = A;
    this.B = B;
    this.C = C;
    start  = s;
    N      = n;
  }
  
  @Override                                                     
  public void run()
  {
    int cols = A[0].length;                                                   
    for(int i=start; i < N+start && i < cols; ++i ){
    	 for( int j= 0; j< cols ; ++j){
            C[i][j] = 0;
          	for( int k=0; k < cols; ++k){
             	C[i][j] = C[i][j] + A[i][k]*B[k][j];
          	}
	    }
     }
   }
}

public class C{

 	public static void printMatrix(int[][] A){
          // this function prints the matrix	
	   int dim = A[0].length;

	   for(int i=0;i < dim ; ++i){
	    for(int j=0; j < dim; ++j)
		    System.out.print(A[i][j] + " ");
	    System.out.println("");
	   }

	}
	public static void main(String[] args){
	   //declaring two matrix of size 10^3 x 10^3
	   int dimension_1 = 1000;
	   int dimension_2 = 1000;
           
	   // A and B are two matrix which have to multiplied which will stored in Matrix named C;
	   int[][] A = new int[dimension_1][dimension_2];
	   int[][] B = new int[dimension_1][dimension_2];
       int[][] Product = new int[dimension_1][dimension_2]; 
          
	    int core;
        Scanner scan = new Scanner(System.in);
	    System.out.print("Enter the value of thread : ");
	    core = scan.nextInt();
	   
	    // System.out.println(" Value entered - > " + core);

        try{ 
	    
		Thread[] thread = new Thread[core]; //need to keep track of threads
		
	    int eachRow = dimension_1/core;   //this much rows will be processes by each thread
        //Randomizing the matrix
		for(int j = 0; j < 2; j++){
			for(int i=0; i < core ; ++i){
	    	int eachRowActual = eachRow;
			//the last row may have to process more rows
			if(i == (core-1) ) 
			{
				eachRowActual = eachRowActual + dimension_1%core;
			} 
			if(j==0)
			thread[i] = new Thread(new RandomizeThread(A,eachRow*i,eachRowActual));
			else
			thread[i] = new Thread(new RandomizeThread(B,eachRow*i,eachRowActual));
	    	//starting the thread
			thread[i].start(); 	
	    	}

	    	for(int i=0;i < core; ++i){
	    	thread[i].join();
	    	}
	    }
		//Matrix multiplication
		for(int i=0; i < core ; ++i){
			int  eachRowActual = eachRow;
			//the last row may have to process more rows
			if(i == (core-1) ) 
			{
				eachRowActual = eachRowActual + dimension_1%core;
			} 
			thread[i]  = new Thread(new MyThread(A,B,Product,eachRow*i,eachRowActual));
	        //starting the thread
			thread[i].start(); 	
	    }

	    for(int i=0;i < core; ++i){
	    	thread[i].join();
	    }
            
	    System.out.println("Matrix A :");
	    printMatrix(A);
	    System.out.println("\nMatrix B :");
	    printMatrix(B);
	    System.out.println("\nMatrix Product of A and B :");
	    printMatrix(Product);
		
	   } catch(InterruptedException e){
	    System.out.println(e);
	    System.exit(1);
	   }   	    
	}
}
