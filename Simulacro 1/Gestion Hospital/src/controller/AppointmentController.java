package controller;

import entity.Appointment;
import entity.Patient;
import entity.Physician;
import model.AppointmentModel;
import model.PatientModel;
import model.PhysicianModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class AppointmentController {

    public AppointmentModel instanceModel() {
        return new AppointmentModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("APPOINTMENT REGISTERED:\n");
        if (objectsList.isEmpty()) {
            list.append("There is no appointment yet");
        } else {
            for (Object obj : objectsList) {
                Appointment objAppointment = (Appointment) obj;
                list.append(objAppointment.toString()).append("\n");
            }
        }
        return list;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        try{Date date_Appointment = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the appointment date (YYYY-MM-DD)"));

            Time time_Appointment = Time.valueOf(JOptionPane.showInputDialog(null, "Enter the appointment time in 24-hour format (HH:MM:SS)"));
            String reason = JOptionPane.showInputDialog("Enter the reason for the appointment");
            Object[] options1 = new PatientModel().findAll().toArray();
            if (options1.length > 0) {
                Patient selectedOption1 = (Patient) JOptionPane.showInputDialog(
                        null,
                        "Select the patient of the new appointment:\n",
                        "Select a patient",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options1,
                        options1[0]);
                if (selectedOption1 == null) {
                    JOptionPane.showMessageDialog(null, "No option selected");
                } else {
                    Object[] options2 = new PhysicianModel().findAll().toArray();
                    if (options2.length > 0) {
                        Physician selectedOption2 = (Physician) JOptionPane.showInputDialog(
                                null,
                                "Select the Physician of the new appointment:\n",
                                "Select a physician",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options2,
                                options2[0]);
                        if (selectedOption2 == null) {
                            JOptionPane.showMessageDialog(null, "No option selected");
                        }else {
                            instanceModel().create(new Appointment(date_Appointment, time_Appointment, reason, selectedOption1.getId_Patient(), selectedOption2.getId_Physicians()));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No appointment registered yet");
                    }
                }


            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"format entered invalid, try again");
        }

    }
    public void delete() {
        StringBuilder List = getAll((instanceModel().findAll()));
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Appointment selectedOption = (Appointment) JOptionPane.showInputDialog(
                    null,
                    "Select the appointment that you want to delete:\n",
                    "Deleting a appointment",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this appointment?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Appointment successfully removed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the appointment");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No appointment was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no appointment yet");
        }
    }
}
