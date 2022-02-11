public class Patient {
    private String FirstName;
    private String SurName;
    private int Age;
    private String City;
    private long NIC;
    private String Vaccine;

    public Patient(String FirstName){
        this.setFirstName(FirstName);
    }

    public Patient(String FirstName,String Surname,int Age,String City,long NIC,String Vaccine){
        //Constructor
        this.setFirstName(FirstName);
        this.setSurName(Surname);
        this.setAge(Age);
        this.setCity(City);
        this.setNIC(NIC);
        this.setVaccine(Vaccine);
    }

    public void Printinfo(){
        System.out.println("First name- "+FirstName);
        System.out.println("Surname- "+SurName);
        System.out.println("Age- "+Age);
        System.out.println("City- "+City);
        System.out.println("NIC- "+NIC);
        System.out.println("Vaccine- "+Vaccine);
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public long getNIC() {
        return NIC;
    }

    public void setNIC(long NIC) {
        this.NIC = NIC;
    }

    public String getVaccine() {
        return Vaccine;
    }

    public void setVaccine(String vaccine) {
        Vaccine = vaccine;
    }
}
