
import controller.AppointmentController;
import controller.PatientController;
import controller.PhysicianController;
import controller.SpecialtyController;
import entity.Appointment;
import model.PhysicianModel;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String option = "";
        String option2 = "";

        do {
            option = JOptionPane.showInputDialog("""
                                            
                    HOSPITAL MANAGEMENT
                                            
                            ===MENU===
                          
                    1.Manage Specialties
                    2.Manage Physicians
                    3.Manage Patients
                    4.Manage Appointments
                    5.Exit
                                            
                    Please insert an option.
                    """);
            switch (option) {
                case "1":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                                        
                                MANAGE SPECIALTIES
                                                        
                                     ===MENU===
                                      
                                1.List Specialty
                                2.Create a new specialty
                                3.Update Specialty
                                4.Delete SSpecialty
                                5.Exit
                                                        
                                Please insert an option.
                                """);
                        SpecialtyController specialtyController = new SpecialtyController();
                        switch (option2) {
                            case "1":
                                specialtyController.getAll();
                                break;
                            case "2":
                                specialtyController.create();
                                break;
                            case "3":
                                specialtyController.update();
                                break;
                            case "4":
                                specialtyController.delete();
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
                                                        
                                MANAGE PHYSICIANS
                                                        
                                     ===MENU===
                                      
                                1.List Physicians
                                2.Create a new Physicians
                                3.Update Physicians
                                4.Delete Physicians
                                5.Exit
                                                        
                                Please insert an option.
                                """);
                        PhysicianController physicianController = new PhysicianController();
                        switch (option2) {
                            case "1":
                                physicianController.getAll();
                                break;
                            case "2":
                                physicianController.create();
                                break;
                            case "3":
                                physicianController.update();
                                break;
                            case "4":
                                physicianController.delete();
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
                                                        
                                MANAGE PATIENTS
                                                        
                                     ===MENU===
                                      
                                1.List Patients
                                2.Create a new Patients
                                3.Update Patients
                                4.Delete Patients
                                5.Exit
                                                        
                                Please insert an option.
                                """);
                        PatientController patientController = new PatientController();
                        switch (option2) {
                            case "1":
                                patientController.getAll();
                                break;
                            case "2":
                                patientController.create();
                                break;
                            case "3":
                                patientController.update();
                                break;
                            case "4":
                                patientController.delete();
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
                                                        
                                MANAGE APPOINTMENTS
                                                        
                                     ===MENU===
                                      
                                1.List Appointments
                                2.Create a new Appointments
                                3.Update Appointments
                                4.Delete Appointments
                                5.Exit
                                                        
                                Please insert an option.
                                """);
                        AppointmentController appointmentController = new AppointmentController();
                        switch (option2) {
                            case "1":
                                appointmentController.getAll();
                                break;
                            case "2":
                                appointmentController.create();
                                break;
                            case "3":
                                appointmentController.update();
                                break;
                            case "4":
                                appointmentController.delete();
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