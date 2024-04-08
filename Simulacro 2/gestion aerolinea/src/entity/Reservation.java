package entity;

import java.sql.Date;

public class Reservation {
    private int id_Reservations;
    private Date reservation_Date;
    private String seat;
    private int id_Passenger;
    private int id_Flight;

    public Reservation() {
    }

    public Reservation( Date reservation_Date, String seat, int id_Passenger, int id_Flight) {
        this.reservation_Date = reservation_Date;
        this.seat = seat;
        this.id_Passenger = id_Passenger;
        this.id_Flight = id_Flight;
    }

    public int getId_Reservations() {
        return id_Reservations;
    }

    public void setId_Reservations(int id_Reservations) {
        this.id_Reservations = id_Reservations;
    }

    public Date getReservartion_Date() {
        return reservation_Date;
    }

    public void setReservation_Date(Date reservation_Date) {
        this.reservation_Date = reservation_Date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getId_Passenger() {
        return id_Passenger;
    }

    public void setId_Passenger(int id_Passenger) {
        this.id_Passenger = id_Passenger;
    }

    public int getId_Flight() {
        return id_Flight;
    }

    public void setId_Flight(int id_Flight) {
        this.id_Flight = id_Flight;
    }

    @Override
    public String toString() {
        return
                "  - ReservationID: " + id_Reservations +
                " Reservation date=" + reservation_Date +
                " Seat: " + seat  +
                " PassengerID:" + id_Passenger +
                ", FlightID: " + id_Flight;
    }
}
