package gui;

import entity.enums.DataType;
import gui.listeners.DatabaseFormListeners;
import service.ColumnManager;
import service.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTableForm extends JFrame {
    private DatabaseManager databaseManager;
    private Map<JTextField,JComboBox> vals;
    private JButton addColumnButton;
    private JButton createButton;
    private String tableName;
    private JPanel panel;

    public CreateTableForm(DatabaseManager databaseManager, String tableName) throws HeadlessException {
        this.tableName = tableName;
        this.databaseManager = databaseManager;
        vals = new HashMap<>();
        addColumnButton = new JButton("add Column");
        createButton = new JButton("Save");
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        add(panel,BorderLayout.WEST);
        panel.add(addColumnButton);
        addColumnButton.addActionListener(e->{
            JTextField name = new JTextField("");
            JComboBox type = new JComboBox(DataType.values());
            JPanel temp = new JPanel();
            temp.setLayout(new GridLayout(2,10));
            vals.put(name,type);
            temp.add(name);
            temp.add(type);
            panel.add(temp);
            panel.revalidate();
            validate();
        });
        panel.add(new JPanel().add(createButton));

        createButton.addActionListener(e->{
            try{
                Map<String,DataType> map = new HashMap<>();
                vals.forEach((name,type)->{
                    map.put(name.getText(), (DataType) type.getSelectedItem());
                });
                databaseManager.createTable(tableName,ColumnManager.convertToColumnSet(map));

            }catch (RuntimeException ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        });



        setTitle(tableName);
        setSize(600, 200);
        setLocationRelativeTo(null);
    }
}
