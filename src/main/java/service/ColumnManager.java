package service;

import entity.Column;
import entity.enums.DataType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ColumnManager {
    private Column column;

    public ColumnManager(Column column) {
        this.column = column;
    }

    public static Set<Column> convertToColumnSet(Map<String,DataType> map){
        Set<Column> result = new HashSet<>();
        map.forEach((key,value)->{
            result.add(new Column(key,value));
        });
        return result;
    }

    public void setNewColumnName(String name){
        column.setName(name);
    }
}
