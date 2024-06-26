package entity;

import java.sql.Date;

public class Patient {
    private int id_Patient;
    private String name;
    private String last_Name;
    private Date date_Birth;
    private String identity_Document;

    public Patient() {
    }

    public Patient( String name, String last_Name, Date date_Birth, String identity_Document) {
        this.name = name;
        this.last_Name = last_Name;
        this.date_Birth = date_Birth;
        this.identity_Document = identity_Document;
    }

    public int getId_Patient() {
        return id_Patient;
    }

    public void setId_Patient(int id_Patient) {
        this.id_Patient = id_Patient;
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

    public Date getDate_Birth() {
        return date_Birth;
    }

    public void setDate_Birth(Date date_Birth) {
        this.date_Birth = date_Birth;
    }

    public String getIdentity_Document() {
        return identity_Document;
    }

    public void setIdentity_Document(String identity_Document) {
        this.identity_Document = identity_Document;
    }

    @Override
    public String toString() {
        return
                "  - PatientID: " + id_Patient +
                "  Name: " + name +
                "  Last names: " + last_Name +
                "  Date birth: " + date_Birth +
                "  Identity document: " + identity_Document ;
    }
}



