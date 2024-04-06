package entity;

public class Specialty {
private int id_Specialty;
private String name;
private String description;

    public Specialty() {
    }

    public Specialty(int id_Specialty, String name, String description) {
        this.id_Specialty = id_Specialty;
        this.name = name;
        this.description = description;
    }

    public int getId_Specialty() {
        return id_Specialty;
    }

    public void setId(int id_Specialty) {
        this.id_Specialty = id_Specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id_Specialty=" + id_Specialty +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
