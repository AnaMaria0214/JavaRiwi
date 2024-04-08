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
        try {
            Date date_Appointment = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the appointment date (YYYY-MM-DD)"));

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
                        } else {
                            instanceModel().create(new Appointment(date_Appointment, time_Appointment, reason, selectedOption1.getId_Patient(), selectedOption2.getId_Physicians()));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No appointment registered yet");
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

    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Appointment selectedOption = (Appointment) JOptionPane.showInputDialog(
                    null,
                    "Select the appointment that you want to update:\n",
                    "Updating a appointment",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to update this appointment?");
                if (confirm == 0) {
                    Date date_Appointment = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the new date of the appointment ", selectedOption.getDate_Appointment()));
                    Time Time_Appointment = Time.valueOf(JOptionPane.showInputDialog(null, "Enter the new time of the appointment ", selectedOption.getTime_Appointment()));
                    String reason = JOptionPane.showInputDialog(null, "Enter the new reason for the appointment");
                    Object[] options_id_Patient = new PatientModel().findAll().toArray();
                    if (options_id_Patient.length > 0) {
                        Patient selectedOption_id_Patient = (Patient) JOptionPane.showInputDialog(
                                null,
                                "Select the new patient for the appointment:\n",
                                "Updating the patient",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options_id_Patient,
                                options_id_Patient[0]);
                        if (selectedOption_id_Patient == null) {
                            JOptionPane.showMessageDialog(null, "No option selected");
                        } else {
                            Object[] options_id_Physician = new PatientModel().findAll().toArray();
                            if (options_id_Physician.length > 0) {
                                Physician selectedOption_id_Physician = (Physician) JOptionPane.showInputDialog(
                                        null,
                                        "Select the new Physician for the appointment:\n",
                                        "Updating the Physician",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        options_id_Physician,
                                        options_id_Physician[0]);
                                if (selectedOption_id_Physician == null) {
                                    JOptionPane.showMessageDialog(null, "No option selected");
                                } else {
                                    selectedOption.setDate_Appointment(date_Appointment);
                                    selectedOption.setTime_Appointment(Time_Appointment);
                                    selectedOption.setReason(reason);
                                    selectedOption.setId_Patient(selectedOption_id_Patient.getId_Patient());
                                    selectedOption.setId_Physician(selectedOption_id_Physician.getId_Physicians());

                                    if (instanceModel().update(selectedOption)) {
                                        JOptionPane.showMessageDialog(null, "Specialty Updated successfully");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Couldn't update the Specialty:");
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
