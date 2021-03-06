package fixtures;

import java.util.Arrays;
import java.util.List;

/*
 * This class represents a room in the house. It will extend fixtures.Fixture, and so will inherit the descriptive properties from Fixture. 
*/

//inherited from Fixture which is Abstract class
public class Room extends Fixture {
   private Room[] exits;
   //used list for the directions 
   private List<String> directions = Arrays.asList("north", "south", "east", "west");

   public Room(String name, String shortDescription, String longDescription) {
       super(name, shortDescription, longDescription);
       this.exits = new Room[directions.size()];
   }
   
   public Room[] getExits() {
       return exits;
   }

   public Room getExit(String direction) {
       return exits[directions.indexOf(direction)];
   }
  

   public List<String> getDirections() {
       return directions;
   }
   //setter
   public void setExits(Room[] exits) {
       this.exits = exits;
   }

   //using toString to print out the prompts 
   //example of polymorphism 
   @Override
   public String toString() {
       String exits = "";
       for (String direction : this.directions) {
           if(getExit(direction)!=null)
           exits += (direction + "\t=> " + getExit(direction).getShortDescription() + "\n");
       }
       return this.getName() + "\n\n" + this.getLongDescription() + "\n\n" + "Exits:\n" + exits;
   }

}