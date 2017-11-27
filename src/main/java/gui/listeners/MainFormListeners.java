package gui.listeners;

import entity.Database;
import gui.DatabaseForm;
import gui.MainForm;
import service.DatabaseManager;
import service.SystemManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFormListeners {
    private MainForm frame;

    public MainFormListeners(MainForm frame) {
        this.frame = frame;
    }

    public void addCreateDatabaseListener(SystemManager sm, JButton button, JTextField textField){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    sm.createDatabase(textField.getText());
                    refresh();
                }catch (RuntimeException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
    }
    private void refresh(){
        DefaultComboBoxModel model = new DefaultComboBoxModel( SystemManager.getSystem().getDatabases().stream().map(Database::getName).toArray(String[]::new) );
        frame.getDatabaseListComboBox().setModel(model);

    }

    public void addDeleteDatabaseListener(SystemManager sm, JButton deleteDatabaseButton, JComboBox<String> deleteDatabaseListComboBox) {
        deleteDatabaseButton.addActionListener(e -> {
            try {
                sm.removeDatabase(deleteDatabaseListComboBox.getSelectedItem().toString());
                refresh();
            }catch (RuntimeException ex){

                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        });
    }

    public void addOpenDatabaseListener(SystemManager sm, JComboBox<String> comboBox, JButton openDatabaseButton) {
        openDatabaseButton.addActionListener(e -> {
            new DatabaseForm(sm.getDatabaseManager(comboBox.getSelectedItem().toString())).setVisible(true);
        });
    }

    public void addSaveDatabaseListener(SystemManager sm, JButton saveDatabaseButton, JComboBox<String> databaseListComboBox) {
        saveDatabaseButton.addActionListener(e->{
            try {
                sm.getDatabaseManager(databaseListComboBox.getSelectedItem().toString()).saveDatabase();
                refresh();
            }catch (RuntimeException ex){

                JOptionPane.showMessageDialog(null,ex.getMessage());
            } catch (IOException e1) {
                System.out.println("test");
            }

        });
    }

    public void addLoadDatabaseListener(SystemManager sm, JTextField loadDatabaseField, JButton loadDatabaseButton) {
        loadDatabaseButton.addActionListener(e->{
            try {
                sm.loadDatabase(loadDatabaseField.getText());
                refresh();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });
    }
}

