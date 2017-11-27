package gui;

import entity.Column;
import entity.Table;
import service.DatabaseManager;
import service.TableManager;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

public class OpenTableForm extends JFrame {
    private TableManager tableManager;
    private Table table;
    private JTable jTable;
    private JButton addRowButton;
    private JButton refreshButton;
    private JButton editButton;
    private JButton renameColumnButton;
    private JTextField editTextField;

    public OpenTableForm(TableManager tableManager) {
        this.tableManager = tableManager;
        table = tableManager.getTable();
        jTable = new JTable(table.getRows().size(),table.getColumns().size());
        addRowButton = new JButton("Add Row");
        refreshButton = new JButton("Refresh");
        editButton = new JButton("Edit");
        editTextField = new JTextField("Enter row index");
        renameColumnButton = new JButton("Rename Column");
        refreshButton = new JButton("Refresh");
        initUI();
    }

    private void initUI() {

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BorderLayout());

        renameColumnButton.addActionListener(e->{
            new RenameColumnForm(tableManager).setVisible(true);
        });

        editButton.addActionListener(e->{
            new EditRowForm(tableManager,editTextField.getText()).setVisible(true);
        });

        refreshButton.addActionListener(e->{
            Object[][] rows = new Object[table.getRows().size()][table.getColumns().size()];
            for(int i = 0; i < table.getColumns().size(); i++){
                for(int j = 0; j < table.getRows().size(); j++){
                    rows[j][i] = table.getRows().get(j).getCells().get(i).getValue();
                }
            }

            TableModel tableModel = new DefaultTableModel(rows, table.getColumns().stream().map(Column::getName).toArray());
            jTable.setModel(tableModel);
        });
        addRowButton.addActionListener(e->{
            new AddRowForm(tableManager).setVisible(true);
        });
        Object[][] rows = new Object[table.getRows().size()][table.getColumns().size()];
        for(int i = 0; i < table.getColumns().size(); i++){
            for(int j = 0; j < table.getRows().size(); j++){
                rows[j][i] = table.getRows().get(j).getCells().get(i).getValue();
            }
        }

        TableModel tableModel = new DefaultTableModel(rows, table.getColumns().stream().map(Column::getName).toArray());
        jTable.setModel(tableModel);
        panel.add(jTable);
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(jTable), BorderLayout.CENTER);

        panel.add(jTable.getTableHeader(), BorderLayout.NORTH);
        container.add(panel);
        editPanel.add(editTextField,BorderLayout.WEST);
        editPanel.add(editButton,BorderLayout.CENTER);
        editPanel.add(renameColumnButton,BorderLayout.EAST);
        buttonPanel.add(addRowButton,BorderLayout.EAST);
        buttonPanel.add(refreshButton,BorderLayout.CENTER);
        buttonPanel.add(editPanel,BorderLayout.WEST);
        container.add(buttonPanel,BorderLayout.NORTH);
        setTitle(table.getName());
        setSize(500, 500);
        setLocationRelativeTo(null);
    }
}
