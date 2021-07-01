package game;

import fixtures.Room;

//This class represents the player moving through these rooms.

public class Player {
   private Room currentRoom;

   public Player(Room currentRoom) {
       super();
       this.currentRoom = currentRoom;
   }

   public Room getCurrentRoom() {
       return currentRoom;
   }

   public void setCurrentRoom(Room currentRoom) {
       this.currentRoom = currentRoom;
   }

}
