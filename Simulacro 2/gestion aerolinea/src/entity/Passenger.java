package entity;

public class Passenger {
    private int id_Passenger;
    private String name;
    private String last_Name;
    private String identification;

    public Passenger() {
    }

    public Passenger( String first_Name, String name, String identification) {
        this.name = name;
        this.last_Name = last_Name;
        this.identification = identification;
    }

    public int getId_Passenger() {
        return id_Passenger;
    }

    public void setId_Passenger(int id_Passenger) {
        this.id_Passenger = id_Passenger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Override
    public String toString() {
        return
                "  - PassengerID: " + id_Passenger +
                " First name: " + name +
                " Last names: " + last_Name +
                " Identification: " + identification;
    }
}
