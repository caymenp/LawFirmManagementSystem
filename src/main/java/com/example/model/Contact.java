package com.example.model;

import com.example.DAO.ContactDoeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Contact {
    private int contactID;
    private String contactName;
    private String contactEmail;

    public Contact(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    //Getter

    public int getContactID() {
        return contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    //Setter

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public ObservableList<Contact> getAllContacts() {
        try {
            return ContactDoeImpl.getAllContacts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<String> getAllContactStrings() {
        ObservableList<String> allStrings = FXCollections.observableArrayList();
        try {
            ObservableList<Contact> allContacts = ContactDoeImpl.getAllContacts();
            for (Contact c : allContacts) {
                String name = c.getContactName();
                allStrings.add(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return allStrings;
    }

    public static Contact getContact(String id) {
        Contact contact;

        try {
           contact = ContactDoeImpl.getContact(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contact;
    }

    public static Contact getContactByName(String name) {
        Contact contact;

        try {
            contact = ContactDoeImpl.getContactByName(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contact;
    }
}
