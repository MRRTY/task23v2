package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    private String name;
    private List<Table> tables;

    public Database(String name, List<Table> tables) {
        this.name = name;
        this.tables = tables;
    }

    public Database(String name) {
        this.name = name;
        tables = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
