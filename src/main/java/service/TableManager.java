package service;

import entity.Column;
import entity.Row;
import entity.Table;
import exception.NoSuchColumnException;
import java.util.stream.Collectors;

public class TableManager {
    private Table table;

    public TableManager(Table table) {
        this.table = table;
    }

    public void createRow(Row row){
        table.getRows().add(row);
    }
    public void editRow(int i, Row newRow){
        table.getRows().remove(getRowManagerByRowIndex(i).getRow());
        table.getRows().add(i,newRow);
    }

    public RowManager getRowManagerByRowIndex(int index){
        return new RowManager(table.getRows().get(index));
    }

    public ColumnManager getColumnManagerByName(String name){
        return new ColumnManager(getColumnByName(name));
    }

    private Column getColumnByName(String name){
        if(checkColumnExist(name)){
            return table.getColumns().stream().filter(column -> column.getName().equals(name)).collect(Collectors.toList()).get(0);
        }else {
            throw new NoSuchColumnException();
        }
    }
    private boolean checkColumnExist(String name){
        return table.getColumns().stream().anyMatch(column -> column.getName().equals(name));
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
