package game;

import java.util.Scanner;

public class Main {
	
  //having it in globle 
   static Scanner keyboard = new Scanner(System.in);
   
   public static void main(String[] args) {
	   System.err.println("Example:* go north *");
       RoomManger manager = new RoomManger();
       manager.init();
       Player player = new Player(manager.startingRoom);
       while (true) {
           printRoom(player);
           parse(collectInput(), player);
       }
   }//end of main

   //current room and printing the toString here 
   public static void printRoom(Player player) {
       System.out.println("\n"+player.getCurrentRoom().toString());
   }
   //getting user input 
   public static String[] collectInput() {
       return getString("Where you want to go?").toLowerCase().split(" ");
   }
   //the parse method 
   public static void parse(String[] input, Player player) {
       switch (input[0]) {
       case "go":
    	   
           player.setCurrentRoom(player.getCurrentRoom().getExit(input[1]));
           
           break;

       default:
    	   
           break;
           
       }
   }
   
   //my get string method with validations 
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
}//end of class
