/*
* CS331 Programming Language Lab
* Assignment 2 - Advance Java Concurrent Programming
*
* Student Details
* Name - Munindra Naik
* Roll - 180101045
*
* Instructions to execute -
* 1) On Terminal type "javac Bank.java" and hit enter
* 2) On successful compilation type "java Bank" and hit enter
* 3) After completion of the program the time taken will pe printed on the screen in milliseconds
*
*/

import java.util.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

class Account{
   private int balance;
   private long accountNumber;
   private boolean activeAccount;

   public Account(long accountNumber){
        this.balance = Math.abs(ThreadLocalRandom.current().nextInt());
		this.accountNumber = accountNumber;
		this.activeAccount = true;
   }
   
   public Account(long accountNumber,int balance){
	   this.balance = balance;
	   this.accountNumber = accountNumber;
	   this.activeAccount = true;
   }
   
   public int getbal(){
   		return this.balance;
   }
   public long AccountNumber(){
   		return this.accountNumber;
   }
   public boolean isactive(){
        return this.activeAccount;
   }
   public void CloseAccount(){
        synchronized(this){
		   this.activeAccount = false;
		}
   }

   public synchronized void transfer(int amount){
	   		this.balance += amount;
   }
   
   public synchronized boolean withdraw(int amount){
   //needs lock so that only one transaction can only access the balance
   //while deducting the amount from account
	   synchronized(this){
	   if( this.balance >= amount){
	     this.balance-=amount;
		 return true;
	   }else{
	     return false;
	   }
   }
   }
}

class Updater extends Thread{
   int branch;
   Hashtable<Integer,LinkedList<Account>> BankAccounts;

   public Updater(int branch, Hashtable<Integer,LinkedList<Account>> BankAccounts){
        this.branch = branch;
   		this.BankAccounts = BankAccounts;
 }

   @Override
   public void run(){
   		int instructions = (int)1e5;
   		int depositTransactions = (int)(0.33*instructions);
   		int addAccountTransactions = (int)(0.003*instructions);
   		int transferAccountTransactions = instructions - 3*depositTransactions - 2*addAccountTransactions;
        
		int[] transactionsLeft = new int[6];
	    transactionsLeft[0] = depositTransactions;
		transactionsLeft[1] = transactionsLeft[2] = depositTransactions;
	    transactionsLeft[3] = transactionsLeft[4] = addAccountTransactions;
	    transactionsLeft[5] = transferAccountTransactions;	
   		
		//10^5 transactions will be performed by each updater
   		//the below loop simulates the number of transactions
		//there are 6 types of operations
		// 1 - cash deposit
		// 2 - cash withdrawl
		// 3 - money transfer 
		// 4 - addAccountTransactions a customer
		// 5 - delete a customer
		// 6 - transfer a customer
   		
		Random rand = new Random();
		for( int i=0 ;i < instructions ; i++){
			//System.out.println(i + " " + branch);
           int sizeOfCurrentBranch = BankAccounts.get(branch-1).size();
			int operation = rand.nextInt(6);
			int randomAccountNumber;
			int amount;
			long newAccountNumber;
			
			randomAccountNumber = Math.abs(rand.nextInt(sizeOfCurrentBranch));
			while(!BankAccounts.get(branch-1).get(randomAccountNumber).isactive()){
				randomAccountNumber = Math.abs(rand.nextInt(sizeOfCurrentBranch));
			}
			Account newAccount;
   		    while(transactionsLeft[operation] <= 0){
				operation = rand.nextInt(6);
			   }
			long factor = (long)1e10;
			if(branch == 10)
				factor = (long)1e9;
			
			switch(operation){
			    case 0:
				      //cash deposit
					   amount = Math.abs(rand.nextInt()); 
					   BankAccounts.get(branch-1).get(randomAccountNumber).transfer(amount);
					//  System.out.println((long)((this.branch*factor) + temp) + "  " + (long)(this.branch) + " " + factor + " " + temp+ " " +amount);
					  break;
			    
			    case 1:
				     //cash withdrawl
					  amount = Math.abs(rand.nextInt());
					  BankAccounts.get(branch-1).get(randomAccountNumber).withdraw(amount);
					  break;
               
				case 2:
				      //transfering amount from one customer to another
					  int randomAccount2 = rand.nextInt(sizeOfCurrentBranch);
					  while(!BankAccounts.get(branch-1).get(randomAccount2).isactive())
						  randomAccount2 = rand.nextInt(sizeOfCurrentBranch);

					  amount = Math.abs(rand.nextInt());
					  boolean b = BankAccounts.get(branch-1).get(randomAccountNumber).withdraw(amount);
					  if(b){
					    BankAccounts.get(branch-1).get(randomAccount2).transfer(amount);
					  }
					  break;
				
			   case 3:
			          //creating a new account for customer
					  newAccountNumber = branch*factor + sizeOfCurrentBranch;
					  newAccount = new Account(newAccountNumber);
					  synchronized(BankAccounts){
                      BankAccounts.get(branch-1).add(newAccount);
					  }
					  break;
              
				case 4:
					  //deleting customer 
					  synchronized(BankAccounts){
					   BankAccounts.get(branch-1).get(randomAccountNumber).CloseAccount();
					  }
					  break;

			    case 5://transfering customer
					  int targetBranch = rand.nextInt(10) +1;
					  while(targetBranch == branch)
						  targetBranch = rand.nextInt(10) + 1;
					  int sz1 = BankAccounts.get(targetBranch-1).size();
					  long m = (long)1e10;
					  if(targetBranch == 10)
						  m = (long)1e9;
					  //generating new account number adding the account on the branch 
					  newAccountNumber = targetBranch*m + sz1;
					  newAccount = new Account(newAccountNumber,BankAccounts.get(branch-1).get(randomAccountNumber).getbal());
					  synchronized(BankAccounts){
					  BankAccounts.get(branch-1).get(randomAccountNumber).CloseAccount();
					  BankAccounts.get(targetBranch-1).add(newAccount);
					  }
					  break;
			   
				default:
					  break;
			
			}
			transactionsLeft[operation]-=1;
		}
		}
  
  }

public class Bank{

   public static void main(String[] args){
              
	          long startTime = System.currentTimeMillis();
	          int branches = 10;
	          int accountinbranch = (int)1e4;
	          int numberofupdaters = 100; 
	        
			  Hashtable<Integer,LinkedList<Account>> accounts = new Hashtable<Integer,LinkedList<Account>>();
	        
			  //initialization of accounts with account number and balance in 10 branches
			  //account number is set by us but balance is set randomly 
	          for(int i=0; i < branches;i++){
			   LinkedList<Account> temp = new LinkedList<>();
			     for(int j=0;j<accountinbranch;j++){
					 long factor = (long)1e10;
					 if(i==9)
						 factor = (long)1e9;
				    Account newAccount = new Account(((i+1)*factor + j));
					temp.add(newAccount);
				 }
				 accounts.put(i,temp);
			  }
			 
			  /*for(int i=0;i<10;i++){
	         LinkedList<Account> temp = accounts.get(i);
			 int h = 0;
             for(Account t : temp){
			  System.out.println(t.AccountNumber() + " " + t.bal());
			  if( h == 10)
				  break;
			  h++;
			 }			 
			 }*/
             
			  try{
		            Thread[] thread = new Thread[numberofupdaters];
			        for( int i=0;i<numberofupdaters;i++){
					 thread[i] = new Thread(new Updater(((i/10) + 1), accounts));
					 thread[i].start();
					}		

					for(int i=0;i<numberofupdaters;i++)
						thread[i].join();

			  }catch(Exception e){
			    System.err.println(e);
			  }
              
			 // for(int i=0;i<10;i++)
			//	  System.out.println((i+1) + " size is = "
			//			  + accounts.get(i).size());
			  long finishTime = System.currentTimeMillis();
			  System.out.println("Time taken to complete the task - " + (finishTime - startTime) + " milliseconds");
		}
}
