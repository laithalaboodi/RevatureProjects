// by Laith Alaboodi
// Project 1A

import java.util.*;

public class Project1A {
	 // keyboard is a global variable because it is above main()
	  static Scanner keyboard = new Scanner(System.in); 
	  
	  public static void main(String[] args) 
	  {
	 
	    //called the welcome message
	    welcomeMessage();
	  
	    //loop to try again
	    //include all the code that i want to show again when the user say yes or no
	    do {
	      //logic here
	    	getMedNumber();
	    } while (getYorN("want to try again? Y or N"));
	    
	    // ending message called here to tell the user end of program 
	    goodBye();
	  }//end of main
	  
	  
	  //the welcome message module
	  public static void welcomeMessage(){
	    System.out.println("*************************************");
	    System.out.println("Welcome to Project 1 A");
	    System.out.println("*************************************");
	  }

	  // function to get the number user chose and do the math
	  public static int getMedNumber(){
	    int userOption=0; 
	      int userCalcInput = 0;
	      
	      options();
	      
	      //made a validation loop 
	      while(userOption < 1 || userOption > 4){
	    	  userOption=getInteger(("pick 1 - 4?"));
	    	  if(userOption < 1 || userOption > 4) {
	          System.out.println("ERROR: Number must be between 1 to 4");
	          System.out.println("Please try again.");
	    	  }
	    	  if(userOption == 1) {
	    		  System.out.println("You picked 1");
	    		  userCalcInput = getInteger("How many cups?:");
	    		  System.out.println("Total Teaspoons: " + userCalcInput * 48);
	    	  }
	    	  if(userOption == 2) {
	    		  System.out.println("You picked 2");
	    		  userCalcInput = getInteger("How many miles?:");
	    		  System.out.println("Total Kilometers: " + userCalcInput * 1.609);
	    	  }
	    	  if(userOption == 3) {
	    		  System.out.println("You picked 3");
	    		  userCalcInput = getInteger("How many US Gallons?:");
	    		  System.out.println("Imperial Gallons: " + userCalcInput / 1.201);
	    	  }
	    	  if(userOption == 4) {
	    		  break;
	    	  }
	      
	      }
	    
	    return userOption;
	  }
	  //this is if i want to break each calc in a functions
	  public static void test(int userOption) {
		  userOption = getMedNumber();
		  System.out.println("TEST: "+ userOption);
	  }
	  
	  //method with all messages for options to pick from
	  public static void options() {
		    System.out.println("1. Cups to Teaspoons");
		    System.out.println("2. Miles to Kilometers");
		    System.out.println("3. US Gallons to Imperial Gallons");
		    System.out.println("4. Quit");
	  }

	  //exit module
	  public static void goodBye(){
	    System.out.println("Thanks for using our app!");
	  }
	  
	   //******************************************************************************************************************
	  //below is a my input routines with full validations 
	  public static String getString(String msg) {
	    String answer = "";
	    System.out.println(msg);
	    try {
	      answer = keyboard.nextLine(); 
	    }
	    catch (Exception e) {
	      System.err.println("Error reading input from user. Ending program.");
	      System.exit(-1);
	    } 
	    
	    while (answer.replace(" ", "").equals("")) {
	      System.err.println("Error: Missing input.");
	      try {
	        System.out.println(msg);
	        answer = keyboard.nextLine(); 
	      }
	      catch (Exception e) {
	        System.err.println("Error reading input from user. Ending program.");
	        System.exit(-1);
	      } 
	    }
	    return answer;            
	  }
	  
	  public static int getInteger(String msg) {
	    System.out.println(msg);
	    while (!keyboard.hasNextInt()) {
	      keyboard.nextLine();
	      System.err.println("Invalid integer. Try again.");
	      System.out.println(msg);
	    }
	    int number = keyboard.nextInt();
	    keyboard.nextLine(); //flushes the buffer
	    return number;
	  }
	  
	  public static boolean getYorN(String msg) {
	    String answer = getString(msg);
	    
	    while (answer.compareToIgnoreCase("y")   != 0 
	             && answer.compareToIgnoreCase("yes") != 0 
	             && answer.compareToIgnoreCase("n")   != 0 
	             && answer.compareToIgnoreCase("no")  != 0) {
	      
	      if (answer.replace(" ", "").equals("")) {
	        System.err.println("Error: Missing y/n input.");
	      } else {
	        if (answer.compareToIgnoreCase("y")   != 0 
	              && answer.compareToIgnoreCase("yes") != 0 
	              && answer.compareToIgnoreCase("n")   != 0 
	              && answer.compareToIgnoreCase("no")  != 0) {
	          System.err.println("Error: Unexpected input.");
	        }
	      }
	      answer = getString(msg);
	    } 
	    
	    if  (answer.compareToIgnoreCase("y")   == 0  
	           || answer.compareToIgnoreCase("yes") == 0) {
	      return true;
	    } 
	    else {
	      return false;
	    }
	  }
	//*********************************************************************************************************************
	}
