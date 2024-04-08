package controller;

import entity.Patient;
import entity.Physician;
import entity.Specialty;
import model.PatientModel;
import model.SpecialtyModel;

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
        Date date_Birth = Date.valueOf(JOptionPane.showInputDialog(null, "Enter the date of birth of the new patient (yyyy-mm-dd)"));
        String identity_Document = JOptionPane.showInputDialog(null, "Enter the identity document of the new patient (no spaces, dashes or commas)");
        instanceModel().create(new Patient(name, last_name, date_Birth, identity_Document));
    }

    public void delete() {
        StringBuilder List = getAll((instanceModel().findAll()));
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

}
