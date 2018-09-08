import java.util.Scanner;
import java.util.Random;

public class BookingSystem {
    
    private static String[] typeOfRooms = {"double","queen","king"};
    private static Random r = new Random(123);
    
    //returns a random String from the above array. 
    private static String getRandomType(){
        int index = r.nextInt(typeOfRooms.length);
        return typeOfRooms[index];
    }
    //returns a random number of rooms between 5 and 50.
    private static int getRandomNumberOfRooms(){
        return r.nextInt(50)+1;
    }
    //End of provided code. 
    
    public static void main(String[] args){
        
        //Student Name: Yuzhou Guo
        //Student Number: 260715042

        int totalRooms = getRandomNumberOfRooms();
        
        Room[] roomArray = new Room[totalRooms];
        //We deicde the total number of rooms by the Random class.
        
        for (int i = 0; i<roomArray.length; i++){
            roomArray[i] = new Room(getRandomType());  
        }//Also by using the random class, assign different types to every room.
      
        Scanner readInt = new Scanner(System.in);
        Scanner readLine = new Scanner(System.in);//I create two Scanners here so that it won't miss any interaction.
       
        System.out.println("Welcome to the COMP 202 booking system.");
        System.out.println("Please enter the name of the hotel you'd like to book.");
        
        String hotelName = readLine.nextLine();
        Hotel hotel = new Hotel(hotelName, roomArray);
        printMenu(hotelName);
        
        while(true){ //The only way to exit the loop is to enter "5" when doing the interaction.

          int a = readInt.nextInt();
            if(a==5){
                System.out.println("It was a pleasure doing business with you!");
                break;
            }
            else if (a==4){
                System.out.println("Here is the hotel info:");
                System.out.println(hotel);//Since we have toString method in Hotel class, now we can just print
                printMenu(hotelName);     //the object.
            }
            else if(a==1){
                System.out.println("Please enter your name: ");
                String name = readLine.nextLine();
                System.out.println("What type of room would you like to reserve? ");
                String roomType = readLine.nextLine();
                hotel.createReservation(name, roomType);//call the method with the object, not the class name.
                printMenu(hotelName);
            }
            else if (a==2){
                System.out.println("Please enter the name you used to make the reservation: ");
                String name = readLine.nextLine();
                System.out.println("What type of room did you reserve? ");
                String roomType = readLine.nextLine();
                hotel.cancelReservation(name, roomType);//call the method with the object, not the class name.
                printMenu(hotelName);
            }
            else if (a==3){
                System.out.println("Please enter your name: ");
                String name = readLine.nextLine();
                hotel.printInvoice(name);//call the method with the object, not the class name.
                printMenu(hotelName);
            }    
        }
    }
    
    private static void printMenu (String hotelName){
        
        System.out.println("\n******************************************************************************");
        
        System.out.println("Welcome to " + hotelName + ". Chose one of the following options:");
        System.out.println("1). Make a reservation");
        System.out.println("2). Cancel a reservation");
        System.out.println("3). See an invoice");
        System.out.println("4). See hotel info");
        System.out.println("5). Exit the booking System");
        
        System.out.println("******************************************************************************");
   
    }//Write another private method here so that we don't need to print these statements again and again.
    
}