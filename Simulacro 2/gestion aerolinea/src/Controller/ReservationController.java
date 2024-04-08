package Controller;

import entity.Flight;
import entity.Passenger;
import entity.Reservation;
import model.FlightModel;
import model.PassengerModel;
import model.ReservationModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ReservationController {
    public ReservationModel instanceModel() {
        return new ReservationModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("RESERVATIONS REGISTERED:\n");
        if (objectsList.isEmpty()) {
            list.append("There is no appointment yet");
        } else {
            for (Object obj : objectsList) {
                Reservation objReservation = (Reservation) obj;
                list.append(objReservation.toString()).append("\n");
            }
        }
        return list;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        try {
            Date reservation_Date = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the reservation date (YYYY-MM-DD)"));
            String seat = JOptionPane.showInputDialog("Enter the seat for the airplane");
            Object[] options1 = new PassengerModel().findAll().toArray();
            if (options1.length > 0) {
                Passenger selectedOption1 = (Passenger) JOptionPane.showInputDialog(
                        null,
                        "Select the passenger of the new reservation:\n",
                        "Select a passenger",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options1,
                        options1[0]);
                if (selectedOption1 == null) {
                    JOptionPane.showMessageDialog(null, "No option selected");
                } else {
                    Object[] options2 = new FlightModel().findAll().toArray();
                    if (options2.length > 0) {
                        Flight selectedOption2 = (Flight) JOptionPane.showInputDialog(
                                null,
                                "Select the flight of the new reservation:\n",
                                "Select a flight",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options2,
                                options2[0]);
                        if (selectedOption2 == null) {
                            JOptionPane.showMessageDialog(null, "No option selected");
                        } else {
                            instanceModel().create(new Reservation(reservation_Date, seat, selectedOption1.getId_Passenger(), selectedOption2.getId_Flights()));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No reservation  registered yet");
                    }
                }


            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "format entered invalid, try again");
        }

    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Reservation selectedOption = (Reservation) JOptionPane.showInputDialog(
                    null,
                    "Select the reservarion that you want to delete:\n",
                    "Deleting a reservation",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this reservation?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Reservation successfully removed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the reservation");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No reservation was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no reservation yet");
        }
    }

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Reservation selectedOption = (Reservation) JOptionPane.showInputDialog(
                    null,
                    "Select the reservation that you want to update:\n",
                    "Updating a reservation",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to update this reservation?");
                if (confirm == 0) {
                    Date reservation_Date = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the new reservation date (YYYY-MM-DD)"));
                    String seat = JOptionPane.showInputDialog("Enter the new seat for the airplane");;
                    Object[] options1 = new PassengerModel().findAll().toArray();
                    if (options1.length > 0) {
                        Passenger selectedOption1 = (Passenger) JOptionPane.showInputDialog(
                                null,
                                "Select the new passenger of the reservation:\n",
                                "Select a passenger",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options1,
                                options1[0]);
                        if (selectedOption1 == null) {
                            JOptionPane.showMessageDialog(null, "No option selected");
                        } else {
                            Object[] options2 = new FlightModel().findAll().toArray();
                            if (options2.length > 0) {
                                Flight selectedOption2 = (Flight) JOptionPane.showInputDialog(
                                        null,
                                        "Select the new flight of the reservation:\n",
                                        "Select a flight",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        options2,
                                        options2[0]);
                                if (selectedOption2 == null) {
                                    JOptionPane.showMessageDialog(null, "No option selected");
                                } else {
                                    selectedOption.setReservation_Date(reservation_Date);
                                    selectedOption.setSeat(seat);
                                    selectedOption.setId_Passenger(selectedOption1.getId_Passenger());
                                    selectedOption.setId_Flight(selectedOption2.getId_Flights());

                                    if (instanceModel().update(selectedOption)) {
                                        JOptionPane.showMessageDialog(null, "Airplane Updated successfully");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Couldn't update the Airplane:");
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "data update cancelled");
                    }
                }
            }
        }
    }
}
