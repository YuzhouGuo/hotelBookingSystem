//Student name: Yuzhou Guo
//Student ID: 260715042

//This is a reservation class, assigning the reservation part of the hotel booking system.

public class Reservation {

   private String name;
   private Room roomReserved;
   
   public Reservation (Room room, String name){//Creating a reservation object.
     this.roomReserved = room;
     this.name = name;//We have two properties for each reservation, the room and the client's name.
   }
   
   public String getName(){
     return this.name;
   }
   
   public Room getRoom(){
     return this.roomReserved; //These two method will be used later outside the class, very important.
   }

}