/*
 * CS331  Programming Language Lab
 * Assignment 1 - Basic Java Concurrent Programming
 *
 * Student Details
 * Name - Munindra Naik
 * Roll - 180101045
 *
 * Part - A - Estimation of PI using monte carlo method
 *
 * Instructions to execute
 * 1) On Linux terminal type "javac A.java" and hit enter
 * 2) After successful compilation then type "java A" and hit enter
 * 3) No of threads will be asked to enter, enter the value and the rest execution will happen
 *
 */

import java.util.*;
import java.io.*;
import java.lang.*;

class MyThread extends Thread{
    int id;
    int points;
    int[] insidePoints;

    public MyThread(int threadId,int[] insidePoints, int points){
      this.id = threadId;
      this.points = points;
      this.insidePoints = insidePoints; 
    }
    
    // simulating monte carlo to generate points and check which are inside the circle
    @Override
    public void run(){
	  int prec = 1000000;
          Random rand = new Random();
	  for( int i=0 ; i < points ; ++i){
	     double x = (double)rand.nextInt(prec+1)/(double)prec;
	     double y = (double)rand.nextInt(prec+1)/(double)prec;

	     if( (x*x + y*y) <= 1){
	        insidePoints[id] += 1 ;
	     }
	  }
	  //System.out.println("Thread " + id + " running and Inside points are" + insidePoints[id]);
    }

}
public class A{
        
	public static void main(String[] args){

	    int totalPoints = 1000000;
            int threadsToBeUsed = 1;

	    Scanner scan = new Scanner(System.in);
	    System.out.print("Enter the value of Threads : ");
	    threadsToBeUsed = scan.nextInt();
            
             try{
		 int eachThreadPoints = totalPoints/threadsToBeUsed;
	         Thread[] thread = new Thread[threadsToBeUsed];

                 //creating an array insidePoints which will store the count of points which will be inside th cirle   
		 int[] insidePoints = new int[threadsToBeUsed];
	         Arrays.fill(insidePoints,0);

		 for(int i = 0; i < threadsToBeUsed ; ++i){
	              if( i == (threadsToBeUsed-1)){
		       eachThreadPoints += (totalPoints)%eachThreadPoints ;
		      }	      
		      thread[i] = new Thread(new MyThread(i,insidePoints, eachThreadPoints));
		      thread[i].start();		  
		 }
                 
		 //below loop waits for the other threads to complete their execution before execution of this main thread
		 for( int  i=0; i < threadsToBeUsed; ++i)
		      thread[i].join();
                 
		 int totalInsidePoints = 0; 
		 for(int  i=0; i<threadsToBeUsed; ++i){
		      totalInsidePoints += insidePoints[i];
	       //     System.out.print(insidePoints[i] + " ");
		 }	      
                 
		 //printing the value of pi
		 //System.out.println("Number of inside points :" + totalInsidePoints);
		 
		 System.out.println("Pi/4 = " + (double)totalInsidePoints/(double)totalPoints);
		 System.out.println("Pi   = " + 4*(double)totalInsidePoints/(double)totalPoints);
		 System.out.print("Difference from Actual Value = ");
	 	 System.out.println( Math.PI - 4*(double)totalInsidePoints/(double)totalPoints);

	     } catch(Exception e){
	        System.out.println(e);
		System.exit(1);
	     }
	}
}
