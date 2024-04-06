package entity;

public class Passenger {
    private int id;
    private String first_Name;
    private String last_Name;
    private int identification;

    public Passenger() {
    }

    public Passenger(int id, String first_Name, String last_Name, int identification) {
        this.id = id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.identification = identification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String First_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", first_Name='" + first_Name + '\'' +
                ", last_Name='" + last_Name + '\'' +
                ", identification=" + identification +
                '}';
    }
}
