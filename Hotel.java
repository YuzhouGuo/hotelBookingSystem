//Student name: Yuzhou Guo
//Student ID: 260715042

//This is a Hotel class, assigning the hotel part of the hotel booking system.

import java.util.NoSuchElementException;

public class Hotel {

   private String name;
   private Room[] roomArray;
   private Reservation[] reservationArray;
   //A huge number of rooms is only one of the systems of a hotel, a very important system is the reservation system.
   //which is closely related to the room system.
   
   public Hotel (String hotelName, Room[] roomArray){
   
       this.name = hotelName;
       this.roomArray = roomArray;
       this.reservationArray = new Reservation[0];//Before any reservation, the reservation number is just zero.
   
   }
   
   public void createReservation (String name, String type){
       Room r = Room.findAvailableRoom(this.roomArray, type);
       //Use the room array we just created to find the type of room which the client wants, this method is from 
       //the Room class and it returns a room object back here.
       
       if(r==null){
           System.out.println("Sorry the hotel has no available rooms of your request.");
       }
       else{
           Reservation newReservation = new Reservation(r, name);//Then put this room into the reservation array.
           r.changeAvailability();//Set the room availability to false, since it is booked.
           addReservation(newReservation);//Add the reservation we just created to the reservation array.
           System.out.println("You have successfully reserved a " + type + " room under the name of "+name+".");
           System.out.println("We look forward having you at " + this.name + "!");
       }
   }
   
   public void cancelReservation (String name, String type){
       try {
           removeReservation(name, type);//Remove the reservation from the array, actually need a new array.
           System.out.println(name+", you have successfully cancelled the " +type+ " room reservation.");
     
       }
       catch(Exception noRoomToCancel){//just in case there's an exception, we use the "try and catch" method.
           System.out.println("No reservation under name "+name+" has been made for the "+type+" size room.");
       }
   }
   
   private void addReservation (Reservation aReservation){
    
       Reservation[] newReservationArray = new Reservation[this.reservationArray.length +1];
       
       for (int i = 0; i<this.reservationArray.length; i++){
           newReservationArray[i] = this.reservationArray[i]; 
       }
       //here we simply copy all the information from the previous array to our new array.
       //Except for the last space.
       
       newReservationArray[this.reservationArray.length] = aReservation;
       this.reservationArray = newReservationArray;
       
   }
   
   private void removeReservation (String name, String type){
        
       int k = 0;//We will use these two variabled in this whole method, better to declare them in the beginning.
       int b = 0;
       int counter = 0;
         
       if (this.reservationArray.length!=0){ //If there's no reservation under the name of the client, we can just 
         for (int i = 0; i<this.reservationArray.length; i++){        //skip everything and throw the exception.
         
           String clientName = this.reservationArray[i].getName();
           String roomReservedType = this.reservationArray[i].getRoom().getType();
         
           if (((clientName).equals(name))&&((roomReservedType).equals(type))){
               k = i;
               counter++;
               break; //find that reservation from the array, note the index and break the loop.
           }
         }
         
         if(counter==0){
           throw new NoSuchElementException ("Sorry you don't have this reservation.");
         }
         //This step is used to make sure we are finding the room with the right name.
             
         Reservation[] newReservationArray = new Reservation[this.reservationArray.length -1];
         
         for (int a = 0; a<=(this.reservationArray.length-1); a++){
           if (a==k){
              this.reservationArray[a].getRoom().changeAvailability();
              continue; //change the room availability and skip this reservation (won't be inside the new array).
              } 
            else{
               if (b<(this.reservationArray.length-1)){  
                  newReservationArray[b] = this.reservationArray[a];
                  b++; //we need the b variable here because the index of the new array is different from the previous
                       //array, also we don't know the index of the room the client want to cancel.
              }
            }
          }
          this.reservationArray = newReservationArray;//remember to assign the new reservation array. 
       }
       else {
           throw new NoSuchElementException ("Sorry you don't have this reservation.");
       }
   }
   
   public void printInvoice (String name){
       double total = 0.0;
       for (int i = 0; i<this.reservationArray.length; i++){
         if((this.reservationArray[i].getName()).equals(name)){
           total += this.reservationArray[i].getRoom().getPrice();
         } //The getPrice() method is very useful here, so that we don't need to care about the type of rooms and
           //use a bunch of if conditions.
       }
       System.out.println(name+"'s invoice is of $"+total);
       //If the client has no reservation, the invoice here will be 0.0.
   }
   
   public String toString(){
       int doubleR = 0;
       int queenR = 0;
       int kingR = 0; //We add a toString method in this class, so that we can just print the information we want 
                      //to show when we say System.out.println(object);
       
       for (int i = 0; i<this.roomArray.length; i++){
         if(this.roomArray[i].getAvailability()==true){
           if((this.roomArray[i].getType()).equals("double")){
             doubleR++;
           }
           if((this.roomArray[i].getType()).equals("queen")){
             queenR++;
           }
           if((this.roomArray[i].getType()).equals("king")){
             kingR++;
           }
         }
       }
       
       return "Hotel name: "+this.name+"\nAvailable Rooms: "+doubleR+" double "+queenR+" queen "+kingR+" king.";
   }
}