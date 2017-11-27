package gui;

import entity.Database;
import gui.listeners.MainFormListeners;
import service.SystemManager;
import sun.swing.FilePane;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private MainFormListeners listeners;
    private SystemManager sm;
    private JButton createDatabaseButton;
    private JButton openDatabaseButton;
    private JButton deleteDatabaseButton;
    private JButton loadDatabaseButton;
    private JButton saveDatabaseButton;
    private JTextField loadDatabaseField;
    private JTextField nameField;
    private JComboBox<String> databaseListComboBox;

    public MainForm(){
        listeners = new MainFormListeners(this);
        sm = new SystemManager();
        loadDatabaseButton = new JButton("Load");
        saveDatabaseButton = new JButton("Save");
        loadDatabaseField = new JTextField("Path");
        DefaultComboBoxModel model = new DefaultComboBoxModel( SystemManager.getSystem().getDatabases().stream().map(Database::getName).toArray(String[]::new) );
        databaseListComboBox = new JComboBox<>(model);
        initUI();
    }

    public void initUI() {
        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout());
        filePanel.add(saveDatabaseButton, BorderLayout.WEST);
        filePanel.add(loadDatabaseField, BorderLayout.CENTER);
        filePanel.add(loadDatabaseButton, BorderLayout.EAST);
        createDatabaseButton = new JButton("Create Database");
        nameField = new JTextField("");
        openDatabaseButton = new JButton("Open");
        deleteDatabaseButton = new JButton("Delete");
        listeners.addCreateDatabaseListener(sm, createDatabaseButton, nameField);
        listeners.addDeleteDatabaseListener(sm,deleteDatabaseButton,databaseListComboBox);
        listeners.addOpenDatabaseListener(sm, databaseListComboBox, openDatabaseButton);
        listeners.addSaveDatabaseListener(sm, saveDatabaseButton,databaseListComboBox);
        listeners.addLoadDatabaseListener(sm, loadDatabaseField, loadDatabaseButton);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2,3));
        container.add(databaseListComboBox);
        container.add(openDatabaseButton);
        container.add(deleteDatabaseButton);
        container.add(nameField);
        container.add(createDatabaseButton);
        container.add(filePanel,BorderLayout.SOUTH);



        setTitle("Database");
        setSize(700, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public MainFormListeners getListeners() {
        return listeners;
    }

    public void setListeners(MainFormListeners listeners) {
        this.listeners = listeners;
    }

    public SystemManager getSm() {
        return sm;
    }

    public void setSm(SystemManager sm) {
        this.sm = sm;
    }

    public JButton getCreateDatabaseButton() {
        return createDatabaseButton;
    }

    public void setCreateDatabaseButton(JButton createDatabaseButton) {
        this.createDatabaseButton = createDatabaseButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JComboBox<String> getDatabaseListComboBox() {
        return databaseListComboBox;
    }

    public void setDatabaseListComboBox(JComboBox<String> databaseListComboBox) {
        this.databaseListComboBox = databaseListComboBox;
    }

    public JButton getOpenDatabaseButton() {
        return openDatabaseButton;
    }

    public void setOpenDatabaseButton(JButton openDatabaseButton) {
        this.openDatabaseButton = openDatabaseButton;
    }

}
