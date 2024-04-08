import Controller.AirplaneController;
import Controller.FlightController;
import Controller.PassengerController;
import Controller.ReservationController;
import database.ConfigDB;
import entity.Airplane;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option = "";
        String option2 = "";

        do {
            option = JOptionPane.showInputDialog("""
                                            
                    AIRLINE MANAGEMENT
                                            
                            ===MENU===
                          
                    1.Manage Airplanes
                    2.Manage Flights
                    3.Manage Passengers
                    4.Manage Reserations
                    5.Exit
                                            
                    Please insert an option.
                    """);
            switch (option) {
                case "1":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                                        
                                MANAGE AIRPLANES
                                                        
                                     ===MENU===
                                      
                                1.List Airplanes
                                2.Create a new Airplanes
                                3.Update Airplanes
                                4.Delete Airplanes
                                5.Exit
                                                        
                                Please insert an option.
                                """);
                        AirplaneController airplaneController = new AirplaneController();
                        switch (option2) {
                            case "1":
                                airplaneController.getAll();
                                break;
                            case "2":
                                airplaneController.create();
                                break;
                            case "3":
                                airplaneController.update();
                                break;
                            case "4":
                                airplaneController.delete();
                                break;
                            case "5":
                                JOptionPane.showMessageDialog(null, "Going back to main menu!!");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Not a valid option");
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;
               case "2":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                                        
                                MANAGE FLIGHTS
                                                        
                                     ===MENU===
                                      
                                1.List Flights
                                2.Create a new Flights
                                3.Update Flights
                                4.Delete Flights
                                5.Exit
                                                        
                                Please insert an option.
                                """);
                        FlightController flightController = new FlightController();
                        switch (option2) {
                            case "1":
                                flightController.getAll();
                                break;
                            case "2":
                                flightController.create();
                                break;
                            case "3":
                                flightController.update();
                                break;
                            case "4":
                                flightController.delete();
                                break;
                            case "5":
                                JOptionPane.showMessageDialog(null, "Going back to main menu!!");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Not a valid option");
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;
                case "3":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                                        
                                MANAGE PASSENGERS
                                                        
                                     ===MENU===
                                      
                                1.List Passenger
                                2.Create a new Passenger
                                3.Update Passenger
                                4.Delete Passenger
                                5.Exit
                                                        
                                Please insert an option.
                                """);
                        PassengerController passengerController = new PassengerController();
                        switch (option2) {
                            case "1":
                                passengerController.getAll();
                                break;
                            case "2":
                                passengerController.create();
                                break;
                            case "3":
                                passengerController.update();
                                break;
                            case "4":
                                passengerController.delete();
                                break;
                            case "5":
                                JOptionPane.showMessageDialog(null, "Going back to main menu!!");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Not a valid option");
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;
                case "4":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                                        
                                MANAGE RESERVATIONS
                                                        
                                     ===MENU===
                                      
                                1.List Reservations
                                2.Create a new Reservations
                                3.Update Reservations
                                4.Delete Reservations
                                5.Exit
                                                        
                                Please insert an option.
                                """);
                        ReservationController reservationsController = new ReservationController();
                        switch (option2) {
                            case "1":
                                reservationsController.getAll();
                                break;
                            case "2":
                                reservationsController.create();
                                break;
                            case "3":
                                reservationsController.update();
                                break;
                            case "4":
                                reservationsController.delete();
                                break;
                            case "5":

                                JOptionPane.showMessageDialog(null, "Going back to main menu!!");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Not a valid option");
                                break;
                        }


                    } while (!option2.equals("5"));
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "See you next time!!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not a valid option");
                    break;
            }
        } while (!option.equals("5"));

    }
}