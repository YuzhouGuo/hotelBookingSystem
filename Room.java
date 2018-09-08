//Student name: Yuzhou Guo
//Student ID: 260715042

//This is a room class, assigning the basic information about rooms in a hotel.

public class Room {
  
   private String type;
   private double price;
   private boolean availability;
   
   private static final double DOUBLE_PRICE = 90.0;
   private static final double QUEEN_PRICE = 110.0;
   private static final double KING_PRICE = 150.0;
   //Since the price of the rooms won't change, we can make them all final constants.
  
   public Room (String type){
    
      boolean oneOfThree = ((type).equals("double"))||((type).equals("queen"))||((type).equals("king"));
      //Remember we cannot use "==" to compare Strings.
      
      if(!oneOfThree){
        throw new IllegalArgumentException ("Sorry there's no such type of room provided.");
      }
      else {
        this.type = type;
      }
    
      if ((type).equals("double")){
        this.price = DOUBLE_PRICE; //Assign the final constant to the object(room) we just created.
      }
      else if ((type).equals("queen")){
        this.price = QUEEN_PRICE;
      }
      else if ((type).equals("king")){
        this.price = KING_PRICE;
      }
    
      this.availability = true; //Set the room availability to true at first.
   }
  
   public String getType (){
      return this.type; //provide get method so that we can see the type of particular room outside this class.
   }
   
   public double getPrice (){
      return this.price;
   }
   
   public boolean getAvailability (){
      return this.availability;
   }
   
   public void changeAvailability (){
      if (this.availability==true){
         this.availability = false;
      }
      else {
         this.availability = true; //So that we can use this method for both create and cancel reservation.
      }
   }
   
   public static Room findAvailableRoom (Room[] roomArray, String type){
     
     for (int i = 0; i<roomArray.length; i++){
       String typeFound = roomArray[i].getType(); //Not necessary but makes everything clear.
       boolean same = typeFound.equals(type);
       
       if(same && (roomArray[i].getAvailability()==true)){
          return roomArray[i];
       }//We need to make sure that not only this is the room client is looking for, but also it is available.
     }
     return null;
   }


}