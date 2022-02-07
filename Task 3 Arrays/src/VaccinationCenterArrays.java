import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VaccinationCenterArrays {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        String[] firstName= new String[6];
        String[] surName= new String[6];
        String[] city= new String[6];
        String[] VaccineRequest= new String[6];
        int[] Age= new int[6];
        long[] Nic= new long[6];
        String option;//to get user selected option
        int stock=150;
        initialise(firstName);
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
                    VaccinationBooths(firstName);
                    break; }
                case "VEB","101": {
                    emptybooths(firstName);
                    break; }
                case "APB","102": {
                    stock=AddPatient(firstName,surName,Age,city,Nic,VaccineRequest,stock);
                    break; }
                case "RPB","103": {
                    VaccinationBooths(firstName);
                    removePatient(firstName,surName,Age,city,Nic,VaccineRequest,stock);
                    break; }
                case "VPS","104": {
                    Sort(firstName);
                    break; }
                case "SPD","105": {
                    Write(firstName,surName,Age,city,Nic,VaccineRequest,stock);
                    break;}
                case "LPD","106": {
                    stock=load(firstName,surName,Age,city,Nic,VaccineRequest,stock);
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

    private static void initialise(String[] firstName) {
        /*Initialise the all the elements in array as empty
          @param firstName Array that holds patient first names */
        for (int x = 0; x < firstName.length; x++ ) {
            firstName[x] = "empty"; }
        System.out.println( "initialised");
    }
    private static void VaccinationBooths(String[] firstName){
        /*Find Only the Booths which are occupied using the firstName array
          @param firstName Array that holds patient first names */
        int check=0;//count the total number of empty booths
        for(int x = 0; x < firstName.length; x++ ){
            if(!(firstName[x].equals("empty"))) {
                System.out.println("booth " + x + " is occupied by " + firstName[x]);
            }
            else{
                check ++;
            }
            if (check==firstName.length) {System.out.println("Booths are not occupied");}
        }
    }

    private static void emptybooths(String[] firstName){
        /*Finds empty booths
          @param firstName Array that holds patient first names*/
        int check=0;
        for(int x = 0; x < firstName.length; x++ ){
            if(firstName[x].equals("empty")){
                System.out.println("booth "+x+" is empty");
            }
            else{
                check ++;
            }
            if (check==firstName.length) {
                System.out.println("All the booths are occupied");}
        }
    }
    private static int AddPatient(String[] firstName, String[] surName, int[] age, String[] city, long[] nic, String[] vaccineRequest, int stock){
        /*Adds  new patients to the booth
          @param firstName Array that holds patient first names
          @param surName Array that holds patient Surnames
          @param age Array that holds patient age
          @param city Array that holds patient city
          @param nic Array that holds patient NIC
          @param vaccineRequest Array that holds patient Vaccine
          @param stock vaccine stock
          @return updated stock*/
        if(stock>0) {
            System.out.println("Enter Vaccination request:");
            String vaccineType = input.nextLine();
            System.out.println("Enter First Name: ");
            String FirstName = input.nextLine();
            System.out.println("Enter SurName: ");
            String SurName = input.nextLine();
            System.out.println("Enter age: ");
            int Age = input.nextInt();
            input.nextLine();
            System.out.println("Enter City: ");
            String City = input.nextLine();
            System.out.println("Enter NIC: ");
            long Nic = input.nextLong();
            input.nextLine();
            int boothNumber = FindBooth(vaccineType);
            if (boothNumber !=7 ) {

                if (firstName[boothNumber].equals("empty")) {
                    firstName[boothNumber] = FirstName;
                    surName[boothNumber] = SurName;
                    age[boothNumber] = Age;
                    city[boothNumber] = City;
                    nic[boothNumber] = Nic;
                    vaccineRequest[boothNumber] = vaccineType;
                    stock--;//reduce number of vaccines by 1
                    System.out.println("Patient added successfully!");
                } else if (firstName[boothNumber + 1].equals("empty")) {
                    firstName[boothNumber + 1] = FirstName;
                    surName[boothNumber + 1] = SurName;
                    age[boothNumber + 1] = Age;
                    city[boothNumber + 1] = City;
                    nic[boothNumber + 1] = Nic;
                    vaccineRequest[boothNumber + 1] = vaccineType;
                    stock--;//reduce number of vaccines by 1
                    System.out.println("Patient added successfully!");
                } else {
                    System.out.println("all booths for this vaccine are already occupied");
                }
            }
        }
        else {
            System.out.println("No vaccines left ");
        }
        return stock;


    }
    public static int FindBooth(String Vaccine){
         /*Finds the booth related to the vaccine
         @param vaccine vaccine request
         */
        int booth = 7;
        if(Vaccine.equalsIgnoreCase("astrazeneca")){
            booth= 0;
        }
        else if(Vaccine.equalsIgnoreCase("sinopharm")){
            booth= 2;
        }
        else if(Vaccine.equalsIgnoreCase("pfizer")){
            booth= 4;
        }
        else{
            System.out.println("Invalid vaccine");
        }
        return booth;


    }
    private static void removePatient(String[] firstName, String[] surName, int[] age, String[] city, long[] nic, String[] vaccineRequest, int stock){
        /*removes patient from the booth
          @param firstName Array that holds patient first names
          @param surName Array that holds patient Surnames
          @param age Array that holds patient age
          @param city Array that holds patient city
          @param nic Array that holds patient NIC
          @param vaccineRequest Array that holds patient Vaccine
          @param stock vaccine stock */
        System.out.println("Enter booth number (0-5) or 6 to stop:" );
        int boothNumber = input.nextInt();
        input.nextLine();
        if(boothNumber<=5) {
            if (!firstName[boothNumber].equals("empty")) {
                firstName[boothNumber]="empty";
                surName[boothNumber]=null;
                age[boothNumber]=0;
                city[boothNumber]=null;
                nic[boothNumber]=0;
                vaccineRequest[boothNumber]=null;
                System.out.println("Patient removed successfully!");
            } else {
                System.out.println("booth " + boothNumber + " is  already empty");
            }
        }

    }
    private static void Sort(String firstName[]){
        /*Sorts names of people in booths in alphabetical order
         @param firstName Array of Patient names
         */
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String[] SortedArray=new String[firstName.length];
        int SortedAIndex=0;//to increase the index of the new array
        for(int i=0;i< alphabet.length;i++){
            char letter=alphabet[i];
            for(int x=0;x< firstName.length;x++){
                if(!firstName[x].equals("empty")){
                    String name=firstName[x].toLowerCase();
                    char firstLetter=name.charAt(0);
                    if(firstLetter==letter){
                        SortedArray[SortedAIndex]=name;
                        SortedAIndex++;
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
    private static void Write(String[] firstName, String[] surName, int[] age, String[] city, long[] nic, String[] vaccineRequest,int stock) throws FileNotFoundException {
        /*Writes booth information onto an external file
          @param firstName Array that holds patient first names
          @param surName Array that holds patient Surnames
          @param age Array that holds patient age
          @param city Array that holds patient city
          @param nic Array that holds patient NIC
          @param vaccineRequest Array that holds patient Vaccine
          */
        PrintWriter out = new PrintWriter("BoothData.txt");
        out.println("Vaccine Stock:"+stock);
        for(int x = 0; x < firstName.length; x++ ) {
            if (!firstName[x].equals("empty")) {
                out.println("booth number:"+x+":   Firstname--:" +firstName[x]+":   Surname--:"+surName[x]+":   Age--:"+age[x]+":   City--:"+city[x]+":   Nic--:"+nic[x]+":   Vaccine--:"+vaccineRequest[x]);
            }
            else{
                out.println("booth " + x + " is :empty");
            }


        }
        out.close();
        System.out.println("data saved successfully!");

    }
    private static int load(String[] firstName, String[] surName, int[] age, String[] city, long[] nic, String[] vaccineRequest, int stock) throws FileNotFoundException {
        /*loads data from an external file and stores them Booth
          @param firstName Array that holds patient first names
          @param surName Array that holds patient Surnames
          @param age Array that holds patient age
          @param city Array that holds patient city
          @param nic Array that holds patient NIC
          @param vaccineRequest Array that holds patient Vaccine
          */
        File file = new File("BoothData.txt");
        Scanner myReader = new Scanner(file);
        initialise(firstName);
        while(myReader.hasNextLine()){
            String data = myReader.nextLine();
            String[] DataArray=data.split(":");
            if(DataArray[0].equals("Vaccine Stock")){
                stock=Integer.parseInt(DataArray[1]);
            }
            else{
                if(DataArray.length>2){
                int booth=Integer.parseInt(DataArray[1]);
                firstName[booth]=DataArray[3];
                surName[booth]=DataArray[5];
                age[booth]=Integer.parseInt(DataArray[7]);
                city[booth]=DataArray[9];
                nic[booth]=Long.parseLong(DataArray[11]);
                vaccineRequest[booth]=DataArray[13];
                }

            }
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

