package entity;

import java.sql.Date;
import java.sql.Time;

public class Appointment
{
    private int id_Appointment;
    private Date date_Appointment;
    private Time time_Appointment;
    private String reason;
    private int id_Patient;
    private int id_Physician;

    public Appointment() {
    }

    public Appointment( Date date_Appointment, Time time_Appointment, String reason, int id_Patient, int id_Physician) {
        this.date_Appointment = date_Appointment;
        this.time_Appointment = time_Appointment;
        this.reason = reason;
        this.id_Patient = id_Patient;
        this.id_Physician = id_Physician;
    }

    public int getId_Appointment() {
        return id_Appointment;
    }

    public void setId_Appointment(int id_Appointment) {
        this.id_Appointment = id_Appointment;
    }

    public Date getDate_Appointment() {
        return date_Appointment;
    }

    public void setDate_Appointment(Date date_Appointment) {
        this.date_Appointment = date_Appointment;
    }

    public Time getTime_Appointment() {
        return time_Appointment;
    }

    public void setTime_Appointment(Time time_Appointment) {
        this.time_Appointment = time_Appointment;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId_Patient() {
        return id_Patient;
    }

    public void setId_Patient(int id_Patient) {
        this.id_Patient = id_Patient;
    }

    public int getId_Physician() {
        return id_Physician;
    }

    public void setId_Physician(int id_Physician) {
        this.id_Physician = id_Physician;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id_Appointment=" + id_Appointment +
                ", date_Appointment=" + date_Appointment +
                ", time_Appointment=" + time_Appointment +
                ", reason='" + reason + '\'' +
                ", id_Patient=" + id_Patient +
                ", id_Physician=" + id_Physician +
                '}';
    }
}
