import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;
import java.util.Iterator;
/*
	Program: Assignment #1 // Grocery Store line
	Author: Pete Oram #800159179
	Date: 9/23/2013
	Summary: This program simulates customers waiting in line at a grocery store. The program represents
	         a sixty minute time period, accounting for new customers entering the store. There is a 25
	         percent chance that a new customer walks in. The program monitors the total amount of customers
	         that entered the queue and the total amount of customers that are serviced. The program also outputs
	         the maximum length the line reached during the one hour period.		
*/
class Driver{
    
     public static void main(String[] args){
     int enteredQueue = 0; //Counts the total customers that entered the queue
     int maxLine = 0; //Accounts for the highest length the queue reached
     int servicedTotal = 0; //Counts the total number of customers serviced
     
     Queue<Customer> line = new LinkedList<Customer>(); //A Queue is created for the customer objects
     Random rand = new Random(); // Generates random number for potential customers entering store     
     
     System.out.println("Starting Hour of Work!");
     System.out.println("---------------------------------------------");//Indicates that iteration begins         
             for(int i =0; i < 60; i++){ //Loop executes 60 times, each iteration representing one minute of time
                if(rand.nextInt(4) == 1) //25% chance that the random number from 0-3 is 1, represents the odds a customer enters the store
                {
                       line.add(new Customer()); // A new customer is added to the line
                       enteredQueue++; //Adds count to number of customers that entered the line
                       System.out.println("A new customer appears! Queue length is now " + line.size()); //Indicates that a customer has been serviced
                }
                if(maxLine< line.size()) //Compares the size of the line with the value assigned to the maxLine variable
                {
                       maxLine = line.size();  //Assigns new value to maxLine
                } 
                Iterator it = line.iterator(); //Create new iterator object for the line   
                if(it.hasNext())//Checks to see if the Queue object has items in it
                {
                       Customer frontCustomer = (Customer)it.next();// This examines the front of the line
                       frontCustomer.decServiceTime(); //Decrements the front customers time by calling the method                     
                if(frontCustomer.getServiceTime() <= 0) //Checks to see if the customer being serviced is finished
                {
                       it.remove();//Removes the customer from the line
                       servicedTotal++; //Counts the total number of customers serviced
                       System.out.println("Customer has Finished!  Queue length is now " + line.size()); //Updates the line and outputs the new size
                } 
                System.out.println("---------------------------------------------"); //To account for the passing of time
                }
             }       
    System.out.println();
    System.out.println("Total number of Customers Serviced: " + servicedTotal); //These statements print the totals accumulated over 60 iterations
    System.out.println("Max Line Length: " + maxLine);
    System.out.println(enteredQueue + " Customers came into the line. Only " + servicedTotal + " were successfully served." );
  }
        
}
 






