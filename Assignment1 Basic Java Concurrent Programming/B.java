/*
 * CS331 Programming Language Lab
 * Assignment 1 - Basic Java Concurrent Programming
 *
 * Student Details
 * Name - Munindra Naik
 * Roll - 180101045
 *
 * Part B -  Approximation of Integration of expression f(x) = e^(-x^2/2)/sqrt(2*PI) from -1 to 1 using Simpson 1/3 rule
 *           Actual Value of Integrations comes to be 0.6826894921370859
 * Instructions to execute
 * 1) Type and execute on linux terminal "javac B.java"
 * 2) After successful compilation type and execute on terminal "java B"
 * 3) Enter the value of thread which will be asked
 */
import java.util.*;
import java.io.*;
import java.lang.*;


//inheriting already present Thread class and overriding the run method
class MyThread extends Thread{
        
	double start;
	int id;
        int sz;
	int cnt;
	double incr;
	double[] sumVal;
	
	public MyThread(double[] sumVal,int id,double start,double incr, int sz, int cnt){
	  this.start = start;
	  this.sz = sz;
	  this.sumVal = sumVal;
	  this.id = id;
	  this.incr = incr;
	  this.cnt = cnt;
	}
        @Override
	public void run(){
	     
	     sumVal[id] = 0.00;
             double b = start;
	     int threads = sumVal.length;
	     
	     //System.out.println("Thread ->" + id + " cnt is " + cnt + " start " + start + " size is " + sz);
	     
	     //cnt is used to detect whether that term needs to multiplied with which coefficient
	     for( int i = 0; i < sz ; ++i,++cnt ){
	        double d = Math.exp(-(b*b)/2)/Math.sqrt(2*Math.PI);
		if(cnt != 0 && !(id == (threads-1) && i == (sz-1)) ){
		  if(cnt%2==1)
		   d = 4*d;
		  else
		   d = 2*d;
		}
		sumVal[id] += d;
		b += incr;
	     }
	}
}

public class B{
        
	public static void main(String[] args){
           
	   Scanner scan = new Scanner(System.in);
	   System.out.print("Enter the value of thread : ");
           int numberOfThreads = scan.nextInt();	   
           
	   //generating a random number which will be added to 10^6 so  we have compute the value for more than 10^6 points
	   int n = 1000037; //taking a prime number of points between a and b
	   
	   //Random rand = new Random();
	  // int l = rand.nextInt();
	  //taking random number of points was leading to longer time to process and sometimes output as 0.0

	   double delx = (double)2/(double)n; 
	  
	   try{

	      Thread[] threads = new Thread[numberOfThreads];
              int eachThread = (n)/numberOfThreads;
	      int cnt = 0; //stores the count of points on which the function has been computed by previous threads
	      
	      double[] sumVal = new double[numberOfThreads]; //stores the summation value of points for each thread
	      double start = -1.00; //starting limit of the function 
	      
	      for(int j=0; j < numberOfThreads ; ++j){
	          //the last thread may perform more computation than what other threads performed 
		  if(j == numberOfThreads -1){
		        eachThread += (n)%numberOfThreads;
		  }
		  threads[j] = new Thread(new MyThread(sumVal,j,start,delx,eachThread,cnt));
		  threads[j].start();

		  cnt = cnt + eachThread;
		  start = start + delx*eachThread;
	      }
              
	      for(int j=0;j<numberOfThreads; ++j)
		  threads[j].join();

       	      double integrationValue = 0;
	      for( int j= 0; j< numberOfThreads; j++){
		  integrationValue+=sumVal[j];
		 // System.out.print(myArray[j] + " ");
	      }

	      //System.out.println("");
	      integrationValue = (delx*integrationValue)/3;

	      System.out.println("The value of the intergration from -1 to 1 is : " + integrationValue);
          System.out.println("The actual value of the integration is        : 0.6826894921370859" );
		  System.out.print("Differnce between them is "); 
		  //the difference between estimation and real value is almost equal in all cases od threads as we have taken constant number of points
		  System.out.println(integrationValue - (double)0.6826894921370859);
	   } catch(InterruptedException e){
	     System.out.println(e);
	     System.exit(1);
	   }
	}
}
