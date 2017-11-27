package service;

import entity.Cell;
import entity.Column;
import entity.Row;
import entity.types.MyEnum;
import entity.types.StringArray;
import entity.types.StringIntl;
import exception.NotAllowedDataTypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RowManager {
    private Row row;

    public RowManager(Row row) {
        this.row = row;
    }

    public void editRow(Row newRow){
        row = newRow;
    }

    public static Row convertToRow(Map<Column,Object> map){
        List<Cell> cells = new ArrayList<>();
        map.forEach(((column, object) ->{
            switch (column.getType()){
                case INTEGER:
                    cells.add(new Cell<Integer>(column,Integer.parseInt(object.toString())));
                    break;
                case CHAR:
                    cells.add(new Cell<Character>(column,object.toString().charAt(0)));
                    break;
                case REAL:
                    cells.add(new Cell<Double>(column,Double.valueOf(object.toString())));
                    break;
                case MY_ENUM:
                    cells.add(new Cell<MyEnum>(column,new MyEnum(object.toString())));
                    break;
                case STRING_INVL:
                    cells.add(new Cell<StringIntl>(column, new StringIntl(object.toString())));
                    break;
                case STRING_ARRAY:
                    cells.add(new Cell<StringArray>(column, new StringArray(object.toString())));
                    break;
                default:
                    throw new NotAllowedDataTypeException();
            }

        }));
        return new Row(cells);
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }
}
