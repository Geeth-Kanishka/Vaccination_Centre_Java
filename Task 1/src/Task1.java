import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Task1 {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        String[] Booth = new String[6];
        String option;//to get user selected option
        int stock=150;
        initialise(Booth);
        do{ System.out.println("\n100 or VVB:  View all Vaccination Booths\n"+
                             "101 or VEB:  View all Empty Booths\n"+
                             "102 or APB:  Add Patient to a Booth\n"+
                             "103 or RPB:  Remove Patient from a Booth\n"+
                             "104 or VPS:  View Patients Sorted in alphabetical order\n"+
                             "105 or SPD:  Store Program Data into file\n"+
                             "106 or LPD:  Load Program Data from file\n"+
                             "107 or VRV: View Remaining Vaccinations\n"+
                             "108 or AVS: Add Vaccinations to the Stock\n"+
                             "999 or EXT: Exit the Program \n"
            );
        System.out.println("Enter your choice:");
        option=input.nextLine().toUpperCase();
        switch (option){
            case "VVB","100": {
                VaccinationBooths(Booth);
                break; }
            case "VEB","101": {
                emptyBooths(Booth);
                break; }
            case "APB","102": {
                stock=AddPatient(Booth,stock);
                break; }
            case "RPB","103": {
                VaccinationBooths(Booth);
                removePatient(Booth);
                break; }
            case "VPS","104": {
                Sort(Booth);
                break; }
            case "SPD","105": {
                Write(Booth,stock);
                break;}
            case "LPD","106": {
                stock=load(Booth,stock);
                break;}
            case "VRV","107": {
                shots(stock);
                break;}
            case "AVS","108": {
                stock=addShots(stock);
                break;}
            default:
                if (!option.equals("999")&&!option.equals("EXT")){
                    System.out.println("Invalid option\n");}
        }}
        while (!option.equals("999")&&!option.equals("EXT"));
    }

    private static void initialise( String Booth[] ) {
        /*Initialise the all the elements in booth as empty
          @param Booth Array that holds patient names */
        for (int x = 0; x < Booth.length; x++ ) {
            Booth[x] = "empty";}
        System.out.println( "initialised");
    }

    private static void VaccinationBooths(String[] Booth){
        /*Find Only the Booths which are occupied
          @param Booth Array that holds patient names */
        int check=0;//count the total number of empty booths
        for(int x = 0; x < Booth.length; x++ ){
            if(!(Booth[x].equals("empty"))) {
                System.out.println("booth " + x + " is occupied by " + Booth[x]);}
            else{
                check ++;}

            if (check==Booth.length) {System.out.println("Booths are not occupied");}
        }
    }

    private static void emptyBooths(String Booth[]){
        /*Finds empty booths
          @param Booth Array that holds patient names*/
        int check=0;
        for(int x = 0; x < Booth.length; x++ ){
            if(Booth[x].equals("empty")){
                System.out.println("booth "+x+" is empty");
            }
            else{
                check++;
            }
        }
        if (check== Booth.length) {
            System.out.println("All the booths are occupied");}
    }

    private static int AddPatient(String[] Booth, int stock){
        /*Adds  new patients to the booth
          @param Booth Array that holds patient names
          @param stock vaccine stock
          @return updated stock*/
        if(stock>0) {
            int test = 0;
            for (int i = 0; i < Booth.length; i++) {
                if (Booth[i].equals("empty")) {
                    System.out.println("Enter customer name for booth " + i + ": ");
                    String customerName = input.nextLine();
                    Booth[i] = customerName;
                    stock--;//reduce number of vaccines by 1
                    System.out.println("Patient added successfully!");
                    break;
                } else {
                    test++;
                }
            }
            if (test == Booth.length) {
                System.out.println("All booths occupied");
            }
        }
        else{
            System.out.println("No vaccines left ");
        }
        return stock;
    }
    private static void removePatient(String Booth[]){
        /*removes patient from the booth
          @param Booth Array that holds patient names*/
        System.out.println("Enter booth number (0-5) or 6 to stop:" );
        int boothNumber = input.nextInt();
        input.nextLine();
        if(boothNumber<=5) {
            if (!Booth[boothNumber].equals("empty")) {
                Booth[boothNumber] = "empty";
                System.out.println("Patient removed successfully!");
            } else {
                System.out.println("booth " + boothNumber + " is  already empty");
            }
        }

    }
    private static void Sort(String Booth[]){
        /*Sorts names of people in Booth array in alphabetical order
          @param Booth Array that holds patient names*/
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String[] SortedArray=new String[Booth.length];
        int SortedArrIndex=0;//to increase the index of the new array
        for(int i=0;i< alphabet.length;i++){
            char letter=alphabet[i];
            for(int x=0;x< Booth.length;x++){
                if(!Booth[x].equals("empty")){
                    String name=Booth[x].toLowerCase();
                    char firstLetter=name.charAt(0);
                    if(firstLetter==letter){
                        SortedArray[SortedArrIndex]=name;
                        SortedArrIndex++;
                    }
                }
            }
        }
        for(int i=0;i< SortedArray.length;i++){
            if(SortedArray[i]!= null){
                System.out.println(SortedArray[i]);
            }
        }
    }
    private static void Write(String Booth[],int stock) throws FileNotFoundException {
        /*Writes booth information onto an external file
        @param Booth Array that holds patient names*/
        PrintWriter Write = new PrintWriter("BoothData.txt");
        Write.println("Vaccine Stock:"+stock);
        for(int x = 0; x < Booth.length; x++ ) {
            if (!Booth[x].equals("empty")) {
                Write.println("booth " + x + " is occupied by :" + Booth[x]);
            }
            else{
                Write.println("booth " + x + " is :empty");
            }
        }
        Write.close();
        System.out.println("data saved successfully!");
    }
    private static int load(String Booth[],int stock) throws FileNotFoundException {
        /*loads data from an external file and stores them Booth
        @param Booth Array that holds patient names*/
        File file = new File("BoothData.txt");
        Scanner myReader = new Scanner(file);
        int x=0;
        while(myReader.hasNextLine()){
            String data = myReader.nextLine();
            String[] DataArray=data.split(":");
            if(DataArray[0].equals("Vaccine Stock")){
                stock=Integer.parseInt(DataArray[1]);
            }
            else{
            Booth[x] = DataArray[1];
            x++;}
        }
        System.out.println("Data loaded successfully!");
        return stock;
    }
    private static void shots(int stock){
        /*Prints number of vaccines left in stock
        @param vaccine stock
         */
        System.out.println(stock+" vaccines  left");

    }
    private static int addShots(int stock){
        /*adds more vaccines to the stock
        @param vaccine stock
        @return updated stock*/
        System.out.println("Number of vaccines to add?");
        int add= input.nextInt();
        input.nextLine();
        stock+=add;
        System.out.println("Stock updated\n"+stock+" vaccines  left");
        return stock;
    }

}

