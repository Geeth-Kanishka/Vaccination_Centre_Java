import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VaccinationCenter {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        Booth[] booth= new Booth[6];
        String option;//to get user selected option
        int stock=150;
        initialise(booth);
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
                    VaccinationBooths(booth);
                    break; }
                case "VEB","101": {
                    emptyBooths(booth);
                    break; }
                case "APB","102": {
                    stock=AddPatient(booth,stock);
                    break; }
                case "RPB","103": {
                    VaccinationBooths(booth);
                    removePatient(booth);
                    break; }
                case "VPS","104": {
                    Sort(booth);
                    break; }
                case "SPD","105": {
                    Write(booth,stock);
                    break;}
                case "LPD","106": {
                    stock=load(booth,stock);
                    break;}
                case "VRV","107": {
                    shots(stock);
                    break;}
                case "AVS","108": {
                    stock=addshots(stock);
                    break;}
                default:
                    if (!option.equals("999")&&!option.equals("EXT")){
                        System.out.println("Invalid option\n");}
            }}
        while (!option.equals("999")&&!option.equals("EXT"));
    }

    private static void initialise(Booth[] booth) {
        /*Initialise the all the elements in booth as empty
          @param booth Array of Booth objects */
        for (int x = 0; x < booth.length; x++ ) {
            booth[x]= new Booth("empty");}
        System.out.println( "initialised");
    }

    private static void VaccinationBooths(Booth[] booth){
        /*Find Only the Booths which are occupied
          @param booth Array of Booth objects */
        int check=0;//count the total number of empty booths
        for(int x = 0; x < booth.length; x++ ){
            if(!(booth[x].getCustomerName().equals("empty"))){
                System.out.println("booth " + x + " is occupied by " + booth[x].getCustomerName());
            }
            else{
                    check ++;
            }
            if (check==booth.length) {
                System.out.println("Booths are not occupied");
            }
        }

    }
    private static void emptyBooths(Booth[] booth){
        /*Finds empty booths
          @param booth Array of Booth objects*/
        int check=0;
        for(int x = 0; x < booth.length; x++ ){
            if(booth[x].getCustomerName().equals("empty")){
                System.out.println("booth "+x+" is empty");
            }
            else{
                check++;
            }
        }
        if (check== booth.length) {
            System.out.println("All the booths are occupied");}
    }
    private static int AddPatient(Booth[] booth,int stock){
        /*Adds  new patients to the booth
          @param booth Array of Booth objects
          @param stock vaccine stock
          @return updated stock*/
        if(stock>0) {
            int test = 0;
            for (int i = 0; i < booth.length; i++) {
                if (booth[i].getCustomerName().equals("empty")) {
                    System.out.println("Enter customer name for booth " + i + ": ");
                    String customerName = input.nextLine();
                    booth[i].setCustomerName(customerName);
                    stock--;//reduce number of vaccines by 1
                    System.out.println("Patient added successfully!");
                    break;
                } else {
                    test++;
                }
            }
            if (test == booth.length) {
                System.out.println("All booths occupied");
            }
        }
        else{
            System.out.println("No vaccines left ");
        }
        return stock;
    }
    private static void removePatient(Booth[] booth){
        /*removes patient from the booth
          @param booth Array of Booth objects*/
        System.out.println("Enter booth number (0-5) or 6 to stop:" );
        int boothNumber = input.nextInt();
        input.nextLine();
        if(boothNumber<=5) {
            if (!booth[boothNumber].getCustomerName().equals("empty")) {
                booth[boothNumber].setCustomerName("empty");
                System.out.println("Patient removed successfully!");
            } else {
                System.out.println("booth " + boothNumber + " is  already empty");
            }
        }

    }
    private static void Sort(Booth[] booth){
        /*Sorts names of people in Booth array in alphabetical order
          @param booth Array of Booth objects*/
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String[] SortedArray=new String[booth.length];
        int SortedAIndex=0;//to increase the index of the new array
        for(int i=0;i< alphabet.length;i++){
            char letter=alphabet[i];
            for(int x=0;x< booth.length;x++){
                if(!booth[x].getCustomerName().equals("empty")){
                    String name=booth[x].getCustomerName().toLowerCase();
                    char firstLetter=name.charAt(0);
                    if(firstLetter==letter){
                        SortedArray[SortedAIndex]=name;
                        SortedAIndex++;
                    }
                }
            }

        }
        for(int i=0;i< SortedArray.length;i++){//Print the sorted array
            if(SortedArray[i]!= null){
                System.out.println(SortedArray[i]);
            }
        }
    }
    private static void Write(Booth[] booth,int stock) throws FileNotFoundException {
        /*Writes booth information onto an external file
        @param  booth Array of Booth objects*/
        PrintWriter Write = new PrintWriter("BoothData.txt");
        Write.println("Vaccine Stock:"+stock);
        for(int x = 0; x < booth.length; x++ ) {
            if (!booth[x].getCustomerName().equals("empty")) {
                Write.println("booth " + x + " is occupied by :" + booth[x].getCustomerName());
            }
            else{
                Write.println("booth " + x + " is :empty");
            }
        }
        Write.close();
        System.out.println("data saved successfully!");
    }
    private static int load(Booth[] booth,int stock) throws FileNotFoundException {
        /*loads data from an external file and stores them Booth
        @param  booth Array of Booth objects*/
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
            booth[x].setCustomerName(DataArray[1]);
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
    private static int addshots(int stock){
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

