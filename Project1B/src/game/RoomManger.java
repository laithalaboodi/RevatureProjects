package game;

import java.util.List;

import fixtures.Room;

/*
 * This class will be responsible for "loading" our rooms into memory. 
 * When game.Main is executed, it will invoke the init() method in this class that will instantiate all our Room objects, 
 * link them together as exits, and designate a startingRoom.
 */

public class RoomManger {
   Room startingRoom;
   Room[] rooms;
   //Init method is a predefined method to initialize an object after its creation
   public void init() {
	   							
       Room livingRoom = new Room("Living Room", "Big Living Room",
               "The living room is big and you can see 4 exits\n"
                       + "To the east you can see a random dooor that you dont know where it will lead to\n"
                       + "to the west you can go to the storage and to north you can go to the study room.\n" +"To the south there is small kitchen.");
       
       Room kitchen = new Room("The Kitchen", " small kitchen",
               "The kitchen have a dining table. The living room is open to the North.\n"
                       + "to the west there is a empty storage");
       
       Room studyRoom = new Room("The Study Room", " a cozy study room   ",
               "This room has a table and white boards for study. \n"
                       + "To the west there is a storage and to\n"
                       + "the south there is the big living room.");
       
       Room dungeon = new Room("The dungeon", " Random Door",
               "Has monsters run away or you will end up being here!!!!!!!!\n");
       
       Room storage = new Room("The Storage", " an storage door",
               "This is a storage and its empty.\n");
       
       List<String> directions = livingRoom.getDirections();
       rooms = new Room[directions.size()];
       
       //Here I am starting in the livingRoom   
       this.startingRoom = livingRoom;

       //logic for starting room
       rooms[directions.indexOf("south")] = kitchen;
       rooms[directions.indexOf("north")] = studyRoom;
       rooms[directions.indexOf("west")] = storage;
       rooms[directions.indexOf("east")] = dungeon;
       livingRoom.setExits(rooms);
       
       //logic for library 
       rooms = new Room[directions.size()];
       rooms[directions.indexOf("south")] = livingRoom;
       rooms[directions.indexOf("west")] = storage;
       studyRoom.setExits(rooms);
       
       // logic for dungeon room
       rooms = new Room[directions.size()];
       rooms[directions.indexOf("west")] = livingRoom;
       //rooms[directions.indexOf("west")] = interiorDoor;
       dungeon.setExits(rooms);
       
       //logic for the dining room
       rooms = new Room[directions.size()];
       rooms[directions.indexOf("north")] = livingRoom;
       rooms[directions.indexOf("west")] = storage;
       kitchen.setExits(rooms);
       
       //logic for the interior door 
       rooms = new Room[directions.size()];
       rooms[directions.indexOf("east")] = livingRoom;
       //rooms[directions.indexOf("west")] = interiorDoor;
       storage.setExits(rooms);
   }
}
