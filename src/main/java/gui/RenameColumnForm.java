package gui;

import entity.Table;
import service.TableManager;

import javax.swing.*;
import java.awt.*;

public class RenameColumnForm extends JFrame {
    private TableManager tableManager;
    private JButton saveButton;
    private JTextField columnNameField;
    private JComboBox columnComboBox;
    public RenameColumnForm(TableManager tableManager) {
        this.tableManager = tableManager;
        saveButton = new JButton("Save");
        columnNameField = new JTextField("");
        columnComboBox = new JComboBox(tableManager.getTable().getColumns().stream().map(column -> column.getName()).toArray());
        initUI();
    }

    private void initUI() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(columnComboBox, BorderLayout.WEST);
        container.add(columnNameField, BorderLayout.CENTER);
        container.add(saveButton, BorderLayout.EAST);
        saveButton.addActionListener(e->{
            tableManager.getColumnManagerByName(columnComboBox.getSelectedItem().toString()).setNewColumnName(columnNameField.getText());
        });
        setTitle(tableManager.getTable().getName());
        setSize(400, 150);
        setLocationRelativeTo(null);


    }
}
