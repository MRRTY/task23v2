package gui;

import entity.Database;
import entity.Table;
import gui.listeners.DatabaseFormListeners;
import service.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class DatabaseForm extends JFrame {
    private DatabaseFormListeners listeners;
    private DatabaseManager databaseManager;
    private JButton createTableButton;
    private JButton openTableButton;
    private JButton deleteTableButton;
    private JButton refreshButton;
    private JTextField nameField;
    private JComboBox<String> tableListComboBox;

    public DatabaseForm(DatabaseManager databaseManager) throws HeadlessException {
        super();
        listeners = new DatabaseFormListeners(this, databaseManager);
        this.databaseManager = databaseManager;
        initUI();
    }
    public void initUI() {

        createTableButton = new JButton("Create Table");
        nameField = new JTextField("");
        openTableButton = new JButton("Open");
        deleteTableButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");
        DefaultComboBoxModel model = new DefaultComboBoxModel(databaseManager.getDatabase().getTables().stream().map(Table::getName).toArray(String[]::new));
        tableListComboBox = new JComboBox<>(model);
        listeners.addDeleteTableListener(databaseManager,deleteTableButton,tableListComboBox);
        listeners.addCreateTableListener(databaseManager,createTableButton,nameField);
        listeners.addOpenTableListener(databaseManager,openTableButton,tableListComboBox);
        listeners.addRefreshListener(refreshButton);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2,3));
        container.add(tableListComboBox);
        container.add(openTableButton);
        container.add(deleteTableButton);
        container.add(nameField);
        container.add(createTableButton);
        container.add(refreshButton);



        setTitle(databaseManager.getDatabase().getName());
        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    public DatabaseFormListeners getListeners() {
        return listeners;
    }

    public void setListeners(DatabaseFormListeners listeners) {
        this.listeners = listeners;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public JButton getCreateTableButton() {
        return createTableButton;
    }

    public void setCreateTableButton(JButton createTableButton) {
        this.createTableButton = createTableButton;
    }

    public JButton getOpenTableButton() {
        return openTableButton;
    }

    public void setOpenTableButton(JButton openTableButton) {
        this.openTableButton = openTableButton;
    }

    public JButton getDeleteTableButton() {
        return deleteTableButton;
    }

    public void setDeleteTableButton(JButton deleteTableButton) {
        this.deleteTableButton = deleteTableButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JComboBox<String> getTableListComboBox() {
        return tableListComboBox;
    }

    public void setTableListComboBox(JComboBox<String> tableListComboBox) {
        this.tableListComboBox = tableListComboBox;
    }
}
