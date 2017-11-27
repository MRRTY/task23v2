package gui;

import entity.Column;
import entity.Table;
import service.RowManager;
import service.TableManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EditRowForm extends JFrame{
    private TableManager tableManager;
    private  int id;
    private JButton saveButton;
    private Map<Column, JTextField> map;

    public EditRowForm(TableManager tableManager, String id) {
        map = new HashMap<>();
        this.tableManager = tableManager;
        this.id = Integer.parseInt(id);
        saveButton = new JButton("Save");
        initUI();
    }

    private void initUI() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1,2));
        for(Column c :tableManager.getTable().getColumns()){
            JTextField textField = new JTextField();
            textField.setText(tableManager.getRowManagerByRowIndex(id).getRow().getCells().stream().filter(cell -> cell.getColumn().equals(c)).collect(Collectors.toList()).get(0).getValue().toString());
            map.put(c,textField);
            fieldPanel.add(new JLabel(c.getName()));
            fieldPanel.add(textField);
        }
        container.add(saveButton, BorderLayout.NORTH);
        container.add(fieldPanel, BorderLayout.CENTER);
        saveButton.addActionListener(e->{

            Map<Column,Object> res = new HashMap<>();
            map.forEach((c,v)->{
                res.put(c,v.getText());
            });
            tableManager.editRow(id,RowManager.convertToRow(res));
        });
        setTitle(tableManager.getTable().getName());
        setSize(400, 100);
        setLocationRelativeTo(null);
    }
}
