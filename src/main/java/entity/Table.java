package entity;

import exception.BadColumnException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Table implements Serializable {
    private String name;
    private Set<Column> columns;
    private List<Row> rows;


    public Table(String name, Set<Column> columns) {
        if(columns.size()<1){
            throw new BadColumnException();
        }
        this.rows = new ArrayList<>();
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Column> getColumns() {
        return columns;
    }

    public void setColumns(Set<Column> columns) {
        this.columns = columns;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
