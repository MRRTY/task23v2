package gui;

import entity.Column;
import entity.enums.DataType;
import service.RowManager;
import service.TableManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AddRowForm extends JFrame {
    private TableManager tableManager;
    private JButton addButton;
    private Map<Column, JTextField> map;


    public AddRowForm(TableManager tableManager) {
        this.tableManager = tableManager;
        map = new HashMap<>();
        addButton = new JButton("Add");
        initUI();
    }

    private void initUI() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1,2));
        for(Column c :tableManager.getTable().getColumns()){
            JTextField textField = new JTextField();
            map.put(c,textField);
            fieldPanel.add(new JLabel(c.getName()));
            fieldPanel.add(textField);
        }
        container.add(addButton,BorderLayout.NORTH);
        container.add(fieldPanel,BorderLayout.CENTER);
        try {
            addButton.addActionListener(e -> {
                Map<Column, Object> res = new HashMap<>();
                map.forEach((c, v) -> {
                    res.put(c, v.getText());
                });
                tableManager.createRow(RowManager.convertToRow(res));
            });
        }catch (RuntimeException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        setTitle(tableManager.getTable().getName());
        setSize(400, 100);
        setLocationRelativeTo(null);

    }
}
