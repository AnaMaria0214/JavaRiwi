package controller;

import entity.Patient;
import model.PatientModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class PatientController {

    private Patient selectedOption;

    public PatientModel instanceModel() {
        return new PatientModel();
    }

    public StringBuilder getAll(List<Object> objectsList) {
        StringBuilder list = new StringBuilder("PATIENT REGISTERED:\n");
        if (objectsList.isEmpty()) {
            list.append("There is no patient yet");
        } else {
            for (Object obj : objectsList) {
                Patient objPatient = (Patient) obj;
                list.append(objPatient.toString()).append("\n");
            }
        }
        return list;
    }

    public void getAll() {
        JOptionPane.showMessageDialog(null, getAll((instanceModel().findAll())));
    }

    public void create() {
        String name = JOptionPane.showInputDialog(null, "Enter the name of the new patient");
        String last_name = JOptionPane.showInputDialog(null, "Enter the last names of the new patient");
        Date date_Birth = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the date of birth of the new patient (YYYY-MM-DD)"));
        String identity_Document = JOptionPane.showInputDialog(null, "Enter the identity document of the new patient (no spaces, dashes or commas)");
        instanceModel().create(new Patient(name, last_name, date_Birth, identity_Document));
    }

    public void delete() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Patient selectedOption = (Patient) JOptionPane.showInputDialog(
                    null,
                    "Select the patient that you want to delete:\n",
                    "Deleting a patient",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this patient?");
                if (confirm == 0) {
                    if (instanceModel().delete(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Patient successfully removed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't delete the patient");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No patient was deleted");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no patient yet");
        }

    }
    public void update() {
        Object[] options = instanceModel().findAll().toArray();
        if (options.length > 0) {
            Patient selectedOption = (Patient) JOptionPane.showInputDialog(
                    null,
                    "Select the Patient that you want to update:\n",
                    "Updating a Patient",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selectedOption == null) {
                JOptionPane.showMessageDialog(null, "No option selected");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to update this Patient?");
                if (confirm == 0) {
                    String name = JOptionPane.showInputDialog(null, "Enter the new name of the patient", selectedOption.getName());
                    String last_Name = JOptionPane.showInputDialog(null, "Enter the new last names of the patient", selectedOption.getName());
                    Date date_Birth = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the new date birth of the patient"));
                    String identity_Document  =JOptionPane.showInputDialog(null, "Enter the new identity document of the patient");

                    selectedOption.setName(name);
                    selectedOption.setLast_Name(last_Name);
                    selectedOption.setDate_Birth(date_Birth);
                    selectedOption.setIdentity_Document(identity_Document);
                    if (instanceModel().update(selectedOption)) {
                        JOptionPane.showMessageDialog(null, "Patient information successfully updated");
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't update the Specialty:");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"data update cancelled");
                }
            }
        }
    }

}
