package gui.listeners;

import entity.System;
import entity.Table;
import gui.CreateTableForm;
import gui.DatabaseForm;
import gui.OpenTableForm;
import service.DatabaseManager;

import javax.swing.*;

public class DatabaseFormListeners {
    private DatabaseForm frame;
    private DatabaseManager databaseManager;

    public DatabaseFormListeners(DatabaseForm frame, DatabaseManager databaseManager) {
        this.frame = frame;
        this.databaseManager = databaseManager;
    }



    public void addCreateTableListener(DatabaseManager databaseManager, JButton createTableButton, JTextField nameField) {
        try{
            createTableButton.addActionListener(e->{
                new CreateTableForm(databaseManager,nameField.getText()).setVisible(true);
            });
        }catch (RuntimeException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

    }

    public void addOpenTableListener(DatabaseManager databaseManager, JButton openTableButton, JComboBox<String> tableListComboBox) {
        try{
            openTableButton.addActionListener(e->{
                new OpenTableForm(databaseManager.getTableManager(tableListComboBox.getSelectedItem().toString())).setVisible(true);
            });
        }catch (RuntimeException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

    }

    private void refresh(){

            DefaultComboBoxModel model = new DefaultComboBoxModel(databaseManager.getDatabase().getTables().stream().map(Table::getName).toArray());
            frame.getTableListComboBox().setModel(model);


    }

    public void addRefreshListener(JButton button) {
        button.addActionListener(e->{
            refresh();
        });
    }

    public void addDeleteTableListener(DatabaseManager databaseManager, JButton deleteTableButton, JComboBox<String> tableListComboBox) {
        try{
            deleteTableButton.addActionListener(e -> {
                databaseManager.deleteTable(tableListComboBox.getSelectedItem().toString());
                refresh();
            });
        }catch (RuntimeException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
}
