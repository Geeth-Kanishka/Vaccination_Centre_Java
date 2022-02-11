import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class VaccinationCenter {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        Patient[] booth= new Patient[6];
        LinkedList<Patient> WaitingList= new LinkedList<Patient>();
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
                    emptybooths(booth);
                    break; }
                case "APB","102": {
                    stock=AddPatient(booth,stock,WaitingList);
                    break; }
                case "RPB","103": {
                    VaccinationBooths(booth);
                    removePatient(booth,WaitingList,stock);
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

    private static void initialise(Patient[] booth) {
        /*Initialise the all the elements in booth as empty
          @param booth Array of Patient objects */
        for (int x = 0; x < booth.length; x++ ) {
            booth[x]= new Patient("empty");}
        System.out.println( "initialised");
    }

    private static void VaccinationBooths(Patient[] booth){
        /*Find Only the Booths which are occupied
          @param booth Array of Patient objects */
        int check=0;//count the total number of empty booths
        for(int x = 0; x < booth.length; x++ ){
            if(!(booth[x].getFirstName().equals("empty"))){
                System.out.println("booth " + x + " is occupied by " + booth[x].getFirstName());
                booth[x].Printinfo();
                System.out.println("\n.......................................\n");
            }
            else{
                check ++;
            }
            if (check==booth.length) System.out.println("Booths are not occupied");
        }

    }
    private static void emptybooths(Patient[] booth){
        /*Finds empty booths
           @param booth Array of Patient objects*/
        int check=0;
        for(int x = 0; x < booth.length; x++ ){
            if(booth[x].getFirstName().equals("empty")){
                System.out.println("booth "+x+" is empty");
            }
            else{
                check ++;
            }
            if (check==booth.length) System.out.println("All the booths are occupied");
        }
    }
    private static int AddPatient(int stock,LinkedList<Patient> WaitingList,Patient[] booth){
        /*Adds  new patients to the booth from the waiting list
          @param booth Array of Patient objects
          @param stock vaccine stock
          @param WaitingList linked list to get waiting patients
          @return updated stock*/
        for (int i=0;i< WaitingList.size();i++){
            int boothNumber=FindBooth(WaitingList.get(i).getVaccine());
            if (booth[boothNumber].getFirstName().equals("empty")) {
                booth[boothNumber]=WaitingList.get(i);
                stock--;//reduce number of vaccines by 1
                WaitingList.remove(i);
                i--;
                System.out.println("Patient added successfully!");
            }
            else if (booth[boothNumber+1].getFirstName().equals("empty")) {
                booth[boothNumber+1]=WaitingList.get(i);
                stock--;//reduce number of vaccines by 1
                WaitingList.remove(i);
                i--;
                System.out.println("Patient added from Waiting list!");
            }
        }
        return stock;
    }
    private static int AddPatient(Patient[] booth,int stock,LinkedList<Patient> WaitingList){
        /*Adds  new patients to the booth
          @param booth Array of Patient objects
          @param stock vaccine stock
          @param WaitingList linked list to add waiting patients
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
            if (boothNumber != 7) {
                if (booth[boothNumber].getFirstName().equals("empty")) {
                    booth[boothNumber] = new Patient(FirstName, SurName, Age, City, Nic, vaccineType);
                    stock--;//reduce number of vaccines by 1
                    System.out.println("Patient added successfully!");
                } else if (booth[boothNumber + 1].getFirstName().equals("empty")) {
                    booth[boothNumber + 1] = new Patient(FirstName, SurName, Age, City, Nic, vaccineType);
                    stock--;//reduce number of vaccines by 1
                    System.out.println("Patient added successfully!");
                } else {
                    Patient patientTemp = new Patient(FirstName, SurName, Age, City, Nic, vaccineType);
                    WaitingList.add(patientTemp);
                    System.out.println("all booths for this are already occupied.\nAdded to Waiting list");
                }
            }
        }
        else{
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
    private static void removePatient(Patient[] booth,LinkedList<Patient> WaitingList,int stock){
        /*removes patient from the booth
          @param booth Array of Patient objects
          @param stock vaccine stick
          @param WaitingList linked list to add waiting patients*/
        System.out.println("Enter booth number (0-5) or 6 to stop:" );
        int boothNumber = input.nextInt();
        input.nextLine();
        if(boothNumber<=5) {
            if (!booth[boothNumber].getFirstName().equals("empty")) {
                booth[boothNumber]= new Patient("empty");
                System.out.println("Patient removed successfully!");
                AddPatient(stock,WaitingList,booth);
            } else {
                System.out.println("booth " + boothNumber + " is  already empty");
            }
        }

    }
    private static void Sort(Patient[] booth){
        /*Sorts names of people in booths in alphabetical order
         @param booth Array of Patient objects
         */
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String[] SortedArray=new String[booth.length];
        int SortedAIndex=0;//to increase the index of the new array
        for(int i=0;i< alphabet.length;i++){
            char letter=alphabet[i];
            for(int x=0;x< booth.length;x++){
                if(!booth[x].getFirstName().equals("empty")){
                    String name=booth[x].getFirstName().toLowerCase();
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
    private static void Write(Patient[] booth,int stock) throws FileNotFoundException {
         /*Writes booth information onto an external file
        @param booth Array of Patient objects*/
        PrintWriter out = new PrintWriter("BoothData.txt");
        out.println("Vaccine Stock:"+stock);
        for(int x = 0; x < booth.length; x++ ) {
            if (!booth[x].getFirstName().equals("empty")) {
                out.println("booth number:"+x+":   Firstname--:" +booth[x].getFirstName()+":   Surname--:"+booth[x].getSurName()+":   Age--:"+booth[x].getAge()+":   City--:"+booth[x].getCity()+":   Nic--:"+booth[x].getNIC()+":   Vaccine--:"+booth[x].getVaccine());
            }

        }
        out.close();
        System.out.println("data saved successfully!");
    }
    private static int load(Patient[] booth,int stock) throws FileNotFoundException {
        /*loads data from an external file and stores them Booth
        @param booth Array of Patient objects*/
        initialise(booth);
        File file = new File("BoothData.txt");
        Scanner myReader = new Scanner(file);
        while(myReader.hasNextLine()){
            String data = myReader.nextLine();
            String[] DataArray=data.split(":");
            if(DataArray[0].equals("Vaccine Stock")){
                stock=Integer.parseInt(DataArray[1]);
            }
            else {
                int boothNum = Integer.parseInt(DataArray[1]);
                booth[boothNum] = new Patient(DataArray[3], DataArray[5], Integer.parseInt(DataArray[7]), DataArray[9], Long.parseLong(DataArray[11]), DataArray[13]);
            }
        }
        System.out.println("Data loaded successfully!");
        return stock;
    }
    private static void shots(int stock) {
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


