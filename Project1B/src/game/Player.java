package game;

import fixtures.Room;

//This class represents the player moving through rooms.

public class Player {
   private Room currentRoom;

   public Player(Room currentRoom) {
       super();
       this.currentRoom = currentRoom;
   }
   //getter 
   public Room getCurrentRoom() {
       return currentRoom;
   }
   //setter
   public void setCurrentRoom(Room currentRoom) {
       this.currentRoom = currentRoom;
   }

}
