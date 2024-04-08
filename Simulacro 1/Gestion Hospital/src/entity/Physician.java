package entity;

public class Physician {
    private int id_Physician;
    private String name;
    private String last_name;
    private int id_Specialty;


    public Physician() {
    }

    public Physician(String name, String last_name, int id_Specialty) {
        this.name = name;
        this.last_name = last_name;
        this.id_Specialty = id_Specialty;
    }

    public int getId_Physicians() {
        return id_Physician;
    }

    public void setId_Physician(int id_Physician) {
        this.id_Physician = id_Physician;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getId_Specialty() {
        return id_Specialty;
    }

    public void setId_Specialty(int id_Specialty) {
        this.id_Specialty = id_Specialty;
    }

    @Override
    public String toString() {
        return
                "  - PhysicianID: " + id_Physician +
                        "  Name:  " + name +
                        "  Last names: " + last_name +
                        "  SpecialtyID: " + id_Specialty;
    }
}
